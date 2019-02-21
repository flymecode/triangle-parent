package com.xupt.search.controller;

import com.xupt.common.entity.PageResult;
import com.xupt.common.entity.Result;
import com.xupt.common.entity.StatusCode;
import com.xupt.search.pojo.Article;
import com.xupt.search.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author maxu
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {
	@Autowired
	private ArticleSearchService articleService;

	@PostMapping
	public Result save(@RequestBody Article article) {
		articleService.save(article);
		return new Result(true, StatusCode.OK, "添加成功");
	}

	@RequestMapping(value="/search/{keywords}/{page}/{size}",method= RequestMethod.GET)
	public Result findByTitleLike(@PathVariable String keywords,
	                              @PathVariable int page,
	                              @PathVariable int size){
		Page<Article> articlePage = articleService.findByTitleLike(keywords,page,size);
		return new Result(true, StatusCode.OK, "查询成功",
				new PageResult<Article>(articlePage.getTotalElements(), articlePage.getContent()));
	}

}
