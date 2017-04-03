package com.amf.classfind;

public class AvailabilityException extends RuntimeException {
    
    public AvailabilityException() {
        super();
    }
    
    public AvailabilityException(String message) {
        super(message);
    }
    
    public AvailabilityException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public AvailabilityException(Throwable cause) {
        super(cause);
    }
    
}