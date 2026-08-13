package org.example.urlshortener.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "url_mappings")
@NoArgsConstructor
@Getter @Setter
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, unique = true)
    private String shortCode;

    @Column(nullable = false)
    private String originalUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public UrlMapping(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
