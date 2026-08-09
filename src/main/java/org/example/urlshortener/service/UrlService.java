package org.example.urlshortener.service;

import lombok.RequiredArgsConstructor;
import org.example.urlshortener.entity.UrlMapping;
import org.example.urlshortener.exception.UrlNotFoundException;
import org.example.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final Base62Service base62Service;
    private final UrlRepository urlRepository;

    @Transactional
    public String shorten(String url) {
        UrlMapping urlMapping = urlRepository.save(new UrlMapping(url));

        String shortCode = base62Service.encode(urlMapping.getId());

        urlMapping.setShortCode(shortCode);

        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        UrlMapping urlMapping = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException(shortCode));

        return urlMapping.getOriginalUrl();
    }
}
