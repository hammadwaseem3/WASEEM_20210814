package com.company.waseem_20210814.service;

import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.company.waseem_20210814.exception.EntityNotFoundException;
import com.company.waseem_20210814.exception.FileUploadException;

@Service
public class LocalFileSystemStorageService implements StorageService {

    private Path PATH_TO_UPLOAD = Paths.get("/Users/hammad.waseem/upload");

    @Override
    public String store(final MultipartFile file) throws FileUploadException, FileAlreadyExistsException {
        try {
            var path = PATH_TO_UPLOAD.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), path);
            return path.toString();
        }
        catch (FileAlreadyExistsException e) {
            throw new FileAlreadyExistsException("file name already exist in the system");
        } catch (Exception e) {
            throw new FileUploadException(file.getOriginalFilename());
        }
    }

    @Override
    public Resource load(String filename) throws EntityNotFoundException, MalformedURLException {

        Resource resource = new UrlResource(PATH_TO_UPLOAD.resolve(filename).toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new EntityNotFoundException("file");
        }

    }
}
