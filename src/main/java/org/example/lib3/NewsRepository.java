package org.example.lib3;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByTitleContaining(String keyword);
    List<News> findByCategory(String category);
    List<News> findByTitle(String title); // 添加这个方法
}
