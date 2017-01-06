package com.amf.sbu;

import java.util.Calendar;

public enum Semester implements FilterOption {
    
    WINTER, SPRING, SUMMER, FALL;
    
    private static final String filter = "&filter[]=ctrlnum%%3A\"%s+%d\"";
    
    public static Semester current() {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        if (month == 0) {
            return WINTER;
        }
        if (month <= 4) {
            return SPRING;
        }
        if (month <= 7) {
            return SUMMER;
        }
        return FALL;
    }
    
    public String filterString() {
        return String.format(filter, toString(), yearForSemester(this));
    }
    
    public static Semester next() {
        return next(current());
    }
    
    public static Semester next(Semester semester) {
        Semester[] semesters = Semester.values();
        int index = semester.ordinal() + 1;
        return index < semesters.length ? semesters[index] : semesters[0];
    }
    
    public static Semester previous() {
        return previous(current());
    }
    
    public static Semester previous(Semester semester) {
        Semester[] semesters = Semester.values();
        int index = semester.ordinal() - 1;
        return index < 0 ? semesters[semesters.length - 1] : semesters[index];
    }
    
    public static int yearForSemester(Semester semester) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH), year = calendar.get(Calendar.YEAR);
        switch (semester) {
            case WINTER:
            case SPRING:
                return month <= 7 ? year : year + 1;
            case SUMMER:
                return year;
            default:
                return month == 0 ? year - 1 : year;
        }
    }
    
    public String toString() {
        String name = super.toString();
        return name.substring(0, 1) + name.substring(1).toLowerCase();
    }
    
}