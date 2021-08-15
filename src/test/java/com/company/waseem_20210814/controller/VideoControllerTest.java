package com.company.waseem_20210814.controller;

import static com.company.waseem_20210814.mock.VideoCategoryMock.getVideoCategoryMock;
import static com.company.waseem_20210814.mock.VideoMock.getVideoMock;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.FileAlreadyExistsException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.company.waseem_20210814.exception.EntityNotFoundException;
import com.company.waseem_20210814.exception.FileUploadException;
import com.company.waseem_20210814.service.StorageService;
import com.company.waseem_20210814.service.VideoCategoryService;
import com.company.waseem_20210814.service.VideoService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VideoController.class)
public class VideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StorageService storageService;

    @MockBean
    private VideoService videoService;

    @MockBean
    private VideoCategoryService videoCategoryService;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    @Test
    public void uploadVideo_successWithMP4() throws Exception {
        var mockFile =  new MockMultipartFile("file", "abc.mp4", "video/mp4", "some data".getBytes());
        var category = getVideoCategoryMock().get(0);
        var filePath = "file path";
        var title = "title";

        when(videoCategoryService.findById(category.getId())).thenReturn(category);
        when(storageService.store(any())).thenReturn(filePath);
        when(videoService.save(title, category, filePath)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/video")
                .file(mockFile)
                .param("title", title)
                .param("category_id", category.getId() + "")
        ).andExpect(status().isOk());

        verify(videoCategoryService).findById(category.getId());
        verify(storageService).store(any());
        verify(storageService, times(0)).delete(any());
        verify(videoService).save(title, category, filePath);
    }

    @Test
    public void uploadVideo_successWithMOV() throws Exception {
        var mockFile =  new MockMultipartFile("file", "abc.mov", "video/mov", "some data".getBytes());
        var category = getVideoCategoryMock().get(0);
        var filePath = "file path";
        var title = "title";

        when(videoCategoryService.findById(category.getId())).thenReturn(category);
        when(storageService.store(any())).thenReturn(filePath);
        when(videoService.save(title, category, filePath)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/video")
                .file(mockFile)
                .param("title", title)
                .param("category_id", category.getId() + "")
        ).andExpect(status().isOk());

        verify(videoCategoryService).findById(category.getId());
        verify(storageService).store(any());
        verify(storageService, times(0)).delete(any());
        verify(videoService).save(title, category, filePath);
    }

    @Test
    public void uploadVideo_unableToSaveVideoEntityInDB() throws Exception {
        var mockFile =  new MockMultipartFile("file", "abc.mp4", "video/mp4", "some data".getBytes());
        var category = getVideoCategoryMock().get(0);
        var filePath = "file path";
        var title = "title";

        when(videoCategoryService.findById(category.getId())).thenReturn(category);
        when(storageService.store(any())).thenReturn(filePath);
        when(videoService.save(title, category, filePath)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/video")
                .file(mockFile)
                .param("title", title)
                .param("category_id", category.getId() + "")
        ).andExpect(status().isOk());

        verify(videoCategoryService).findById(category.getId());
        verify(storageService).store(any());
        verify(storageService).delete(any());
        verify(videoService).save(title, category, filePath);
    }

    @Test
    public void uploadVideo_fileIsNotMP4OrMOV() throws Exception {
        var mockFile =  new MockMultipartFile("file", "abc.txt", "video/mov", "some data".getBytes());
        var category = getVideoCategoryMock().get(0);
        var filePath = "file path";
        var title = "title";


        mockMvc.perform(MockMvcRequestBuilders.multipart("/video")
                .file(mockFile)
                .param("title", title)
                .param("category_id", category.getId() + "")
        ).andExpect(status().isBadRequest());

        verify(videoCategoryService, times(0)).findById(category.getId());
        verify(storageService, times(0)).store(any());
        verify(storageService, times(0)).delete(any());
        verify(videoService, times(0)).save(title, category, filePath);
    }

    @Test
    public void uploadVideo_categoryIsNull() throws Exception {
        var mockFile =  new MockMultipartFile("file", "abc.txt", "video/mov", "some data".getBytes());
        var category = getVideoCategoryMock().get(0);
        var filePath = "file path";
        var title = "title";


        mockMvc.perform(MockMvcRequestBuilders.multipart("/video")
                .file(mockFile)
                .param("title", title)
                .param("category_id", "")
        ).andExpect(status().isBadRequest());

        verify(videoCategoryService, times(0)).findById(category.getId());
        verify(storageService, times(0)).store(any());
        verify(storageService, times(0)).delete(any());
        verify(videoService, times(0)).save(title, category, filePath);
    }

    @Test
    public void uploadVideo_titleIsEmpty() throws Exception {
        var mockFile =  new MockMultipartFile("file", "abc.txt", "video/mov", "some data".getBytes());
        var category = getVideoCategoryMock().get(0);
        var filePath = "file path";
        var title = "";


        mockMvc.perform(MockMvcRequestBuilders.multipart("/video")
                .file(mockFile)
                .param("title", title)
                .param("category_id", "")
        ).andExpect(status().isBadRequest());

        verify(videoCategoryService, times(0)).findById(category.getId());
        verify(storageService, times(0)).store(any());
        verify(storageService, times(0)).delete(any());
        verify(videoService, times(0)).save(title, category, filePath);
    }

    @Test
    public void uploadVideo_categoryNotPresent() throws Exception {
        var mockFile =  new MockMultipartFile("file", "abc.mp4", "video/mp4", "some data".getBytes());
        var category = getVideoCategoryMock().get(0);
        var filePath = "file path";
        var title = "title";

        when(videoCategoryService.findById(category.getId())).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/video")
                .file(mockFile)
                .param("title", title)
                .param("category_id", category.getId() + "")
        ).andExpect(status().isBadRequest());

        verify(videoCategoryService).findById(category.getId());
        verify(storageService, times(0)).store(any());
        verify(storageService, times(0)).delete(any());
        verify(videoService, times(0)).save(title, category, filePath);
    }

    @Test
    public void uploadVideo_fileAlreadyException() throws Exception {
        var mockFile =  new MockMultipartFile("file", "abc.mp4", "video/mp4", "some data".getBytes());
        var category = getVideoCategoryMock().get(0);
        var filePath = "file path";
        var title = "title";

        when(videoCategoryService.findById(category.getId())).thenReturn(category);
        when(storageService.store(any())).thenThrow(FileAlreadyExistsException.class);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/video")
                .file(mockFile)
                .param("title", title)
                .param("category_id", category.getId() + "")
        ).andExpect(status().isBadRequest());

        verify(videoCategoryService).findById(category.getId());
        verify(storageService).store(any());
        verify(storageService, times(0)).delete(any());
        verify(videoService, times(0)).save(title, category, filePath);
    }

    @Test
    public void uploadVideo_fileUploadException() throws Exception {
        var mockFile =  new MockMultipartFile("file", "abc.mp4", "video/mp4", "some data".getBytes());
        var category = getVideoCategoryMock().get(0);
        var filePath = "file path";
        var title = "title";

        when(videoCategoryService.findById(category.getId())).thenReturn(category);
        when(storageService.store(any())).thenThrow(FileUploadException.class);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/video")
                .file(mockFile)
                .param("title", title)
                .param("category_id", category.getId() + "")
        ).andExpect(status().isInternalServerError());

        verify(videoCategoryService).findById(category.getId());
        verify(storageService).store(any());
        verify(storageService, times(0)).delete(any());
        verify(videoService, times(0)).save(title, category, filePath);
    }

    @Test
    public void getAllVideo() throws Exception {
        var mockData = getVideoMock();
        when(videoService.findAll()).thenReturn(mockData);

        var actions = mockMvc.perform(MockMvcRequestBuilders.get("/video/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(mockData.size())));

        for (int i=0; i<mockData.size(); i++)
        {
            actions = actions.andExpect(jsonPath("$["+i+"].id").value(mockData.get(i).getId()));
            actions = actions.andExpect(jsonPath("$["+i+"].title").value(mockData.get(i).getTitle()));
            actions = actions.andExpect(jsonPath("$["+i+"].category").value(mockData.get(i).getCategory().getName()));
            actions = actions.andExpect(jsonPath("$["+i+"].thumbnails").isArray());
            actions = actions.andExpect(jsonPath("$["+i+"].thumbnails", hasSize(mockData.get(i).getThumbnails().size())));

        }
    }

    @Test
    public void getVideo() throws Exception {
        var mockData = getVideoMock().get(0);
        var id = mockData.getId();

        when(storageService.load(mockData.getFilePath())).thenReturn(new UrlResource(temporaryFolder.getRoot().toURI()));
        when(videoService.findById(id)).thenReturn(mockData);

        mockMvc.perform(MockMvcRequestBuilders.get("/video/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.path").value(temporaryFolder.getRoot().getAbsolutePath()));

    }

}
