package com.fifa.user.dao;

import java.util.List;

import com.fifa.user.entity.Continent;

public interface ContinentDao {
	
	public Continent findContinent(Long id) throws Exception;		//id로 Continent 하나 찾기
	public List<Continent> findAll();    					 		//모든 Continent찾기
	public Continent insert(Continent continent) throws Exception;  // insert
	
	// 회원 id로 대륙 전체 찾기
//	List<Continent> findContinentByMember_UserName(String userName) throws Exception;//
	
}
