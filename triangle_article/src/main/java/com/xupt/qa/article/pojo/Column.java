package com.xupt.qa.article.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Data
@Table(name="tb_column")
public class Column implements Serializable{

	@Id
	private String id;//ID
	private String name;//专栏名称
	private String summary;//专栏简介
	private String userid;//用户ID
	private Date createtime;//申请日期
	private Date checktime;//审核日期
	private String state;//状态

}
