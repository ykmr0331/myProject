package com.fifa.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fifa.user.entity.Continent;
import com.fifa.user.entity.Country;
import com.fifa.user.exception.CountryNotFoundException;
import com.fifa.user.repository.CountryRepository;

import jakarta.transaction.Transactional;

@Repository
public class CountryDaoImpl implements CountryDao {
	@Autowired
	CountryRepository countryRepository;

	/*
	 * id로 Country하나 찾기
	 */
	@Transactional
	public Country findCountry(Long id) throws Exception, CountryNotFoundException {
		if (countryRepository.findById(id).isPresent()) {
			return countryRepository.findById(id).get();
		}
		throw new CountryNotFoundException("국가를 찾을 수 없습니다.");
	};
	
	/*
	 * 모든 Country찾기
	 */
	public List<Country> findAll() {//모든 Continent찾기
		return countryRepository.findAll();
	}
	

	/*
	 * 멤버 id로 모든 Country 찾기
	 */
//	@Transactional
//	public List<Country> findCountryByMember_UserName(String userName) throws Exception {
//		if(userName!=null) {
//			List<Country> countries= countryRepository.findCountryByMember_UserName(userName);
//			return countries;
//			
//		} else {
//			throw new Exception("일치하는 id가 없습니다.");
//		}
//	}
	
	
	
	
	/*
	 * Country 추가하기
	 */
	public Country insert(Country country) throws Exception {
		return countryRepository.save(country);

	}
}