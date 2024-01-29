package com.fifa.user.dao;

import java.util.List;

import com.fifa.user.dto.TransferMarketInsertDto;
import com.fifa.user.entity.Player;
import com.fifa.user.entity.TransferMarket;

public interface TransferMarketDao {
	//find, findAll, insert, update, delete
	
	/*
	 * find
	 */
	 TransferMarket findTransferMarket(Long id) throws Exception;
	
	/*
	 * 모든 이적시장 찾기
	 */
	 List<TransferMarket> findAll();
	
	
	/*
	 * 이적시장 만들기  만들면 
	   선수의 club도 바뀌어야함
	   이전클럽은 이적료만금 자금이 늘어나고 옮겨간 클럽은 이적료만큼 자금 없어짐 
	 * 
	 */
	 TransferMarket insert(TransferMarketInsertDto transferMarketInsertDto) throws Exception;
	
	
	/*
	 * 멤버 id로 transfermarket 전부찾기
	 */
//	public List<TransferMarket>findTransferMarketByMember_UserName(String userName) throws Exception;
	
	
	 //몸값 TOP3 선수 찾기
	 List<TransferMarket> findTop3ByOrderByTransferFeeDesc() throws Exception;
}
