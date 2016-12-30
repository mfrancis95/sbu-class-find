package com.amf.sbu;

public class Section {
    
    public final String days, hours;
    
    public Section(String days, String hours) {
        this.days = days;
        this.hours = hours;
    }
    
    public String toString() {
        return days + " " + hours;
    }
    
}