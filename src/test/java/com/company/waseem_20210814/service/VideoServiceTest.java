package com.company.waseem_20210814.service;

import static com.company.waseem_20210814.mock.VideoCategoryMock.getVideoCategoryMock;
import static com.company.waseem_20210814.mock.VideoMock.getVideoMock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.company.waseem_20210814.exception.EntityNotFoundException;
import com.company.waseem_20210814.repository.VideoRepository;

@RunWith(MockitoJUnitRunner.class)
public class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ThumbnailService thumbnailService;

    @InjectMocks
    private VideoService videoService;

    @Test
    public void save_success() {
        var mockCategory = getVideoCategoryMock().get(0);
        var video = getVideoMock().get(0);

        when(videoRepository.save(any())).thenReturn(video);
        var result = videoService.save(video.getTitle(), mockCategory,  video.getFilePath());

        assertTrue(result);
        verify(videoRepository).save(any());
        verify(thumbnailService).createThumbnailAsync( video.getFilePath(), video.getId());
    }

    @Test
    public void save_exception() {
        var mockCategory = getVideoCategoryMock().get(0);
        var video = getVideoMock().get(0);


        when(videoRepository.save(any())).thenThrow(new RuntimeException(""));
        var result = videoService.save(video.getTitle(), mockCategory, video.getFilePath());

        assertFalse(result);
        verify(videoRepository).save(any());
        verify(thumbnailService, times(0)).createThumbnailAsync(video.getFilePath(), video.getId());
    }

    @Test
    public void findById_success() throws EntityNotFoundException {
        var video = getVideoMock().get(0);

        when(videoRepository.findById(video.getId())).thenReturn(Optional.of(video));

        var result = videoService.findById(video.getId());

        assertEquals(video.getId(), result.getId());
        assertEquals(video.getTitle(), result.getTitle());
        assertEquals(video.getFilePath(), result.getFilePath());
        assertEquals(video.getCategory(), result.getCategory());

    }

    @Test(expected = EntityNotFoundException.class)
    public void findById_entityNotFound() throws EntityNotFoundException {
        var video = getVideoMock().get(0);

        when(videoRepository.findById(video.getId())).thenReturn(Optional.empty());

        videoService.findById(video.getId());
    }

    @Test
    public void findAll() throws EntityNotFoundException {
        var videos = getVideoMock();

        when(videoRepository.findAll()).thenReturn(videos);

        var result = videoService.findAll();

        assertEquals(videos.size(), result.size());

        for(int i=0; i<videos.size(); i++) {
            assertEquals(videos.get(i).getId(), result.get(i).getId());
            assertEquals(videos.get(i).getTitle(), result.get(i).getTitle());
            assertEquals(videos.get(i).getFilePath(), result.get(i).getFilePath());
            assertEquals(videos.get(i).getCategory(), result.get(i).getCategory());
        }


    }

}
