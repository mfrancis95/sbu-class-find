package com.amf.classfind;

import java.util.Iterator;

public abstract class CourseIterator implements Iterator<Course> {
    
    public int limit = Integer.MAX_VALUE;
    
    protected final String url;
    
    protected CourseIterator(String url) {
        this.url = url;
    }
    
}