package org.example.lib3;

import org.example.lib3.News;
import org.example.lib3.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News getNewsById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    public List<News> searchNews(String keyword) {
        return newsRepository.findByTitleContaining(keyword);
    }

    public List<News> getNewsByCategory(String category) {
        return newsRepository.findByCategory(category);
    }

    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
