package com.alanding.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot默认支持两种技术来和ES交互；
 * １、Jest（默认不生效）
 *    需要导入Jest的工具包(io.searchbox.client.JestClient)
 * ２、SpringData Elasticsearch [ES版本有可能不合适]
 *     版本适配说明在　SpringData官网文档可查到:
 *     https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#preface.versions
 *     如果版本不适配：
 *         １）、换SpringBoot版本
 *         ２）、换ElasticSearch版本
 *    １）、Client 节点信息clusternode; clusterName
 *    ２）、ElasticsearchTemplate 操作 es
 *    ３）、编写一个ElasticsearchRepository的子接口来操作ES;
 */
@SpringBootApplication
public class Springboot03ElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03ElasticApplication.class, args);
    }

}
