package com.fifa.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fifa.user.dao.TransferMarketDao;
import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.dto.TransferMarketFindDto;
import com.fifa.user.dto.TransferMarketInsertDto;
import com.fifa.user.entity.Player;
import com.fifa.user.entity.TransferMarket;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferMarketServiceImpl implements TransferMarketService{
	
	@Autowired
	TransferMarketDao tDao;
	/*
	 * find
	 */
	 public TransferMarketFindDto findTransferMarket(Long id) throws Exception {
		 TransferMarket findTransferMarket = tDao.findTransferMarket(id);
		 TransferMarketFindDto transferMarketFindDto =  TransferMarketFindDto.responseDto(findTransferMarket);
		 return transferMarketFindDto;
	 }
	/*
	 * 모든 이적시장 찾기
	 */
	 public List<TransferMarketFindDto> findAll() {
		List<TransferMarket> findAlltransferMarket = tDao.findAll();
		List<TransferMarketFindDto> findAllDto = new ArrayList<TransferMarketFindDto>();
		for (int i = 0; i < findAlltransferMarket.size(); i++) {
			TransferMarket transferMarket = findAlltransferMarket.get(i);
			TransferMarketFindDto findDto = TransferMarketFindDto.responseDto(transferMarket);
			findAllDto.add(findDto);
		}
		 return findAllDto; 
	 }
	/*
	 * 이적시장 만들기  만들면 
	   선수의 club도 바뀌어야함
	   이전클럽은 이적료만금 자금이 늘어나고 옮겨간 클럽은 이적료만큼 자금 없어짐 
	 * 
	 */
	 public TransferMarketFindDto insert(TransferMarketInsertDto transferMarketInsertDto) throws Exception {
		 TransferMarket transferMarket = tDao.insert(transferMarketInsertDto);
		 TransferMarketFindDto transferMarketFindDto =TransferMarketFindDto.responseDto(transferMarket);
		 return transferMarketFindDto;
	 }
	 
	 
	 
//		@Override	
//		public List<TransferMarketFindDto> findTransferMarketByMember_UserName(String userName) throws Exception {
//			List<TransferMarket> allTransferMarket = tDao.findTransferMarketByMember_UserName(userName);
//			List<TransferMarketFindDto> alltransfermarketFindDto = new ArrayList<TransferMarketFindDto>();
//			for (int i = 0; i < allTransferMarket.size(); i++) {
//				TransferMarket tMarket = allTransferMarket.get(i);
//				if(tMarket.getCreateDate() == null) {
//					throw new Exception("이적 생성날짜가 존재하지 않습니다.");
//				}
//				TransferMarketFindDto transferMarketFindDto = TransferMarketFindDto.responseDto(tMarket);
//				alltransfermarketFindDto.add(transferMarketFindDto);
//			}
//			return alltransfermarketFindDto;
//		}	
	 //이적료 Top3
	 @Override
	 public List<TransferMarketFindDto> findTop3ByOrderByMarketValueDesc() throws Exception {
			List<TransferMarket> TransferMarketList = tDao.findTop3ByOrderByTransferFeeDesc();
			List<TransferMarketFindDto> transferMarketFindDtoList = new ArrayList<>();
			for (int i = 0; i < TransferMarketList.size() ; i++) {
				TransferMarket transferMarket = TransferMarketList.get(i);
				TransferMarketFindDto transferMarketFindDto = TransferMarketFindDto.responseDto(transferMarket);
				transferMarketFindDtoList.add(transferMarketFindDto);
			}
			return transferMarketFindDtoList;
	 }
}
