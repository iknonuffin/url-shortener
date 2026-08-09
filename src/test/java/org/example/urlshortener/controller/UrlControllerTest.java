package org.example.urlshortener.controller;

import org.example.urlshortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.endsWith;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UrlController.class)
public class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UrlService urlService;

    @Test
    void shorten_validRequest_returnsShortUrlCorrectly() throws Exception {
        when(urlService.shorten("https://example.com"))
                .thenReturn("1Z");

        mockMvc.perform(post("/shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "url": "https://example.com"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shortUrl").value(endsWith("/1Z")));
    }

    @Test
    void shorten_invalidRequest_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "url": "not-a-url"
                        }
                        """))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(urlService);
    }
}
