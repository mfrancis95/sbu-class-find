package com.amf.classfind.sbu;

public class ClassFind {
    
    private static final String url = "http://classfind.stonybrook.edu/vufind/Search/Results?type=%s&lookfor=%s%s&page=";
    
    public static SBUCourseIterator all(Filter filter) {
        return byTitle("", filter);
    }
    
    public static SBUCourseIterator byCourse(String department, Filter filter) {
        return byCourse(department, "", filter);
    }
    
    public static SBUCourseIterator byCourse(String department, String code, Filter filter) {
        return new SBUCourseIterator(String.format(url, "callnumber", department + code, filter == null ? "" : filter));
    }
    
    public static SBUCourseIterator byInstructor(String instructor, Filter filter) {
        return new SBUCourseIterator(String.format(url, "Author", instructor, filter == null ? "" : filter));
    }
    
    public static SBUCourseIterator byTitle(String title, Filter filter) {
        return new SBUCourseIterator(String.format(url, "Title", title, filter == null ? "" : filter));
    }

}