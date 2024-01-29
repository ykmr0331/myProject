package com.fifa.user.service;

import java.util.List;

import com.fifa.user.dto.ClubDto;
import com.fifa.user.dto.ClubFindDto;
import com.fifa.user.dto.ClubInsertDto;
import com.fifa.user.dto.ClubShowDto;
import com.fifa.user.dto.ClubUpdateDto;
import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.entity.Club;

import jakarta.transaction.Transactional;

@Transactional
public interface ClubService {
	
	// Club id로 Club찾기   
	ClubFindDto findClub(Long clubId) throws Exception;
	
	//모든 Club찾기
	 List<ClubShowDto> findAll();
	 
	//멤버 id로 모든 Club찾기
//	List<ClubShowDto> findClubByMember_UserName(String userName) throws Exception; 
	
	
	 // insert
	ClubFindDto insert(ClubInsertDto clubInsertDto) throws Exception;

	// update
	ClubUpdateDto update(ClubUpdateDto clubUpdateDto) throws Exception;
	
	
	
	//delete
	ClubFindDto delete(Long id) throws Exception;
	
	//키워드로 Club들 찾기
	List<ClubShowDto> searchClubs(String keyword) throws Exception;
	
	 //자금 TOP3 클럽
	 List<ClubShowDto> findTop3ByOrderByFundsValueDesc() throws Exception;
	
	
	
}
