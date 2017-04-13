package com.amf.classfind;

import java.util.Iterator;

public interface CourseIterator extends Iterator<Course> {
    
    Exception getException();
    
}