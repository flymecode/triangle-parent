package com.xupt.qa.service;

import com.xupt.qa.dao.ProblemDao;
import com.xupt.qa.pojo.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

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
}
