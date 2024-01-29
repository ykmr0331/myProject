package com.fifa.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fifa.user.dto.TransferMarketInsertDto;
import com.fifa.user.entity.Club;
import com.fifa.user.entity.Member;
import com.fifa.user.entity.Player;
import com.fifa.user.entity.TransferMarket;
import com.fifa.user.exception.InsufficientFundsException;
import com.fifa.user.exception.TransferMarketNotFoundException;
import com.fifa.user.repository.ClubRepository;
import com.fifa.user.repository.PlayerRepository;
import com.fifa.user.repository.TransferMarketRepository;

/* 	Spring에서 DAO(Data Access Object) 클래스에 붙이는 어노테이션으로, 
1.데이터 액세스 관련 예외를 일관된 방식으로 처리
2.DAO 클래스를 Spring Bean으로 등록*/
@Repository
public class TransferMarketDaoImpl implements TransferMarketDao {
	@Autowired
	MemberDao memberDao;
	@Autowired
	TransferMarketRepository repository;
	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	ClubRepository clubRepository;
	@Autowired
	ClubDao clubDao;
	@Autowired
	PlayerDao playerDao;

	/*
	 * find
	 */
	public TransferMarket findTransferMarket(Long id) throws Exception, TransferMarketNotFoundException {
		if (repository.findById(id).isPresent()) {
			return repository.findById(id).get();
		}
		throw new TransferMarketNotFoundException("이적내역이 없습니다.(TransferMarketDaoImpl)");
	}

	/*
	 * 모든 이적시장 찾기
	 */
	public List<TransferMarket> findAll() {
		return repository.findAll();
	}

	/*
	 * 이적시장 만들기 만들면 선수의 club도 바뀌어야함 이전클럽은 이적료만금 자금이 늘어나고 옮겨간 클럽은 이적료만큼 자금 없어짐
	 * 
	 */
	public TransferMarket insert(TransferMarketInsertDto insertDto) throws Exception, InsufficientFundsException {

		// 나중클럽의 자금 이적료만큼 줄인다.
		Club newClub = clubDao.findClub(insertDto.getNewClubId());// 옮겨간 클럽
		Long newClubFunds = newClub.getFunds();// 새클럽의 자금
		Long transferFee = insertDto.getTransferFee();// 옮겨간 클럽으로부터 받은 이적료
		Long updatedNewClubFunds = newClubFunds - transferFee;// 이적료 지불로 줄어든 새클럽 자금

		if (updatedNewClubFunds < 0) {
			throw new InsufficientFundsException("이적료를 지불할만한 자금이 충분하지 않습니다.");
		}

		newClub.setFunds(updatedNewClubFunds);// 줄어든 새클럽 자금 엔터티에 저장
		clubRepository.save(newClub);// 새클럽 db에 저장

		// 이전 클럽의 자금 이적료만큼 늘리고
		Club previousClub = clubDao.findClub(insertDto.getPreviousClubId());// 이전클럽
		Long previousClubFunds = previousClub.getFunds();// 이전 클럽의 자금

		Long updatedFunds = previousClubFunds + transferFee;// 이적료로 올라간 이전클럽 자금
		previousClub.setFunds(updatedFunds);// 늘어난 자금 엔터티에 저장
		clubRepository.save(previousClub);// 이전클럽 db에 저장

		// 선수의 Club을 바꾸고 db에 저장해야
		Player player = playerDao.findPlayer(insertDto.getPlayerId());
		player.setClub(clubDao.findClub(insertDto.getNewClubId()));
		player.setClubName(newClub.getName());
		playerRepository.save(player);
		// Dto에서 엔터티로 바꿔야지

		TransferMarket transferMarket = TransferMarket.toResponseEntity(insertDto);
		/*
		 * 일단 transferFee만 있으니까 private Long playerId, previousClubId, newClubId 이렇게 세개
		 * 집어넣어야한다.
		 */
		transferMarket.setPlayer(playerDao.findPlayer(insertDto.getPlayerId()));
		transferMarket.setPreviousClub(clubDao.findClub(insertDto.getPreviousClubId()));
		transferMarket.setNewClub(clubDao.findClub(insertDto.getNewClubId()));

		TransferMarket insertedTransferMarket = repository.save(transferMarket);
		return insertedTransferMarket;
	}

//	public List<TransferMarket>findTransferMarketByMember_UserName(String userName) throws Exception {
//		if(userName!=null) {
//			List<TransferMarket> transferMarkets= repository.findTransferMarketByMember_UserName(userName);
//			return transferMarkets;
//			
//		} else {
//			throw new Exception("일치하는 id가 없습니다.");
//		}
//	}

	// 몸값 TOP3 선수 찾기
	public List<TransferMarket> findTop3ByOrderByTransferFeeDesc() throws Exception {
		return repository.findTop3ByOrderByTransferFeeDesc();
	}

}
