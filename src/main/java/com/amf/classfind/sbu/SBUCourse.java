package com.amf.classfind.sbu;

import com.amf.classfind.AvailabilityException;
import com.amf.classfind.Course;
import com.amf.classfind.Section;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ListIterator;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class SBUCourse extends Course {
    
    private static final String url = "http://classfind.stonybrook.edu/vufind/AJAX/JSON?method=getItemVUStatuses&strm=%s&itemid=";
    
    private Integer availability;
    
    private String code, credit, department, instructor, number, section, semesterCode, title;
    
    private Section lab, lecture, recitation;
    
    SBUCourse(Element result) {
        extractCourse(result);
        extractCreditAndInstructor(result);
        extractSections(result);
        Element span3 = result.getElementsByClass("span-3").first();
        number = span3.className().split(" ")[2];
        number = number.substring(number.indexOf('d') + 1);        
        semesterCode = result.getElementsByClass("hiddenId").first().val();
        semesterCode = semesterCode.replaceAll("\\D+.*", "");        
        title = result.getElementsByClass("resultItemLine1").first().text();
    }
    
    public int availability() {
        return availability == null ? refreshAvailability() : availability;
    }
    
    public boolean equals(Object object) {
        if (!(object instanceof SBUCourse)) {
            return false;
        }
        SBUCourse course = (SBUCourse) object;
        return getNumber().equals(course.getNumber());
    }
    
    private void extractCourse(Element result) {
        String course = result.getElementsByClass("title").first().text();
        department = course.substring(0, 3);
        code = course.substring(3, 6);
        section = course.substring(7);  
    }
    
    private void extractCreditAndInstructor(Element result) {
        Element resultItemLine2 = result.getElementsByClass("resultItemLine2").first();
        instructor = resultItemLine2.child(0).text();
        int commaIndex = instructor.indexOf(",");
        if (commaIndex >= 0) {
            String firstName = instructor.substring(commaIndex + 1);
            instructor = firstName + " " + instructor.substring(0, commaIndex);            
        }
        credit = resultItemLine2.textNodes().get(2).text().trim();
    }
    
    private void extractSections(Element result) {
        Element span11 = result.getElementsByClass("span-11").get(1);
        ListIterator<Node> iterator = span11.childNodes().listIterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node instanceof TextNode) {
                continue;
            }
            String text = ((Element) node).text();
            if (text.startsWith("LAB")) {
                TextNode textNode = (TextNode) iterator.next();
                String[] split = textNode.text().trim().split(" ");
                lab = new Section(split[0].replaceFirst("\\W", ""), split[1]);
            }
            else if (text.startsWith("LEC")) {
                TextNode textNode = (TextNode) iterator.next();
                String[] split = textNode.text().trim().split(" ");
                lecture = new Section(split[0].replaceFirst("\\W", ""), split[1]);
            }
            else if (text.startsWith("REC")) {
                TextNode textNode = (TextNode) iterator.next();
                String[] split = textNode.text().trim().split(" ");
                recitation = new Section(split[0].replaceFirst("\\W", ""), split[1]);
            }
        }
    }
    
    public String getCode() {
        return code;
    }
    
    public String getCredit() {
        return credit;        
    }
    
    public String getDepartment() {
        return department;
    }    
    
    public String getInstructor() {
        return instructor;
    }
    
    public Section getLab() {
        return lab;
    }
    
    public Section getLecture() {
        return lecture;
    }
    
    public String getNumber() {
        return number;
    }
    
    public Section getRecitation() {
        return recitation;
    }
    
    public String getSection() {
        return section;
    }
    
    public String getSemesterCode() {
        return semesterCode;
    }
    
    public String getTitle() {
        return title;
    }
    
    public int hashCode() {
        return Integer.parseInt(number);
    }    
    
    public int refreshAvailability() {
        String url = String.format(this.url, semesterCode) + number;
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        catch (Exception ex) {
            throw new AvailabilityException(ex);
        }
        String substring = json.substring(json.indexOf("L>") + 2);
        availability = Integer.parseInt(substring.substring(0, substring.indexOf('<')));
        if (availability < 0) {
            substring = substring.substring(substring.indexOf("OS>") + 3);
            if (Integer.parseInt(substring.substring(0, substring.indexOf('<'))) == 0) {
                availability = 0;
            }
        }
        return availability;        
    }
    
    public boolean retrievedAvailability() {
        return availability != null;
    }
    
}