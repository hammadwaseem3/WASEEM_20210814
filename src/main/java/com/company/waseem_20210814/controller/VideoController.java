package com.company.waseem_20210814.controller;

import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.waseem_20210814.exception.EntityNotFoundException;
import com.company.waseem_20210814.exception.FileUploadException;
import com.company.waseem_20210814.exception.InValidVideoFormatException;
import com.company.waseem_20210814.service.StorageService;
import com.company.waseem_20210814.service.VideoCategoryService;
import com.company.waseem_20210814.service.VideoService;

@RestController
@RequestMapping("video")
@Validated
public class VideoController {

    private StorageService storageService;
    private VideoService videoService;
    private VideoCategoryService videoCategoryService;

    public VideoController(final StorageService storageService, final VideoService videoService, final VideoCategoryService videoCategoryService) {
        this.storageService = storageService;
        this.videoService = videoService;
        this.videoCategoryService = videoCategoryService;
    }

    @PostMapping
    public void uploadVideo(
            @RequestParam @NotNull @NotEmpty String title,
            @RequestParam("category_id") @NotNull Integer categoryId,
            @RequestParam("file") MultipartFile video)
            throws InValidVideoFormatException, FileUploadException, EntityNotFoundException, FileAlreadyExistsException {
        if(!video.getOriginalFilename().endsWith(".mp4") && !video.getOriginalFilename().endsWith(".mov") ) {
            throw new InValidVideoFormatException();
        }
        var category = videoCategoryService.findById(categoryId);

        var filePath = storageService.store(video);
        videoService.save(title, category, filePath);

    }

    //TODO need to figure out the return type
    @GetMapping("/{id}")
    public Resource getVideo(@PathVariable final Integer id) throws EntityNotFoundException, MalformedURLException {
        var filePath = videoService.findById(id).getFilePath();

        return storageService.load(filePath);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException exception) {
        return getResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InValidVideoFormatException.class)
    public ResponseEntity<String> handleInValidVideoFormatException(InValidVideoFormatException exception) {
        return getResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<String> handleFileUploadException(FileUploadException exception) {
        return getResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileAlreadyExistsException.class)
    public ResponseEntity<String> handleFileAlreadyExistsException(FileAlreadyExistsException exception) {
        return getResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return getResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException exception) {
        return getResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedURLException.class)
    public ResponseEntity<String> handleMalformedURLException(MalformedURLException exception) {
        return getResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> getResponseEntity(Exception exception, HttpStatus status) {
        var message = "{ \"message\": \""+exception.getMessage()+"\"}";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(message);
    }
}
