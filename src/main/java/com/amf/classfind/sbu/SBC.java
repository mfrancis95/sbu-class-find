package com.amf.classfind.sbu;

import com.amf.classfind.FilterOption;

public enum SBC implements FilterOption {
    
    ARTS("Explore &amp; Understand the Fine &amp; Perf. Arts"),
    CER("Practice & Respect Critical/Ethical Reasoning"),
    ESI("Evaluate & Synthesize Researched Information"),
    EXP_PLUS("Experiential Learning"),
    GLO("Engage Global Issues"),
    HFA_PLUS("Humanities and Fine Arts"),
    HUM("Use Critical Analysis & Methods of Humanities"),
    LANG("Communicate in a Language Other Than English"),
    QPS("Master Quantitative Problem Solving"),
    SBS("Observe & Analyze Human Behavior & Society"),
    SBS_PLUS("Social and Behavioral Sciences"),
    SNW("Study the Natural World"),
    SPK("Speak Effectively before an Audience"),
    STAS("Explore Interconnectedness"),
    STEM_PLUS("Science, Technology, Engineering &amp; Math"),
    TECH("Understand Technology"),
    USA("Understand the History of the United States"),
    WRT("Write Effectively in English"),
    WRTD("Write Effectively within One's Discipline");
    
    private static final String filter = "genre_facet:\"%s %s\"";
    
    private final String filterString;
    
    SBC(String description) {
        String name = toString();
        if (name.contains("_")) {
            filterString = String.format(filter, name.replace("_PLUS", "+"), description);
        }
        else {
            filterString = String.format(filter, name, description);
        }
    }

    public String filterString() {
        return filterString;
    }
    
}