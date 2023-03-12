package ru.ruszhu.url_shortener.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.ruszhu.url_shortener.entity.UrlEntity;
import ru.ruszhu.url_shortener.exception.ShortLinkNotFound;
import ru.ruszhu.url_shortener.repository.UrlRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlServiceImpl implements UrlService {
    private final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);
    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public UrlEntity getShortLinkFromFull(String link) {
        String shortLink = linkShortener(link);
        UrlEntity newUrl = UrlEntity.builder()
                .originalUrl(link)
                .shortUrl(shortLink)
                .build();
        urlRepository.save(newUrl);
       logger.info("Short link from " + link + " to " + shortLink + " saved");
        return newUrl;
    }

    @Override
    public UrlEntity getFullLinkFromShort(String shortLink) {
        Optional<UrlEntity> optional = urlRepository.findUrlEntityByShortUrl(shortLink);
        if (optional.isEmpty()) {
            logger.info(shortLink + " not found in database");
            throw new ShortLinkNotFound(shortLink + " not found! Check short link!");
        }
        return optional.get();
    }

    private String linkShortener(String link) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        String alfSeq = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int len = 10;
        for (int i = 0; i < len; i++) {
            int number = random.nextInt(62);
            sb.append(alfSeq.charAt(number));
        }
        logger.info("Short link from " + link + " generated to -> " + sb);
        return sb.toString();
    }

}
