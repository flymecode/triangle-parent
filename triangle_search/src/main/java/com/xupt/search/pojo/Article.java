package com.xupt.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author maxu
 */
@Data
@Document(indexName = "triangle_article", type = "article")
public class Article implements Serializable {

	@Id
	private String id;
	// 索引，该域是否能够被搜索
	// 是否分词，就表示搜索的时候整体匹配还是单词匹配
	// 是否存储，是否在页面上显示

	@Field(type = FieldType.Auto, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String title;
	private String state;
	@Field(type = FieldType.Auto, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String content;
}
