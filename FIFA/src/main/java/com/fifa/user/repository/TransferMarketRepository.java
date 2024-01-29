package com.fifa.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fifa.user.entity.Player;
import com.fifa.user.entity.TransferMarket;

public interface TransferMarketRepository  extends JpaRepository<TransferMarket, Long>{
	
	
//  멤버 id로 TransferMarket 전체찾기
//	List<TransferMarket> findTransferMarketByMember_UserName(String userName);
	
	// marketValue를 기준으로 내림차순 정렬하여 상위 3개 조회
    @Query("SELECT t FROM TransferMarket t ORDER BY t.transferFee DESC  LIMIT 3")
    List<TransferMarket> findTop3ByOrderByTransferFeeDesc();
	
}
