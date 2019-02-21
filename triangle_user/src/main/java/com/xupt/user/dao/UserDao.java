package com.xupt.user.dao;

import com.xupt.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author maxu
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
	User findByMobile(String mobile);

	void updatefanscount(int x, String friendid);

	void updatefollowcount(int x, String userid);
}
