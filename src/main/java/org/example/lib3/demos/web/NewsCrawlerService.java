package org.example.lib3.demos.web;

import org.example.lib3.News;
import org.example.lib3.NewsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class NewsCrawlerService {

    @Autowired
    private NewsRepository newsRepository;

    private static final String URL = "http://news.baidu.com";
    private static final Logger LOGGER = Logger.getLogger(NewsCrawlerService.class.getName());

    @Scheduled(fixedRate = 30000) // 每30秒执行一次
    public void fetchNews() {
        LOGGER.info("Fetching news from " + URL);
        List<News> newsList = scrapeNews(URL);
        LOGGER.info("Fetched " + newsList.size() + " news articles.");
        saveNewsToDatabase(newsList);
    }

    private List<News> scrapeNews(String url) {
        List<News> newsList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements newsElements = doc.select("a[href]");

            for (Element element : newsElements) {
                String title = element.text().trim();
                String newsUrl = element.attr("href").trim();
                String category = element.parent().attr("class").trim(); // 假设类别信息在父元素的类名中
                LocalDateTime scrapeTime = LocalDateTime.now();

                if (!title.isEmpty() && !newsUrl.isEmpty() && newsUrl.startsWith("http")) {
                    newsList.add(new News(category, title, newsUrl, scrapeTime));
                }
            }
        } catch (IOException e) {
            LOGGER.severe("Error during scraping news: " + e.getMessage());
        }
        return newsList;
    }

    private void saveNewsToDatabase(List<News> newsList) {
        for (News news : newsList) {
            // 检查新闻标题是否已经存在
            if (newsRepository.findByTitle(news.getTitle()).isEmpty()) {
                newsRepository.save(news);
                LOGGER.info("Saved news: " + news.getTitle());
            } else {
                LOGGER.info("News already exists: " + news.getTitle());
            }
        }
    }
}
