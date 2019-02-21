package com.xupt.article.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author maxu
 */
@Data
public class Comment implements Serializable {

	private String id;
	private String articleid;
	private String content;
	private String userid;
	private String parentid;
	private Date publishdate;
}
