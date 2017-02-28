package com.amf.classfind.sbu;

public class Filter {
    
    public DEC dec;
    
    public SBC sbc;
    
    public Semester semester;
    
    public String toString() {
        StringBuilder filter = new StringBuilder();
        if (dec != null) {
            filter.append(dec.filterString());
        }
        if (sbc != null) {
            filter.append(sbc.filterString());
        }
        if (semester != null) {
            filter.append(semester.filterString());
        }
        return filter.toString();
    }
    
}