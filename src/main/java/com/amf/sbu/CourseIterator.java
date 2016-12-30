package com.amf.sbu;

import java.util.Iterator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class CourseIterator implements Iterator<Course> {
    
    private final String url;
        
    private Connection connection;
    
    private int count, index, limit = Integer.MAX_VALUE, total, page = 1;
    
    private Elements recordSet;
    
    CourseIterator(String url) {
        this.url = url;
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
    
    public CourseIterator limit(int limit) {
        this.limit = limit;
        return this;
    }

    public Course next() {
        total++;
        return new Course(recordSet.get(index++));
    }
    
}