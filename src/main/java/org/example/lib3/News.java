package org.example.lib3;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String title;
    private String url;
    private LocalDateTime scrapeTime;

    public News() {}

    public News(String category, String title, String url, LocalDateTime scrapeTime) {
        this.category = category;
        this.title = title;
        this.url = url;
        this.scrapeTime = scrapeTime;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getScrapeTime() {
        return scrapeTime;
    }

    public void setScrapeTime(LocalDateTime scrapeTime) {
        this.scrapeTime = scrapeTime;
    }
}
