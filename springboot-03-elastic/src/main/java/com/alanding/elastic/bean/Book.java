package com.alanding.elastic.bean;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/19 下午5:41
 *  @File        :  Book.java
 *  @Description :
 */


@Document(indexName = "bookstore", type = "book")
public class Book {
    private Integer id;
    private String bookName;
    private String author;

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", bookName='" + bookName + '\'' +
            ", author='" + author + '\'' +
            '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
