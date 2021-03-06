package com.xupt.article.controller;

import com.xupt.article.pojo.Comment;
import com.xupt.article.service.CommentService;
import com.xupt.common.entity.Result;
import com.xupt.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author maxu
 */
@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Comment comment) {
		commentService.add(comment);
		return new Result(true, StatusCode.OK, "提交成功 ");
	}

	@RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
	public Result findByArticleid(@PathVariable String articleId) {
		return new Result(true, StatusCode.OK, "查询成功",
				commentService.findByArticleid(articleId));
	}

}

