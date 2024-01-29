package com.fifa.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fifa.user.entity.Continent;
import com.fifa.user.exception.ContinentNotFoundException;
import com.fifa.user.repository.ContinentRepository;
@Repository
/* 	Spring에서 DAO(Data Access Object) 클래스에 붙이는 어노테이션으로, 
	1.데이터 액세스 관련 예외를 일관된 방식으로 처리
	2.DAO 클래스를 Spring Bean으로 등록*/


public class ContinentDaoImpl implements ContinentDao{
	@Autowired
	ContinentRepository continentRepository;
	/*
	 * id로 대륙 하나 찾기
	 */
	public Continent findContinent(Long id) throws Exception, ContinentNotFoundException{
		if(continentRepository.findById(id).isPresent()) {
			return continentRepository.findById(id).get();
		} else {
			throw new ContinentNotFoundException("대륙이 존재하지 않습니다.");
		}
	}
	
	
	public List<Continent> findAll() {//모든 Continent찾기
		return continentRepository.findAll();
	}
	
	
	public Continent insert(Continent continent)  throws Exception{// insert
		return continentRepository.save(continent); 
	}
	
	
	
	// 멤버Id로 대륙 전체 찾기
	//	public List<Continent> findContinentByMember_UserName(String userName) throws Exception {
//		if(userName!=null) {
//			List<Continent> continents= continentRepository.findContinentByMember_UserName(userName);
//			return continents;
//			
//		} else {
//			throw new Exception("일치하는 id가 없습니다.");
//		}
//	}

}
