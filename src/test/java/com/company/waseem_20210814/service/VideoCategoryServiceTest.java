package com.company.waseem_20210814.service;

import static com.company.waseem_20210814.mock.VideoCategoryMock.getVideoCategoryMock;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.company.waseem_20210814.repository.VideoCategoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class VideoCategoryServiceTest {

    @Mock
    private VideoCategoryRepository videoCategoryRepository;

    @InjectMocks
    private VideoCategoryService videoCategoryService;

    @Test
    public void findAll() {
        var mockData = getVideoCategoryMock();
        when(videoCategoryRepository.findAll()).thenReturn(mockData);

        var result = videoCategoryService.findAll();

        assertEquals(result.size(), mockData.size());
        for(int i=0; i<result.size(); i++) {
            assertEquals(result.get(i).getId(), mockData.get(i).getId());
            assertEquals(result.get(i).getName(), mockData.get(i).getName());
        }
    }

}
