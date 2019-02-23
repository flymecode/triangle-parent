package com.xupt.friend.service;

import com.xupt.friend.dao.FriendDao;
import com.xupt.friend.dao.NoFriendDao;
import com.xupt.friend.pojo.Friend;
import com.xupt.friend.pojo.NoFriend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author maxu
 */
@Service
@Slf4j
public class FriendService {
	@Autowired
	private FriendDao friendDao;

	@Autowired
	private NoFriendDao noFriendDao;

	@Transactional(rollbackFor = Exception.class)
	public int addFriend(String myId, String friendId) {
		// 先判断userid到friendid是否有数据，有就是重复添加好友，返回0
		Friend user = friendDao.findByUseridAndFriendid(myId, friendId);
		if (user != null) {
			return 0;
		}
		user = new Friend();
		user.setUserid(myId);
		user.setFriendid(friendId);
		// 判断从friendid到userid是否有数据，如果有，双方的状态都修改为1
		Friend friend = friendDao.findByUseridAndFriendid(friendId, myId);
		if (friend == null) {
			user.setIslike("0");
		} else {
			// TODO 为啥这样会错呢
			// friend.setIslike("1");
			friendDao.updateIslike("1", friendId, myId);
			user.setIslike("1");
		}
		friendDao.save(user);
		return 1;
	}


	public int addNoFriend(String myId, String friendId) {
		NoFriend noFriend = noFriendDao.findByAndFriendidAndUserid(friendId, myId);
		if (noFriend != null) {
			return 0;
		}
		noFriend = new NoFriend();
		noFriend.setFriendid(friendId);
		noFriend.setUserid(myId);
		noFriendDao.save(noFriend);
		return 1;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteFriend(String userId, String friendid) {
		// 删除好友表 userid -> friendid
		friendDao.deletefriend(userId, friendid);
		// 更新 friendid -> userid 的islike = 0
		friendDao.updateIslike("0",friendid,userId);
		// 非好友表中添加userid ->friendid
		NoFriend noFriend = new NoFriend();
		noFriend.setUserid(userId);
		noFriend.setFriendid(friendid);
		noFriendDao.save(noFriend);
	}
}
