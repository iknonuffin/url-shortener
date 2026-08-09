package org.example.urlshortener.service;

import org.example.urlshortener.entity.UrlMapping;
import org.example.urlshortener.exception.UrlNotFoundException;
import org.example.urlshortener.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

    @Mock
    private Base62Service base62Service;

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlService urlService;

    @Test
    void shorten_validUrl_returnsCorrectShortCode() {
        String validUrl = "https://example.com";
        UrlMapping urlMapping = new UrlMapping(validUrl);
        urlMapping.setId(123L);

        when(urlRepository.save(any(UrlMapping.class)))
                .thenReturn(urlMapping);

        when(base62Service.encode(123L))
                .thenReturn("1Z");

        String result = urlService.shorten(validUrl);

        assertEquals("1Z", result);
        assertEquals("1Z", urlMapping.getShortCode());
    }

    @Test
    void getOriginalUrl_validShortCode_returnsCorrectUrl() {
        UrlMapping urlMapping = new UrlMapping("https://example.com");
        urlMapping.setShortCode("1Z");

        when(urlRepository.findByShortCode("1Z"))
                .thenReturn(Optional.of(urlMapping));

        String result = urlService.getOriginalUrl("1Z");

        assertEquals("https://example.com", result);
    }

    @Test
    void getOriginalUrl_nonExistentShortCode_throwsException() {
        when(urlRepository.findByShortCode("doesnotexist"))
                .thenReturn(Optional.empty());

        assertThrows(UrlNotFoundException.class, () -> urlService.getOriginalUrl("doesnotexist"));
    }
}
