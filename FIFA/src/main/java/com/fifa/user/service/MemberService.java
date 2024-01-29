package com.fifa.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.dto.MemberInsertDto;
import com.fifa.user.dto.MemberUpdateDto;
import com.fifa.user.entity.Board;
import com.fifa.user.entity.Member;
import com.fifa.user.repository.MemberRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface MemberService {
	
	//멤버 아이디로 멤버찾기
	MemberFindDto findMember(Long id) throws Exception;
	
	
	//멤버 userName으로 멤버찾기
	MemberFindDto findMemberByUserName(String  userName) throws Exception;
	
	
	//멤버 전체 찾기
	List<MemberFindDto> findAll();
	
	//멤버 가입하기
	MemberFindDto insert(MemberInsertDto insertDto) throws Exception;
		
	
	
	//멤버 업데이트하기
	public MemberFindDto update(MemberUpdateDto updateDto) throws Exception;
		
	
	//멤버 지우기
	public MemberFindDto delete(Long id) throws Exception;
	
	//멤버 로그인하기
	public boolean login(String userName, String password) throws Exception;

	//멤버 찾기
	public MemberFindDto getMemberBy(String value) throws Exception;

	public boolean isMatchEmailByUserName(String email, String userName) throws Exception;
	
    //관리자에서 페이지로 멤버 List찾기
    Page<Member> memberList(Pageable pageable) throws Exception;
	
	
	//관리자에서 페이지 형태로 특정회원 검색 찾기
    Page<Member> findByUserNameContaining(String userName, Pageable pageable) throws Exception;
	
	
	
	
	
	
	
	
	

}
