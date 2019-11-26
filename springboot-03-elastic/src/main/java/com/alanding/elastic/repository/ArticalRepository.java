package com.alanding.elastic.repository;

import com.alanding.elastic.bean.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 *  @Author      :   AlanDing
 *  @Time        :   2019/11/19 下午7:41
 *  @File        :   ArticalRepository.java
 *  @Description :
 */

public interface ArticalRepository extends ElasticsearchRepository<Article, Integer> {
}
