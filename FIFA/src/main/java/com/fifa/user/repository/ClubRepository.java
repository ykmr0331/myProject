package com.fifa.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fifa.user.entity.Club;
import com.fifa.user.entity.Player;

public interface ClubRepository  extends JpaRepository<Club, Long> {
	public Optional<Club> findById(Long id);
	
	//키워드로 Club들 찾기
	List<Club> findByNameContaining(String keyword);
	
	
//  멤버 id로 Club 전체찾기
//	List<Club> findClubByMember_UserName(String userName);
	
	
	// 자금을 기준으로 내림차순 정렬하여  클럽 상위 3개 조회
    @Query("SELECT c FROM Club c ORDER BY c.funds DESC  LIMIT 3")
    List<Club> findTop3ByOrderByFundsValueDesc();
	
    
}
