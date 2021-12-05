package com.example.spaceflightnews.get;

public class Article {
    public String getTitle() {
        return title;
    }

    public String getImageUrl() { return imageUrl; }

    public String getSummary() {
        return summary;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public Article(String title, String imageUrl, String summary, String publishedAt) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.summary = summary;
        this.publishedAt = publishedAt;
    }

    private final String title;
    private final String imageUrl;
    private final String summary;
    private final String publishedAt;
}
