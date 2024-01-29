package com.fifa.user.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fifa.user.dto.MemberInsertDto;
import com.fifa.user.dto.MemberUpdateDto;
import com.fifa.user.entity.Board;
import com.fifa.user.entity.Member;

public interface MemberDao {
	//find insert update delete
	
	//멤버 아이디로 멤버찾기
	public Member findMember(Long id) throws Exception;

	//멤버 userName으로 멤버찾기
	public Member findMemberByUserName(String userName) throws Exception;
	
	//email로 멤버찾기
	public Member findMemberByEmail(String email) throws Exception;
	
	//멤버 전체 찾기
	public List<Member> findAll();
	
	//멤버 가입하기
	public Member insert(Member member) throws Exception;
		
	
	
	//멤버 업데이트하기
	public Member update(MemberUpdateDto updateDto) throws Exception;
		
	
	//멤버 지우기
	public Member delete(Long id) throws Exception;
	
	
	//회원 id로 회원찾기
	public boolean existedMemberByUserName(String userName) throws Exception;//
	
	//회원 email로 회원찾기
	public boolean existedMemberByEmail(String email) throws Exception;//
	
	//  회원의 id로 특정회원 페이지 형태로 찾기찾기
    Page<Member> findByMemberUserName(String userName, Pageable pageable) throws Exception;
	
	
}
