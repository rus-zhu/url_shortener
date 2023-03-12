package ru.ruszhu.url_shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ShortLinkNotFound extends RuntimeException {
    public ShortLinkNotFound(String message) {
        super(message);
    }
}
