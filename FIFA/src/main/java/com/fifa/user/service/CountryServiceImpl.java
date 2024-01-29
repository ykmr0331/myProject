package com.fifa.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fifa.user.dao.CountryDao;
import com.fifa.user.dto.ContinentDto;
import com.fifa.user.dto.CountryDto;
import com.fifa.user.entity.Continent;
import com.fifa.user.entity.Country;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{

	@Autowired
	CountryDao countryDao;
	
	@Override
	public CountryDto findCountry(Long countryId) throws Exception {
		Country findCountry = countryDao.findCountry(countryId);
		CountryDto findCountryDto = CountryDto.builder()
												.continentId(findCountry.getContinent().getId())
												.id(countryId)
												.name(findCountry.getName())
												.build();
		return findCountryDto;
	}
	
//	@Override
//	public List<CountryDto> findAllByUserName(String userName) throws Exception {
//		List<Country> allCountry = countryDao.findCountryByMember_UserName(userName);
//		List<CountryDto> allCountryDto = new ArrayList<CountryDto>();
//		for (int i = 0; i < allCountry.size(); i++) {
//			Country country = allCountry.get(i);
//			System.out.println("!@!@!@"+country);
//			CountryDto countryDto = CountryDto.responseDto(country);
//			allCountryDto.add(countryDto);
//		}
//		return allCountryDto;
//	}
	
	@Override
	public CountryDto insert(Country country) throws Exception {
		countryDao.insert(country);
		CountryDto countryDto = CountryDto.builder()
											.id(country.getId())
											.name(country.getName())
											.continentId(country.getContinent().getId())
											.build();
		return countryDto;
	}
	
	
	public List<CountryDto> findAll() {
		List<Country> allCountry = countryDao.findAll();
		List<CountryDto> allCountryDto = new ArrayList<CountryDto>();
		for (int i = 0; i < allCountry.size(); i++) {
			Country country = allCountry.get(i);
//			System.out.println("!@!@!@"+country);
			CountryDto countryDto = CountryDto.responseDto(country);
			allCountryDto.add(countryDto);
		}
		return allCountryDto;
	}

}

