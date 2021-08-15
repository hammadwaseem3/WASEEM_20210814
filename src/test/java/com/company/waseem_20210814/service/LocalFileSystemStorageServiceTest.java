package com.company.waseem_20210814.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.mock.web.MockMultipartFile;

import com.company.waseem_20210814.exception.EntityNotFoundException;
import com.company.waseem_20210814.exception.FileUploadException;

public class LocalFileSystemStorageServiceTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private LocalFileSystemStorageService storageService;

    public LocalFileSystemStorageServiceTest() throws IOException {
    }

    @Before
    public void setUp() throws IOException {
        this.storageService = new LocalFileSystemStorageService(temporaryFolder.newFolder("upload").getAbsolutePath());
    }

    @Test
    public void store_success() throws IOException, FileUploadException {
        var mockFile =  new MockMultipartFile("data", "abc.mp4", "video/mp4", "some data".getBytes());
        storageService.store(mockFile);
        assertTrue(temporaryFolder.getRoot().listFiles()[0].listFiles()[0].toString().endsWith("abc.mp4"));
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void store_fileAlreadyExist() throws IOException, FileUploadException {
        var mockFile =  new MockMultipartFile("data", "abc.mp4", "video/mp4", "some data".getBytes());
        storageService.store(mockFile); //create file first time
        storageService.store(mockFile); //throws exception
    }

    @Test(expected = FileUploadException.class)
    public void store_fileUploadException() throws IOException, FileUploadException {
        temporaryFolder.delete();
        var mockFile =  new MockMultipartFile("data", "abc.mp4", "video/mp4", "some data".getBytes());
        storageService.store(mockFile);
    }

    @Test
    public void load_success() throws IOException, FileUploadException, EntityNotFoundException {
        var mockFile =  new MockMultipartFile("data", "abc.mp4", "video/mp4", "some data".getBytes());
        storageService.store(mockFile); //first store it
        var result = storageService.load("abc.mp4");
        assertTrue(result.getFile().toString().endsWith("abc.mp4"));
    }

    @Test(expected = EntityNotFoundException.class)
    public void load_resourceNotExist() throws IOException, EntityNotFoundException {
        storageService.load("abc.mp4");
    }

    @Test
    public void delete() throws IOException, FileUploadException {
        var mockFile =  new MockMultipartFile("data", "abc.mp4", "video/mp4", "some data".getBytes());
        storageService.store(mockFile); //first store it
        storageService.delete("abc.mp4");
        assertEquals(0, temporaryFolder.getRoot().listFiles()[0].listFiles().length);
    }
}
