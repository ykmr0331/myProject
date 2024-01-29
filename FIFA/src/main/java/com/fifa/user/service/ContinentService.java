package com.fifa.user.service;

import java.util.List;

import com.fifa.user.dto.ContinentDto;
import com.fifa.user.entity.Continent;

import jakarta.transaction.Transactional;
@Transactional
public interface ContinentService {
	/*
	public Continent findContinent(Long id) throws Exception;		//id로 Continent 하나 찾기
	public List<Continent> findAll();    					 		//모든 Continent찾기
	public Continent insert(Continent continent) throws Exception;  // insert
	 */
	
	//Player id로 대륙찾기
	ContinentDto findContinent(Long id) throws Exception;
	
	//대륙 전부 조회하기
	List<ContinentDto> findAll();
	
	//대륙 추가하기
	ContinentDto insert(Continent continent) throws Exception;
	
	// 회원 id로 대륙 전체 찾기
//	List<ContinentDto> findContinentListByUserName(String userName) throws Exception ;
	
}
