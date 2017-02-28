package com.amf.classfind.sbu;

import com.amf.classfind.CourseIterator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class SBUCourseIterator extends CourseIterator{
        
    private Connection connection;
    
    private int count, index, total, page = 1;
    
    private Elements recordSet;
    
    SBUCourseIterator(String url) {
        super(url);
    }
    
    public boolean hasNext() {
        if (total >= limit) {
            return false;
        }
        if (index >= count) {
            if (connection == null) {
                connection = Jsoup.connect(url + page++);
            }
            else {
                connection.url(url + page++);
            }
            try {
                recordSet = connection.get().getElementsByClass("recordSet").first().children();
            }
            catch (Exception ex) {
                connection = null;
                count = index = total = 0;
                page = 1;
                return false;
            }
            count = recordSet.size();
            index = 0;
        }
        return index < count;
    }

    public SBUCourse next() {
        total++;
        return new SBUCourse(recordSet.get(index++));
    }
    
}