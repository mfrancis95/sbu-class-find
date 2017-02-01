package com.amf.sbu;

public enum SBC implements FilterOption {
    
    ARTS("Explore %26amp%3B Understand the Fine %26amp%3B Perf. Arts"),
    CER("Practice %26 Respect Critical/Ethical Reasoning"),
    ESI("Evaluate %26amp%3B Synthesize Researched Information"),
    EXP_PLUS("Experiential Learning"),
    GLO("Engage Global Issues"),
    HFA_PLUS("Humanities and Fine Arts"),
    HUM("Use Critical Analysis %26 Methods of Humanities"),
    LANG("Communicate in a Language Other Than English"),
    QPS("Master Quantitative Problem Solving"),
    SBS("Observe %26 Analyze Human Behavior %26 Society"),
    SBS_PLUS("Social and Behavioral Sciences"),
    SNW("Study the Natural World"),
    SPK("Speak Effectively before an Audience"),
    STAS("Explore Interconnectedness"),
    STEM_PLUS("Science%2C Technology%2C Engineering %26amp%3B Math"),
    TECH("Understand Technology"),
    USA("Understand the History of the United States"),
    WRT("Write Effectively in English"),
    WRTD("Write Effectively within One's Discipline");
    
    private static final String filter = "&filter[]=genre_facet%%3A\"%s %s\"";
    
    private final String filterString;
    
    SBC(String description) {
        String name = toString();
        if (name.contains("_")) {
            filterString = String.format(filter, name.replace("_PLUS", "%2B"), description);
        }
        else {
            filterString = String.format(filter, name, description);
        }
    }

    public String filterString() {
        return filterString;
    }
    
}
