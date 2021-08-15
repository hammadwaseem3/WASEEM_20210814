package com.company.waseem_20210814.service;

import static com.company.waseem_20210814.mock.ThumbnailDtoMock.getThumbnailDtoListMock;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.company.waseem_20210814.dto.VideoThumbnailDto;
import com.company.waseem_20210814.entity.VideoThumbnail;
import com.company.waseem_20210814.repository.VideoThumbnailRepository;

@RunWith(MockitoJUnitRunner.class)
public class VideoThumbnailServiceTest {

    @Mock
    private VideoThumbnailRepository videoThumbnailRepository;

    @InjectMocks
    private VideoThumbnailService videoThumbnailService;

    @Captor
    ArgumentCaptor<ArrayList<VideoThumbnail>> thumbnailCaptor;

    @Test
    public void save() {
        var mockDto = getThumbnailDtoListMock();
        var videoId = 1;
        videoThumbnailService.save(mockDto, videoId);

        verify(videoThumbnailRepository).saveAll(thumbnailCaptor.capture());
        var thumbnails = thumbnailCaptor.getValue();

        assertEquals(mockDto.size(), thumbnails.size());

        for(int i=0; i<mockDto.size(); i++) {
            assertEquals(mockDto.get(i).getFilePath(), thumbnails.get(i).getFilePath());
            assertEquals(mockDto.get(i).getSize(), thumbnails.get(i).getSize());
        }

    }
}
