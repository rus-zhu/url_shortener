package ru.ruszhu.url_shortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ruszhu.url_shortener.entity.UrlEntity;
import ru.ruszhu.url_shortener.service.UrlService;

import java.net.URI;
import java.util.Map;

@RestController
public class UrlShortenerController {

    private final UrlService urlService;

    public UrlShortenerController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/urls")
    public Map<String, String> createShortUrl(@RequestBody Map<String, String> requestBody) {
        String originalUrl = requestBody.get("url");
        UrlEntity shortLink = urlService.getShortLinkFromFull(originalUrl);
        return Map.of("shortUrl", shortLink.getShortUrl());
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
        UrlEntity fullLink = urlService.getFullLinkFromShort(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(fullLink.getOriginalUrl()))
                .build();
    }

}
