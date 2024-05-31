package org.example.lib3.demos.web;

import org.example.lib3.News;
import org.example.lib3.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public List<News> getNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public News getNewsById(@PathVariable Long id) {
        return newsService.getNewsById(id);
    }

    @GetMapping("/search")
    public List<News> searchNews(@RequestParam String keyword) {
        return newsService.searchNews(keyword);
    }

    @GetMapping("/category")
    public List<News> getNewsByCategory(@RequestParam String category) {
        return newsService.getNewsByCategory(category);
    }

    @PostMapping
    public News createNews(@RequestBody News news) {
        return newsService.saveNews(news);
    }

    @PutMapping("/{id}")
    public News updateNews(@PathVariable Long id, @RequestBody News newsDetails) {
        News news = newsService.getNewsById(id);
        if (news != null) {
            news.setCategory(newsDetails.getCategory());
            news.setTitle(newsDetails.getTitle());
            news.setUrl(newsDetails.getUrl());
            news.setScrapeTime(newsDetails.getScrapeTime());
            return newsService.saveNews(news);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
    }
}
