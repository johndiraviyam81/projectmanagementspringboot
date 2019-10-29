package com.projectmanagement.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projectmanagement.app.entity.ProjectVO;

@Transactional
@Repository("projectVORepository")
public interface ProjectVORepository extends JpaRepository<ProjectVO, Integer>{

	ProjectVO findByProjectId(long projectId);
	

}
