package com.fifa.user.service;

import java.util.List;

import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.dto.TransferMarketFindDto;
import com.fifa.user.dto.TransferMarketInsertDto;
import com.fifa.user.entity.TransferMarket;

import jakarta.transaction.Transactional;

@Transactional
public interface TransferMarketService {
	/*
	 * find
	 */
	 TransferMarketFindDto findTransferMarket(Long id) throws Exception;
	/*
	 * 모든 이적시장 찾기
	 */
	 List<TransferMarketFindDto> findAll();
	/*
	 * 이적시장 만들기  만들면 
	   선수의 club도 바뀌어야함
	   이전클럽은 이적료만금 자금이 늘어나고 옮겨간 클럽은 이적료만큼 자금 없어짐 
	 * 
	 */
	 TransferMarketFindDto insert(TransferMarketInsertDto transferMarketInsertDto) throws Exception;
	
	 
	 /*
	  *  멤버id로 transfermarket 전체 찾기 
	  */
//	 List<TransferMarketFindDto> findTransferMarketByMember_UserName(String userName) throws Exception;
	 
	 
	 //이적료 Top3
	 List<TransferMarketFindDto> findTop3ByOrderByMarketValueDesc() throws Exception;
}
