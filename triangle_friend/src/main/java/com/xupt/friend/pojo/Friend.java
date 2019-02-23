package com.xupt.friend.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author maxu
 */
@Data
@Entity
@Table(name="tb_friend")
@IdClass(Friend.class)
public class Friend implements Serializable {
	@Id
	private String userid;
	@Id
	private String friendid;

	private String islike;
}
