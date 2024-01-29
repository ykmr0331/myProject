package com.fifa.user.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.dto.MemberInsertDto;
import com.fifa.user.dto.MemberUpdateDto;

import jakarta.transaction.Transactional;
@SpringBootTest
class MemberServiceImplTest {

	@Autowired
	MemberService memberService;
	
	
	@Test
	@Rollback(false)
	@Transactional
	@Disabled
	void testFindMember() throws Exception{
		MemberFindDto findDtoMember = memberService.findMember(2L);
		System.out.println("findDtoMember"+findDtoMember);
	}
	
	
	
	@Test
	@Rollback(false)
	@Transactional
	@Disabled
	void testFindMemberByUserName() throws Exception{
		MemberFindDto findDtoMember = memberService.findMemberByUserName("user2");
		System.out.println("findDtoMember"+findDtoMember);
	}
	
	
	
	@Test
	@Rollback(false)
	@Transactional
	@Disabled
	void testFindAll() throws Exception{
		List<MemberFindDto> findDtoAll = memberService.findAll();
		System.out.println("모든 멤버는 >>"+findDtoAll);
	}

	
	@Test
	@Rollback(false)
	@Transactional
//	@Disabled
	void testInsert() throws Exception{
		MemberInsertDto insertDto  = MemberInsertDto.builder()
													.email("ykmr0331@daum.net")
													.password("a")
													.userName("user1")
													.build();
		
		MemberFindDto insertedMember = memberService.insert(insertDto);
		System.out.println("insertedMember = "+insertedMember);
	}
	
	@Test
	@Rollback(false)
	@Transactional
	@Disabled
	void testUpdate() throws Exception{
		MemberUpdateDto updateDto = MemberUpdateDto.builder().userName("user1").password("aa").build();
		MemberFindDto findDto = memberService.update(updateDto);
		System.out.println("업데이터된 member" + findDto);
	}
	
	
	@Test
	@Rollback(false)
	@Transactional
	@Disabled
	void testDelete() throws Exception{
		MemberFindDto findDto = memberService.delete(3L);
		System.out.println("제거된 멤버"+findDto); 
	}
}
