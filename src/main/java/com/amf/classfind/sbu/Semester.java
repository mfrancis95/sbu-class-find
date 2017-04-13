package com.amf.classfind.sbu;

import com.amf.classfind.FilterOption;
import java.util.Calendar;

public enum Semester implements FilterOption {
    
    WINTER, SPRING, SUMMER, FALL;
    
    private static final String filter = "ctrlnum:\"%s %d\"";
    
    private final String name;
    
    Semester() {
        String capital = super.toString();
        name = capital.substring(0, 1) + capital.substring(1).toLowerCase();
    }
    
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
        return String.format(filter, name, year());
    }
    
    public Semester next() {
        switch (this) {
            case WINTER:
                return SPRING;
            case SPRING:
                return SUMMER;
            case SUMMER:
                return FALL;
            default:
                return WINTER;
        }
    }
    
    public Semester previous() {
        switch (this) {
            case WINTER:
                return FALL;
            case SPRING:
                return WINTER;
            case SUMMER:
                return SPRING;
            default:
                return SUMMER;
        }
    }
    
    public int year() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH), year = calendar.get(Calendar.YEAR);
        switch (this) {
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
        return name;
    }
    
}