package com.amf.classfind.sbu;

public class ClassFind {
    
    public static SBUCourseIterator all() {
        return all(null);
    }
    
    public static SBUCourseIterator all(Filter filter) {
        return byTitle("", filter);
    }
    
    public static SBUCourseIterator byCourse(String department) {
        return byCourse(department, "", null);
    }
    
    public static SBUCourseIterator byCourse(String department, Filter filter) {
        return byCourse(department, "", filter);
    }
    
    public static SBUCourseIterator byCourse(String department, String code) {
        return byCourse(department, code, null);
    }
    
    public static SBUCourseIterator byCourse(String department, String code, Filter filter) {
        return new SBUCourseIterator(department + code, "callnumber", filter);
    }
    
    public static SBUCourseIterator byInstructor(String firstName, String lastName) {
        return byInstructor(firstName, lastName, null);
    }
    
    public static SBUCourseIterator byInstructor(String firstName, String lastName, Filter filter) {
        return new SBUCourseIterator(lastName + ", " + firstName, "Author", filter);
    }
    
    public static SBUCourseIterator byTitle(String title) {
        return byTitle(title, null);
    }
    
    public static SBUCourseIterator byTitle(String title, Filter filter) {
        return new SBUCourseIterator(title, "Title", filter);
    }

}