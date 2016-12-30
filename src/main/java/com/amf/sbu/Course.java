package com.amf.sbu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ListIterator;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class Course {
    
    private final static String url = "http://classfind.stonybrook.edu/vufind/AJAX/JSON?method=getItemVUStatuses&strm=%s&itemid=";
    
    private final Element result;
    
    private boolean hasLab = true, hasRecitation = true;
    
    private String code, credit, department, instructor, number, section, semesterCode, title;
    
    private Section lab, lecture, recitation;
    
    Course(Element result) {
        this.result = result;
    }
    
    public int availability() throws IOException {
        URLConnection connection = new URL(String.format(url, semesterCode()) + number()).openConnection();
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            while (reader.ready()) {
                json.append(reader.readLine());
            }
        }
        String substring = json.substring(json.indexOf("L>") + 2);
        return Integer.parseInt(substring.substring(0, substring.indexOf('<')));
    }
    
    public String code() {
        if (code == null) {
            extractCourse();
        }
        return code;
    }
    
    public String credit() {
        if (credit == null) {
            extractCreditAndInstructor();
        }
        return credit;        
    }
    
    public String department() {
        if (department == null) {
            extractCourse();
        }
        return department;
    }
    
    private void extractCourse() {
        String course = result.getElementsByClass("title").first().text();
        department = course.substring(0, 3);
        code = course.substring(3, 6);
        section = course.substring(7);  
    }
    
    private void extractCreditAndInstructor() {
        Element resultItemLine2 = result.getElementsByClass("resultItemLine2").first();
        instructor = resultItemLine2.child(0).text();
        credit = resultItemLine2.textNodes().get(2).text().trim();
    }
    
    private void extractSpan11() {
        hasLab = hasRecitation = false;
        Element span11 = result.getElementsByClass("span-11").get(1);
        ListIterator<Node> iterator = span11.childNodes().listIterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node instanceof TextNode) {
                continue;
            }
            String text = ((Element) node).text();
            if (text.startsWith("LAB")) {
                hasLab = true;
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
                hasRecitation = true;
                TextNode textNode = (TextNode) iterator.next();
                String[] split = textNode.text().trim().split(" ");
                recitation = new Section(split[0].replaceFirst("\\W", ""), split[1]);
            }
        }
    }
    
    public String instructor() {
        if (instructor == null) {
            extractCreditAndInstructor();
        }
        return instructor;
    }
    
    public Section lab() {
        if (!hasLab) {
            return null;
        }
        if (lab == null) {
            extractSpan11();
        }
        return lab;
    }
    
    public Section lecture() {
        if (lecture == null) {
            extractSpan11();
        }
        return lecture;
    }
    
    public String number() {
        if (number == null) {
            Element span3 = result.getElementsByClass("span-3").first();
            number = span3.className().split(" ")[2];
            number = number.substring(number.indexOf('d') + 1);
        }
        return number;
    }
    
    public Section recitation() {
        if (!hasRecitation) {
            return null;
        }
        if (recitation == null) {
            extractSpan11();
        }
        return recitation;
    }
    
    public String section() {
        if (section == null) {
            extractCourse();
        }
        return section;
    }
    
    public String semesterCode() {
        if (semesterCode == null) {
            semesterCode = result.getElementsByClass("hiddenId").first().val();
            semesterCode = semesterCode.replaceAll("\\D+.*", "");
        }
        return semesterCode;
    }
    
    public String title() {
        if (title == null) {
            title = result.getElementsByClass("resultItemLine1").first().text();
        }
        return title;
    }
    
}