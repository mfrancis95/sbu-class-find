package com.amf.sbu;

public class ClassFind {
    
    private static final String url = "http://classfind.stonybrook.edu/vufind/Search/Results?type=%s&lookfor=%s%s&page=";
    
    public static CourseIterator all(Filter filter) {
        return byTitle("", filter);
    }
    
    public static CourseIterator byCourse(String department, Filter filter) {
        return byCourse(department, "", filter);
    }
    
    public static CourseIterator byCourse(String department, String code, Filter filter) {
        return new CourseIterator(String.format(url, "callnumber", department + code, filter == null ? "" : filter));
    }
    
    public static CourseIterator byInstructor(String instructor, Filter filter) {
        return new CourseIterator(String.format(url, "Author", instructor, filter == null ? "" : filter));
    }
    
    public static CourseIterator byTitle(String title, Filter filter) {
        return new CourseIterator(String.format(url, "Title", title, filter == null ? "" : filter));
    }

}