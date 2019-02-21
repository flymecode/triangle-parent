package com.xupt.search.dao;

import com.xupt.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author maxu
 */
public interface ArticleDao extends ElasticsearchRepository<Article,String> {
	/**
	 * 检索
	 * @param
	 * @return
	 */
	Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
