package com.fifa.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fifa.user.entity.Country;
import com.fifa.user.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
//  멤버 id로 Player 전체찾기
//	List<Player> findPlayerByMember_UserName(String userName);
	
//키워드로 player들 찾기
	List<Player> findByNameContaining(String keyword);

	
	// marketValue를 기준으로 내림차순 정렬하여 상위 3개 조회
    @Query("SELECT p FROM Player p ORDER BY p.marketValue DESC LIMIT 3")
    List<Player> findTop3ByOrderByMarketValueDesc();
}


