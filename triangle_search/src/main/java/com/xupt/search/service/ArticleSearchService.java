package com.xupt.search.service;

import com.xupt.common.untils.IdWorker;
import com.xupt.search.dao.ArticleDao;
import com.xupt.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author maxu
 */
@Service
public class ArticleSearchService {

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 增加文章
	 *
	 * @param article
	 */
	public void save(Article article) {
		//article.setId(idWorker.nextId() + "");
		articleDao.save(article);
	}

	public Page<Article> findByTitleLike(String keywords, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page-1, size);
		return articleDao.findByTitleOrContentLike(keywords, keywords, pageRequest);
	}

}
