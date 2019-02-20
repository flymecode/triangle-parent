package com.xupt.qa.gathering.dao;

import com.xupt.qa.gathering.pojo.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author maxu
 */
public interface GatheringDao extends JpaRepository<Gathering,String> {

}
