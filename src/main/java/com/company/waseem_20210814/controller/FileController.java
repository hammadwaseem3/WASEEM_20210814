package com.company.waseem_20210814.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @GetMapping("/file/**")
    public byte[] getFile(HttpServletRequest request) throws IOException {

        String path = request.getRequestURL().toString().substring(26).replace("%7D", "");
        File file = new File(path);
        byte[] fileContent = FileUtils.readFileToByteArray(file);
        return fileContent;
    }
}
