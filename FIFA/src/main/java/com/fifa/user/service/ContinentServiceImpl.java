package com.fifa.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fifa.user.dao.ContinentDao;
import com.fifa.user.dto.ContinentDto;
import com.fifa.user.entity.Continent;
import com.fifa.user.exception.PlayerNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContinentServiceImpl implements ContinentService {
	@Autowired
	ContinentDao continentDao;
	
	@Transactional
	public ContinentDto findContinent(Long id) throws Exception {
		Continent continent = continentDao.findContinent(id);
		ContinentDto continentDto = ContinentDto.builder().id(id).name(continent.getName()).build();
		return continentDto; 
	}  
	
	@Transactional
	public List<ContinentDto> findAll() {
		List<Continent> allContinent = continentDao.findAll();
		List<ContinentDto> allContinentDto = new ArrayList<ContinentDto>();
		
		for (int i = 0; i < allContinent.size(); i++) {
			Continent continent = allContinent.get(i);
			System.out.println("!@!@!@"+continent);
			ContinentDto continentDto = ContinentDto.builder()
													.id(continent.getId())
													.name(continent.getName())
													.build();
			System.out.println("#$#$#$"+continentDto);
			allContinentDto.add(continentDto);
			System.out.println("**********"+allContinentDto.get(i));
			
		}
		return allContinentDto;
	}
	
	@Transactional
	public  ContinentDto insert(Continent continent) throws Exception {
		continentDao.insert(continent);
		ContinentDto continentDto = ContinentDto.builder().id(continent.getId()).name(continent.getName()).build();
		return continentDto;
	}
	
	
//	// 회원 id로 대륙 전체 찾기
//	@Transactional
//	public List<ContinentDto> findContinentListByUserName(String userName) throws Exception  {
//		List<ContinentDto> continentDtoList = new ArrayList<>();
//		if(userName == null) {// 회원아이디가 null인경우
//			throw new PlayerNotFoundException("회원아이디가 존재하지 않습니다.");
//		} else {
//			List<Continent> continentList = continentDao.findContinentByMember_UserName(userName);
//			for (Continent continent : continentList) {
//				ContinentDto continentDto = ContinentDto.responseDto(continent);
//				continentDtoList.add(continentDto);
//			}
//		}
//		return continentDtoList;
//	} 
//	
	
	
	
	
	
	
	
}
