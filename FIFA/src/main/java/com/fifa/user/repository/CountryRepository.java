package com.fifa.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fifa.user.entity.Continent;
import com.fifa.user.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{
//  멤버 id로 국가 전체찾기
//	List<Country> findCountryByMember_UserName(String userName);//
}
