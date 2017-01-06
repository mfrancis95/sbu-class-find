package com.amf.sbu;

public class Filter {
    
    public DEC dec;
    
    public Semester semester;
    
    public String toString() {
        StringBuilder filter = new StringBuilder();
        if (dec != null) {
            filter.append(dec.filterString());
        }
        if (semester != null) {
            filter.append(semester.filterString());
        }
        return filter.toString();
    }
    
}