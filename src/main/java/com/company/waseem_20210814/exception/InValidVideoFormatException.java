package com.company.waseem_20210814.exception;

public class InValidVideoFormatException extends Exception {

    public InValidVideoFormatException() {
        super("video format should be in mp4 or mov");
    }
}
