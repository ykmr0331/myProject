package com.fifa.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fifa.user.entity.Club;
import com.fifa.user.entity.Continent;

public interface ContinentRepository  extends JpaRepository<Continent, Long> {
//  멤버 id로 대륙 전체찾기
//	List<Continent> findContinentByMember_UserName(String userName);//
}
