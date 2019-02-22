package com.xupt.qa.service;

import com.xupt.common.untils.IdWorker;
import com.xupt.qa.dao.ProblemDao;
import com.xupt.qa.pojo.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
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
 * @date 2019/2/20
 */
@Service
public class ProblemService {

	@Autowired
	private ProblemDao problemDao;

	@Autowired
	private IdWorker idWorker;

	public Page<Problem> newlist(String labelid, int page, int size) {
		Pageable pageable = new QPageRequest(page - 1, size);
		return problemDao.newlist(labelid, pageable);
	}

	public Page<Problem> hotlist(String labelid, int page, int size) {
		Pageable pageable = new QPageRequest(page - 1, size);
		return problemDao.hotlist(labelid, pageable);
	}

	public Page<Problem> waitlist(String labelid, int page, int size) {
		Pageable pageable = new QPageRequest(page - 1, size);
		return problemDao.waitlist(labelid, pageable);
	}

	public Page<Problem> findSearch(Map searchMap, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Specification<Problem> specification = createSpecification(searchMap);
		return problemDao.findAll(specification, pageable);
	}

	public List<Problem> findSearch(Map searchMap) {
		Specification<Problem> specification = createSpecification(searchMap);
		return problemDao.findAll(specification);
	}

	public void add(Problem problem) {
		problem.setId(idWorker.nextId() + "");
		problemDao.save(problem);
	}

	public void update(Problem problem) {
		problemDao.save(problem);
	}

	public void deleteById(String id) {
		problemDao.deleteById(id);
	}

	public Problem findById(String id) {
		return problemDao.findById(id).get();
	}

	public List<Problem> findAll() {
		return problemDao.findAll();
	}

	private Specification<Problem> createSpecification(Map searchMap) {
		return new Specification<Problem>() {
			@Override
			public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				// ID
				if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
					predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
				}
				// 标题
				if (searchMap.get("title") != null && !"".equals(searchMap.get("title"))) {
					predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) searchMap.get("title") + "%"));
				}
				// 内容
				if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))) {
					predicateList.add(cb.like(root.get("content").as(String.class), "%" + (String) searchMap.get("content") + "%"));
				}
				// 用户ID
				if (searchMap.get("userid") != null && !"".equals(searchMap.get("userid"))) {
					predicateList.add(cb.like(root.get("userid").as(String.class), "%" + (String) searchMap.get("userid") + "%"));
				}
				// 昵称
				if (searchMap.get("nickname") != null && !"".equals(searchMap.get("nickname"))) {
					predicateList.add(cb.like(root.get("nickname").as(String.class), "%" + (String) searchMap.get("nickname") + "%"));
				}
				// 是否解决
				if (searchMap.get("solve") != null && !"".equals(searchMap.get("solve"))) {
					predicateList.add(cb.like(root.get("solve").as(String.class), "%" + (String) searchMap.get("solve") + "%"));
				}
				// 回复人昵称
				if (searchMap.get("replyname") != null && !"".equals(searchMap.get("replyname"))) {
					predicateList.add(cb.like(root.get("replyname").as(String.class), "%" + (String) searchMap.get("replyname") + "%"));
				}

				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}
