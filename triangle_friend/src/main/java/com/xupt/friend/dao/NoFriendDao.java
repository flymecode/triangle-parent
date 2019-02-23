package com.xupt.friend.dao;

import com.xupt.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author maxu
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String> {

	NoFriend findByAndFriendidAndUserid(String friendid, String userid);
}
