package com.fifa.user.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.fifa.user.dao.CountryDao;
import com.fifa.user.dto.ClubDto;
import com.fifa.user.dto.ClubFindDto;
import com.fifa.user.dto.ClubInsertDto;
import com.fifa.user.dto.ClubUpdateDto;
import com.fifa.user.entity.Club;
import com.fifa.user.entity.Country;

import jakarta.transaction.Transactional;
@SpringBootTest
class ClubServiceImplTest {
	@Autowired
	ClubService clubService;
	@Autowired
	CountryDao countryDao;

	@Test
	@Transactional
	@Rollback(false)
//	@Disabled
	void testFindClub() throws Exception {

		ClubFindDto findClubDto = clubService.findClub(24L);
		System.out.println("@@@@@@@@@" + findClubDto);

	}

//	@Test
//	@Transactional
//	@Rollback(false)
//	@Disabled
//	void testFindAll() {
//		List<ClubFindDto> clubFindDtoList = clubService.findAll();
//		System.out.println("@@@@@@@@" + clubFindDtoList);
//	}

//	@Test
//	@Transactional
//	@Rollback(false)
//	@Disabled
//	void testInsert() throws Exception {
//		Long countryId = 150L; // 스페인 국가번호 150 //프랑스:61 //잉글랜드:151  //독일:65
//		Long memberId = 2L;  //주 관리자 번호 2
//		String name = "Valencia CF";
//		Long funds = 5000000000000L;
//		
//		ClubInsertDto clubInsertDto = ClubInsertDto.builder().countryId(countryId).funds(funds).memberId(memberId).name(name).build(); 
//		
//		ClubFindDto clubFindDto = clubService.insert(clubInsertDto);
//		System.out.println("################"+clubFindDto);
//	}

	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void testUpdate() throws Exception{
		ClubUpdateDto clubUpdateDto = ClubUpdateDto.builder()
													.funds(5000L)
													.id(23L)
													.name("changedIbiza")
													.build();
		
		
		ClubUpdateDto updatedClubDto = clubService.update(clubUpdateDto);
		System.out.println("@@@@@@@@@@@@@@@@@"+updatedClubDto);
	}

	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void testDelete() throws Exception{
		ClubFindDto deletedClubDto = clubService.delete(23L);
		System.out.println("@@@@@@@@@@@@@@@"+deletedClubDto);
	}
}
