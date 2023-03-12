package ru.ruszhu.url_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ruszhu.url_shortener.entity.UrlEntity;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
    Optional<UrlEntity> findUrlEntityByShortUrl(String shortUrl);
}
