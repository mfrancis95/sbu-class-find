package com.amf.sbu;

public class ClassFind {
    
    private static final String url = "http://classfind.stonybrook.edu/vufind/Search/Results?type=%s&lookfor=%s&filter[]=ctrlnum%%3A\"%s\"&page=";

    private static String buildSemesterString(Semester semester) {
        return semester + "+" + Semester.yearForSemester(semester);
    }
    
    public static CourseIterator byCourse(String department, Filter filter) {
        return byCourse(department, "", filter);
    }
    
    public static CourseIterator byCourse(String department, String code, Filter filter) {
        return new CourseIterator(String.format(url, "callnumber", department + code, buildSemesterString(filter.semester)));
    }
    
    public static CourseIterator byInstructor(String instructor, Filter filter) {
        return new CourseIterator(String.format(url, "Author", instructor, buildSemesterString(filter.semester)));
    }
    
    public static CourseIterator byTitle(String title, Filter filter) {
        return new CourseIterator(String.format(url, "Title", title, buildSemesterString(filter.semester)));
    }

}