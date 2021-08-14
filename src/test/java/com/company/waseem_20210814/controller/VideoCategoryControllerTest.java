package com.company.waseem_20210814.controller;

import static com.company.waseem_20210814.mock.VideoCategoryMock.getVideoCategoryMock;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.company.waseem_20210814.service.VideoCategoryService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VideoCategoryController.class)
public class VideoCategoryControllerTest {

    @MockBean
    private VideoCategoryService videoCategoryService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllVideoCategories() throws Exception {
        var mockData = getVideoCategoryMock();
        when(videoCategoryService.findAll()).thenReturn(mockData);

        var actions = mockMvc.perform(MockMvcRequestBuilders.get("/video/category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(mockData.size())));

        for (int i=0; i<mockData.size(); i++)
        {
            actions = actions.andExpect(jsonPath("$["+i+"].id").value(mockData.get(i).getId()));
            actions = actions.andExpect(jsonPath("$["+i+"].name").value(mockData.get(i).getName()));
        }
    }
}
