package com.fifa.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fifa.user.entity.Member;
@SpringBootTest
class MemberDaoImplTest {
	@Autowired
	MemberDao memberDao;

	@Test
	@Rollback(false)
	@Disabled
	@Transactional
	void testFindMember() throws Exception{
		Member findMember = memberDao.findMember(null);
		System.out.println("Member = "+ findMember);
	}

	@Test
	@Rollback(false)
	@Disabled
	@Transactional
	void testFindAll() {
		List<Member> findAllMember = memberDao.findAll();
		System.out.println("allMember "+findAllMember);
		
	}
	
	@Test
	@Rollback(false)
	@Disabled
	@Transactional
	void testInsert() throws Exception{
		Member member = Member.builder()
								.password("a")
								.userName("user1")
								.email("ykmr0331@naver.com")
								.build();
		Member insertedMember = memberDao.insert(member);
		System.out.println("insertedMember = "+insertedMember);
		
		
	}
	
}
