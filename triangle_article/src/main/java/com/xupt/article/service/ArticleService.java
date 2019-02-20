package com.xupt.article.service;

import com.xupt.article.dao.ArticleDao;
import com.xupt.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author maxu
 */
@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 文章审核
	 *
	 * @param id
	 */
	@Transactional
	public void examine(String id) {
		articleDao.examine(id);
	}

	/**
	 * 点赞
	 *
	 * @param id 文章ID
	 * @return
	 */
	@Transactional
	public int updateThumbup(String id) {
		return articleDao.updateThumbup(id);
	}

	/**
	 * 根据ID查询实体
	 *
	 * @param id
	 * @return
	 */
	@Cacheable(value = "article", key = "'article_'+ #id")
	public Article findById(String id) {
		//从缓存中提取
//		Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
//		// 如果缓存没有则到数据库查询并放入缓存
//		if (article == null) {
//			article = articleDao.findById(id).get();
//			redisTemplate.opsForValue().set("article_" + id, article);
//		}
//		return article;
		return articleDao.findById(id).get();
	}

	/**
	 * 修改
	 *
	 * @param article
	 */
	@CachePut(value = "article", key = "'article_'+ #article.id")
	public void update(Article article) {
		//redisTemplate.delete( "article_" + article.getId() );//删除缓存
		articleDao.save(article);
	}

	/**
	 * 删除
	 *
	 * @param id
	 */
	@CacheEvict(value = "article", key = "'article_'+ #id")
	public void deleteById(String id) {
		//redisTemplate.delete( "article_" + id );//删除缓存
		articleDao.deleteById(id);
	}

	/**
	 * 增加
	 * @param article
	 */
	public void add(Article article) {
		articleDao.save(article);
	}


	public List<Article> findAll() {
		return articleDao.findAll();
	}

	public Page<Article> findSearch(Map searchMap, int page, int size) {
		return null;
	}

	public Page<Article> findSearch(Map searchMap) {
		return null;
	}
}
