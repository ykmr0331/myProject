package com.fifa.user.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fifa.user.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board , Integer> {
	Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
	
	
	//멤버 id로 BoardList찾기
	Page<Board> findByMemberUserName(String  userName, Pageable pageable);
}

