package com.xupt.qa.service;

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
		problemDao.save(problem);
	}

	public void update(Problem problem) {

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
			public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				return null;
			}
		};
	}
}
