package com.amf.sbu;

public enum DEC implements FilterOption {
    
    A, B, C, D, E, F, G, H, I, J, K;
    
    private static final String filter = "&filter[]=era_facet%%3A\"%s+-+D.E.C.+Category+%<s\"";
    
    private final String filterString;
    
    DEC() {
        filterString = String.format(filter, toString());
    }
    
    public String filterString() {
        return filterString;
    }
    
}