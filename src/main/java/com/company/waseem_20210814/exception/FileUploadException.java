package com.company.waseem_20210814.exception;

public class FileUploadException extends Exception {

    public FileUploadException(String fileName) {
        super("Unable to upload file " + fileName);
    }
}
