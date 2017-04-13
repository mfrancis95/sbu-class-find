package com.amf.classfind.sbu;

import com.amf.classfind.FilterOption;

public enum DEC implements FilterOption {
    
    A1, A2, B, C, D, E, F, G, H, I, J, K;
    
    private static final String aFilter = "era_facet:\"%s - D.E.C. A %s crs & Skill 2\"";
    
    private static final String otherFilter = "era_facet:\"%s - D.E.C. Category %<s";
    
    private final String filterString;
    
    DEC() {
        String string = toString();
        switch (string.charAt(0)) {
            case 'A':
                filterString = String.format(aFilter, string, string.charAt(1) == '1' ? "1st" : "2nd");
                break;
            case 'C':
                filterString = String.format(otherFilter, string) + " & Skill 1\"";
                break;
            default:
                filterString = String.format(otherFilter, string) + "\"";
        }
    }
    
    public String filterString() {
        return filterString;
    }
    
}