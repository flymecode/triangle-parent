package com.xupt.qa.article.dao;

import com.xupt.qa.article.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author maxu
 */
public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {
	/**
	 * 审核
	 *
	 * @param id
	 */
	@Modifying
	@Query("UPDATE Article SET state='1' WHERE id=?1")
	void examine(String id);

	/**
	 * 点赞
	 *
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("UPDATE Article a SET thumbup=thumbup+1 WHERE id=?1")
	int updateThumbup(String id);

}
