package com.xupt.friend.dao;

import com.xupt.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author maxu
 */
public interface FriendDao extends JpaRepository<Friend, String> {
	Friend findByUseridAndFriendid(String userid, String friendid);

	@Modifying
	@Query(value = "UPDATE tb_friend SET islike=? WHERE userid = ? AND friendid = ?", nativeQuery = true)
	void updateIslike(String islike, String userid, String friendid);

	@Modifying
	@Query(value = "delete FROM tb_friend WHERE userid = ? AND friendid = ?", nativeQuery = true)
	void deletefriend(String userid, String friendid);
}
