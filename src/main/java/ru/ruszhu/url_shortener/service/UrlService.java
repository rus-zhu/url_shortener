package ru.ruszhu.url_shortener.service;

import ru.ruszhu.url_shortener.entity.UrlEntity;

public interface UrlService {
    UrlEntity getShortLinkFromFull(String link);

    UrlEntity getFullLinkFromShort(String shortLink);
}
