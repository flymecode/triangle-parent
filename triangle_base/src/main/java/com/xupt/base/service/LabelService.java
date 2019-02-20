package com.xupt.base.service;

import com.xupt.base.dao.LabelDao;
import com.xupt.base.pojo.Label;
import com.xupt.common.untils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author maxu
 */
@Service
public class LabelService {

	@Autowired
	private LabelDao labelDao;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部标签
	 *
	 * @return
	 */
	public List<Label> findAll() {
		return labelDao.findAll();
	}

	/**
	 * 根据ID查询标签
	 *
	 * @return
	 */
	public Label findById(String id) {
		return labelDao.findById(id).get();
	}

	/**
	 * 增加标签
	 *
	 * @param label
	 */
	public void add(Label label) {
		//设置ID
		label.setId(idWorker.nextId() + "");
		labelDao.save(label);
	}

	/**
	 * 修改标签
	 *
	 * @param label
	 */
	public void update(Label label) {
		labelDao.save(label);
	}

	/**
	 * 删除标签
	 *
	 * @param id
	 */
	public void deleteById(String id) {
		labelDao.deleteById(id);
	}

	/**
	 * 条件查询
	 *
	 * @param searchMap
	 * @return
	 */
	public List<Label> findSearch(Map searchMap) {
		Specification specification = createSpecification(searchMap);
		return labelDao.findAll(specification);

	}

	/**
	 * 分页条件查询
	 *
	 * @param searchMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Label> findSearch(Map searchMap, int page, int size) {
		Specification specification = createSpecification(searchMap);
		Pageable pageable = PageRequest.of(page - 1, size);
		return labelDao.findAll(specification, pageable);
	}

	/**
	 * 构建查询条件
	 *
	 * @param searchMap
	 * @return
	 */
	private Specification<Label> createSpecification(Map searchMap) {
		return new Specification<Label>() {
			@Override
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				if (StringUtils.isNotEmpty((String)searchMap.get("labelname"))) {
					predicateList.add(cb.like(root.get("labelname").as(String.class)
							, "%" + searchMap.get("labelname") + "%"));
				}
				if (StringUtils.isNotEmpty((String)searchMap.get("state"))) {
					predicateList.add(cb.equal(root.get("state").as(String.class)
							, searchMap.get("state")));
				}
				if (StringUtils.isNotEmpty((String)searchMap.get("recommend"))) {
					predicateList.add(cb.equal(root.get("recommend").as(String.class)
							, searchMap.get("recommend")));
				}
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}
