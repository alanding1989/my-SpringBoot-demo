package com.alanding.elastic;

import com.alanding.elastic.bean.Article;
import com.alanding.elastic.bean.Book;
import com.alanding.elastic.repository.ArticalRepository;
import com.alanding.elastic.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot03ElasticApplicationTests {

    // jestClient已经废弃
    // @Autowired
    // JestClient jestClient;

    // @Autowired
    // RestHighLevelClient highLevelClient;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ArticalRepository articalRepository;

    // SpringData es
    @Test
    void test02() {
        Book book = new Book();
        bookRepository.index(book);
    }

    @Test
    void test03() {
        Article article = new Article();
        articalRepository.findAll();
    }

    // jest
    // @Test
    // void contextLoads() {
    //     // 1、给Es中索引（保存）一个文档
    //     Article article = new Article();
    //     article.setId(1);
    //     article.setTitle("好消息");
    //     article.setAuthor("zhangsan");
    //     article.setContent("Hello World");
    //
    //     // 构建一个索引功能
    //     Index build = new Builder(article).index("alanding").type("news").build();
    //     try {
    //         jestClient.execute(build);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // // 测试搜索
    // @Test
    // void search() {
    //     // 查询表达式
    //     String json = "+{\n"
    //         + "    \"query\" : {\n"
    //         + "        \"match\" : {\n"
    //         + "            \"content\" : \"Hello\"\n"
    //         + "        }\n"
    //         + "    }\n"
    //         + "}";
    //
    //     // 构建搜索功能
    //     Search build = new Search.Builder(json).addIndex("alanding").addType("news").build();
    //
    //     try {
    //         SearchResult result = jestClient.execute(build);
    //         System.out.println(result);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

}
