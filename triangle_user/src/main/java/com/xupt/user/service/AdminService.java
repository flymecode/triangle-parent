package com.xupt.user.service;

import com.xupt.common.untils.IdWorker;
import com.xupt.user.dao.AdminDao;
import com.xupt.user.pojo.Admin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class AdminService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private IdWorker idWorker;

	public Admin login(Admin admin) {
		//先根据用户名查询对象。
		Admin adminLogin = adminDao.findByLoginname(admin.getLoginname());
		//然后拿数据库中的密码和用户输入的密码匹配是否相同。
		if (adminLogin != null && bCryptPasswordEncoder.matches(admin.getPassword(), adminLogin.getPassword())) {
			//保证数据库中的对象中的密码和用户输入的密码是一致的。登录成功
			return adminLogin;
		}
		//登录失败
		return null;

	}

	public void add(Admin admin) {
		admin.setId(idWorker.nextId() + "");
		// 密码加密
		admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		adminDao.save(admin);
	}

	/**
	 * 修改
	 *
	 * @param admin
	 */
	public void update(Admin admin) {
		adminDao.save(admin);
	}

	/**
	 * 删除
	 *
	 * @param id
	 */
	public void deleteById(String id) {
		adminDao.deleteById(id);
	}

	public List<Admin> findAll() {
		return adminDao.findAll();
	}

	/**
	 * 根据ID查询实体
	 *
	 * @param id
	 * @return
	 */
	public Admin findById(String id) {
		return adminDao.findById(id).get();
	}

	/**
	 * 条件查询
	 *
	 * @param searchMap
	 * @return
	 */
	public List<Admin> findSearch(Map searchMap) {
		Specification<Admin> specification = createSpecification(searchMap);
		return adminDao.findAll(specification);
	}

	/**
	 * 条件查询+分页
	 *
	 * @param searchMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Admin> findSearch(Map searchMap, int page, int size) {
		Specification<Admin> specification = createSpecification(searchMap);
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return adminDao.findAll(specification, pageRequest);
	}

	private Specification<Admin> createSpecification(Map searchMap) {
		return new Specification<Admin>() {
			@Override
			public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				if (StringUtils.isNotEmpty((String) searchMap.get("id"))) {
					predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
				}
				// 登陆名称
				if (StringUtils.isNotEmpty((String) searchMap.get("loginname"))) {
					predicateList.add(cb.like(root.get("loginname").as(String.class), "%" + (String) searchMap.get("loginname") + "%"));
				}
				// 密码
				if (StringUtils.isNotEmpty((String) searchMap.get("password"))) {
					predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
				}
				// 状态
				if (StringUtils.isNotEmpty((String) searchMap.get("state"))) {
					predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) searchMap.get("state") + "%"));
				}
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}
