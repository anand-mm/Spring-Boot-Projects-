package com.gepnic.mobileservice.Exception;

public class TenderNotFoundException extends RuntimeException{

    public TenderNotFoundException(Long id){
        super(String.format("Tender with Id %d not found", id));
    }
    
}
