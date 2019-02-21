package com.xupt.qa.controller;

import com.xupt.common.entity.PageResult;
import com.xupt.common.entity.Result;
import com.xupt.common.entity.StatusCode;
import com.xupt.qa.pojo.Problem;
import com.xupt.qa.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author maxu
 * @date 2019/2/20
 */
@CrossOrigin
@Controller
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/newlist/{labelId}/{page}/{size}", method = RequestMethod.GET)
    public Result newList(@PathVariable("labelId") String labelId,
                          @PathVariable("page") int page,
                          @PathVariable("size") int size) {
        Page<Problem> pageData = problemService.newlist(labelId, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageData.getTotalElements(), pageData.getContent()));
    }

    @RequestMapping(value = "/waitlist/{labelId}/{page}/{size}", method = RequestMethod.GET)
    public Result waitList(@PathVariable("labelId") String labelId,
                           @PathVariable("page") int page,
                           @PathVariable("size") int size) {
        Page<Problem> pageData = problemService.waitlist(labelId, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageData.getTotalElements(), pageData.getContent()));
    }

    @RequestMapping(value = "/hotlist/{labelId}/{page}/{size}", method = RequestMethod.GET)
    public Result hotlist(@PathVariable("labelId") String labelId,
                          @PathVariable("page") int page,
                          @PathVariable("size") int size) {
        Page<Problem> pageData = problemService.hotlist(labelId, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageData.getTotalElements(), pageData.getContent()));
    }
}
