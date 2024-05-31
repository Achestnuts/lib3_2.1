document.addEventListener('DOMContentLoaded', function() {
    fetch('/api/news')
        .then(response => response.json())
        .then(data => {
            const newsContainer = document.getElementById('news-container');
            data.forEach(news => {
                const newsItem = document.createElement('div');
                newsItem.className = 'news-item';
                newsItem.innerHTML = `
                    <h2>${news.title}</h2>
                    <p>${news.content}</p>
                    <small>${news.source} - ${new Date(news.publishDate).toLocaleString()}</small>
                `;
                newsContainer.appendChild(newsItem);
            });
        });
});
