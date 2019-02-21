package com.xupt.gathering.dao;

import com.xupt.gathering.pojo.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author maxu
 */
public interface GatheringDao extends JpaRepository<Gathering,String> {

}
