package com.amf.classfind;

import java.io.Serializable;

public abstract class Course implements Serializable {
    
    public abstract int availability();
    
    public String getCode() {
        return null;
    }
    
    public String getCredit() {
        return null;
    }
    
    public String getDepartment() {
        return null;
    }
    
    public String getInstructor() {
        return null;
    }
    
    public Section getLab() {
        return null;
    }
    
    public Section getLecture() {
        return null;
    }
    
    public String getNumber() {
        return null;
    }
    
    public Section getRecitation() {
        return null;
    }
    
    public String getSection() {
        return null;
    }
    
    public String getSemesterCode() {
        return null;
    }
    
    public String getTitle() {
        return null;
    }
    
    public abstract int refreshAvailability();
    
    public boolean retrievedAvailability() {
        return false;
    }

}