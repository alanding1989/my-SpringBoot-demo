package com.alanding.elastic.bean;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/19 下午4:01
 *  @File        :  Article.java
 *  @Description :
 */

@Document(indexName = "alanding", type = "text")
public class Article {
    private Integer id;
    private String author;
    private String title;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
