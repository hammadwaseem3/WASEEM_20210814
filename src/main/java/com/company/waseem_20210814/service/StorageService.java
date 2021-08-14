package com.company.waseem_20210814.service;


import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.company.waseem_20210814.exception.EntityNotFoundException;
import com.company.waseem_20210814.exception.FileUploadException;

public interface StorageService {

    String store(MultipartFile file) throws FileUploadException, FileAlreadyExistsException;

    Resource load(String filename) throws EntityNotFoundException, MalformedURLException;
}
