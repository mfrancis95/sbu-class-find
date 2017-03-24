package com.amf.classfind.sbu;

public class Filter {
    
    private DEC dec;
    
    private SBC sbc;
    
    private Semester semester;
    
    private Filter() {}
    
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
    
    public static class Builder {
        
        private final Filter filter = new Filter();
        
        public Filter build() {
            return filter;
        }
        
        public Builder dec(DEC dec) {
            filter.dec = dec;
            return this;
        }
        
        public Builder sbc(SBC sbc) {
            filter.sbc = sbc;
            return this;
        }
        
        public Builder semester(Semester semester) {
            filter.semester = semester;
            return this;
        }
        
    }
    
}