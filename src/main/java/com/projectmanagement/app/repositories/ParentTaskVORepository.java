package com.projectmanagement.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projectmanagement.app.entity.ParentTaskVO;

@Transactional
@Repository("parentTaskVORepository")
public interface ParentTaskVORepository extends JpaRepository<ParentTaskVO, Integer>{

		

}
