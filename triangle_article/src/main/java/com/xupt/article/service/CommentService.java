package com.xupt.article.service;

import com.xupt.article.dao.CommentDao;
import com.xupt.article.pojo.Comment;
import com.xupt.common.untils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author maxu
 */
@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private IdWorker idWorker;

	public void add(Comment comment) {
		comment.setId(idWorker.nextId() + "");
		commentDao.save(comment);
	}

	public List<Comment> findByArticleid(String articleid) {
		return commentDao.findByArticleid(articleid);
	}
}
