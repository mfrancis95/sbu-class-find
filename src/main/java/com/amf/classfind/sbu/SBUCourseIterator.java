package com.amf.classfind.sbu;

import com.amf.classfind.CourseIterator;
import com.amf.classfind.FilterOption;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class SBUCourseIterator implements CourseIterator {
    
    private static final String url = "http://classfind.stonybrook.edu/vufind/Search/Results?page=";
    
    private final Filter filter;
        
    private final String lookFor, type;
    
    private Connection connection;
    
    private int count, index, total, page = 1;
    
    private Exception exception;
    
    private Elements recordSet;
    
    SBUCourseIterator(String lookFor, String type, Filter filter) {
        this.lookFor = lookFor;
        this.type = type;
        this.filter = filter;
    }
    
    public Exception getException() {
        return exception;
    }
    
    public boolean hasNext() {
        if (exception != null || (filter != null && total >= filter.getLimit())) {
            return false;
        }
        if (index >= count) {
            if (connection == null) {
                connection = Jsoup.connect(url + page++);
            }
            else {
                connection.url(url + page++);
            }
            connection.data("lookfor", lookFor).data("type", type);
            FilterOption option = filter.getDEC();
            if (option != null) {
                connection.data("filter[]", option.filterString());
            }
            option = filter.getSBC();
            if (option != null) {
                connection.data("filter[]", option.filterString());
            }
            option = filter.getSemester();
            if (option != null) {
                connection.data("filter[]", option.filterString());
            }
            try {
                Elements span18 = connection.get().getElementsByClass("span-18");
                if (span18.isEmpty()) {
                    throw new Exception();
                }
                recordSet = span18.first().getElementsByClass("recordSet").first().children();
            }
            catch (NullPointerException ex) {
                connection = null;
                count = index = total = 0;
                page = 1;
                return false;
            }
            catch (Exception ex) {
                connection = null;
                count = index = total = 0;
                exception = ex;
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