package com.projectmanagement.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.projectmanagement.app.entity.UsersVO;

@Transactional
@Repository("usersVORepository")
public interface UsersVORepository extends JpaRepository<UsersVO, Integer>{

	UsersVO findByUserId(long userId);

	List<UsersVO> findByFirstNameIn(List<String> userNames);
}
