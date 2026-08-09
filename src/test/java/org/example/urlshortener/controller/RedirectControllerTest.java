package org.example.urlshortener.controller;

import org.example.urlshortener.exception.UrlNotFoundException;
import org.example.urlshortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@WebMvcTest(RedirectController.class)
public class RedirectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UrlService urlService;

    @Test
    void redirect_existingShortCode_redirectsToOriginalUrl() throws Exception {
        when(urlService.getOriginalUrl("1Z"))
                .thenReturn("https://example.com");

        mockMvc.perform(get("/1Z"))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string(
                        "Location",
                        "https://example.com"
                ));
    }

    @Test
    void redirect_notExistentShortCode_returnsNotFound() throws Exception {
        when(urlService.getOriginalUrl("doesnotexist"))
                .thenThrow(new UrlNotFoundException("doesnotexist"));

        mockMvc.perform(get("/doesnotexist"))
                .andExpect(status().isNotFound());
    }
}
