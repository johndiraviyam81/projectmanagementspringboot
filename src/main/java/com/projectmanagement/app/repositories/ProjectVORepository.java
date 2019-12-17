/*
 * 
 */
package com.projectmanagement.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projectmanagement.app.entity.ProjectVO;
import com.projectmanagement.app.entity.UsersVO;

/**
 * The Interface ProjectVORepository.
 */
@Transactional
@Repository("projectVORepository")
public interface ProjectVORepository extends JpaRepository<ProjectVO, Integer> {

	/**
	 * Find by project id.
	 *
	 * @param projectId
	 *            the project id
	 * @return the project VO
	 */
	ProjectVO findByProjectId(long projectId);

	/**
	 * Find by project containing.
	 *
	 * @param projectName
	 *            the project name
	 * @return the list
	 */
	List<ProjectVO> findByProjectContaining(String projectName);

	/**
	 * Find by project containing or project ending with.
	 *
	 * @param projectName the project name
	 * @param projectName1 the project name 1
	 * @return the list
	 */
	List<ProjectVO> findByProjectContainingOrProjectEndingWith(String projectName, String projectName1);

	/**
	 * Find by users VO.
	 *
	 * @param usersVO
	 *            the users VO
	 * @return the list
	 */
	List<ProjectVO> findByUsersVO(UsersVO usersVO);

	/**
	 * Delete by project id.
	 *
	 * @param projectId
	 *            the project id
	 */
	void deleteByProjectId(long projectId);

}
