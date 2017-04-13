package com.amf.classfind.sbu;

public class Filter {
    
    private DEC dec;
    
    private int limit = Integer.MAX_VALUE;
    
    private SBC sbc;
    
    private Semester semester;
    
    private Filter() {}
    
    public DEC getDEC() {
        return dec;
    }
    
    public int getLimit() {
        return limit;
    }
    
    public SBC getSBC() {
        return sbc;
    }
    
    public Semester getSemester() {
        return semester;
    }
    
    public static class Builder {
        
        private final Filter filter = new Filter();
        
        public Filter build() {
            return filter;
        }
        
        public Builder setDEC(DEC dec) {
            filter.dec = dec;
            return this;
        }
        
        public Builder setLimit(int limit) {
            filter.limit = limit;
            return this;
        }
        
        public Builder setSBC(SBC sbc) {
            filter.sbc = sbc;
            return this;
        }
        
        public Builder setSemester(Semester semester) {
            filter.semester = semester;
            return this;
        }
        
    }
    
}