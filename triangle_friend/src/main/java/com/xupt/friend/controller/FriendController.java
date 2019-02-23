package com.xupt.friend.controller;

import com.xupt.common.entity.Result;
import com.xupt.common.entity.StatusCode;
import com.xupt.friend.client.UserClient;
import com.xupt.friend.service.FriendService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author maxu
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private FriendService friendService;

	@Autowired
	private UserClient userClient;


	@PutMapping("/like/{friendId}/{type}")
	public Result friend(@PathVariable String friendId, @PathVariable String type) {
		// 验证是否登陆,并且拿到当前用户的id
		Claims claims = (Claims) request.getAttribute("user_claims");
		if (claims == null) {
			return new Result(false, StatusCode.LOGINERROR, "权限不足");
		}
		String myId = claims.getId();
		int flag = 0;
		if (StringUtils.isNotEmpty(type)) {
			if (type.equals("1")) {
				flag = friendService.addFriend(myId, friendId);
				userClient.updatefanscountandfollowcount(myId, friendId, 1);
			} else if (type.equals("2")) {
				flag = friendService.addNoFriend(myId, friendId);
			} else {
				return new Result(false, StatusCode.ERROR, "参数异常");
			}
		}

		if (flag == 0) {
			return new Result(false, StatusCode.ERROR, "不能重复添加");
		}
		return new Result(true, StatusCode.OK, "添加成功");
	}

	@RequestMapping(value = "/{friendid}",method = RequestMethod.DELETE)
	public Result deleteFriend(@PathVariable("friendid") String friendid) {
		// 验证是否登陆
		// 验证是否登陆,并且拿到当前用户的id
		Claims claims = (Claims) request.getAttribute("user_claims");
		if (claims == null) {
			return new Result(false, StatusCode.LOGINERROR, "权限不足");
		}
		String userId = claims.getId();
		friendService.deleteFriend(userId, friendid);
		userClient.updatefanscountandfollowcount(userId, friendid, -1);
		return new Result(true,StatusCode.OK,"删除成功");
	}
}
