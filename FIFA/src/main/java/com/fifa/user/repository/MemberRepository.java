package com.fifa.user.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fifa.user.entity.Board;
import com.fifa.user.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	public Optional<Member> findByUserName(String userName);// 회원 userName으로 회원찾기
	public Optional<Member> findByEmail(String email); // 회원 email로 회원찾기
	
	
	// 페이지 형태로 키워 특정회원 찾기
	Page<Member> findByUserNameContaining(String userName, Pageable pageable);
	
	// 페이지 형태로 모든 회원 찾기
	Page<Member> findAll(Pageable pageable);
	
	
	
	

}
