package com.alanding.elastic.repository;

import com.alanding.elastic.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 *  @Author      :   AlanDing
 *  @Time        :   2019/11/19 下午5:40
 *  @File        :   BookRepository.java
 *  @Description :
 */

public interface BookRepository extends ElasticsearchRepository<Book, Integer> {

}
