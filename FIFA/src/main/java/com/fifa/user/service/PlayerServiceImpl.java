package com.fifa.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fifa.user.dao.PlayerDao;
import com.fifa.user.dto.ClubFindDto;
import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.dto.PlayerInsertDto;
import com.fifa.user.dto.PlayerUpdateDto;
import com.fifa.user.entity.Club;
import com.fifa.user.entity.Player;
import com.fifa.user.repository.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService{
	
	@Autowired
	PlayerDao playerDao;
	@Autowired
	PlayerRepository playerRepository;
	
	
	@Override
	public PlayerFindDto findPlayer(Long playerId) throws Exception {
		Player findPlayer = playerDao.findPlayer(playerId);
		PlayerFindDto findPlayerDto = PlayerFindDto.responseDto(findPlayer);
		return findPlayerDto;
	}
	
	
	//모든 Player찾기
	@Override
	public List<PlayerFindDto> findAll() {
		List<Player> allPlayer= playerDao.findAll();
		List<PlayerFindDto> allPlayerFindDto= new ArrayList<PlayerFindDto>();
		for (int i = 0; i < allPlayer.size(); i++) {
			Player player = allPlayer.get(i);
			
			PlayerFindDto playerFindDto = PlayerFindDto.builder()
					.id(player.getId())
					.name(player.getName())
					.countryId(player.getCountry().getId())
					.clubId(player.getClub().getId())
					.position(player.getPosition())
					.marketValue(player.getMarketValue())
					.clubName(player.getClub().getName())
					.countryName(player.getCountry().getName())
					.img(player.getImg())
					.build();
			allPlayerFindDto.add(playerFindDto);
		}
		return allPlayerFindDto;
	}
	
	
	
	
	
	
	
//	@Override	
//	public List<PlayerFindDto> findPlayerByMember_UserName(String userName) throws Exception {
//		List<Player> allPlayer = playerDao.findPlayerByMember_UserName(userName);
//		List<PlayerFindDto> allPlayerFindDto = new ArrayList<PlayerFindDto>();
//		for (int i = 0; i < allPlayer.size(); i++) {
//			Player player = allPlayer.get(i);
//			
//			PlayerFindDto playerFindDto = PlayerFindDto.responseDto(player);
//			allPlayerFindDto.add(playerFindDto);
//		}
//		return allPlayerFindDto;
//	}	
	
	@Override
	public PlayerFindDto insert(PlayerInsertDto playerInsertDto) throws Exception {
		
		Player insertedPlayer = playerDao.insert(playerInsertDto);
		PlayerFindDto insertedPlayerDto = PlayerFindDto.responseDto(insertedPlayer);
		return insertedPlayerDto;
	}
	
	@Override
	public PlayerUpdateDto update(PlayerUpdateDto playerUpdateDto) throws Exception {
		Player updatedPlayer = playerDao.update(playerUpdateDto);
		PlayerUpdateDto updatedPlayerDto = PlayerUpdateDto.responseDto(updatedPlayer);
		return updatedPlayerDto;
	}
	
	@Override
	public PlayerFindDto delete(Long playerId) throws Exception {
		Player deletedPlayer = playerDao.delete(playerId);
		PlayerFindDto deltedPlayerDto = PlayerFindDto.responseDto(deletedPlayer);
		return deltedPlayerDto;
	}


		
	//키워드로 player들 찾기
	@Override
	public List<PlayerFindDto> searchPlayers(String keyword) throws Exception {
		List<Player> players = playerRepository.findByNameContaining(keyword);
		
		List<PlayerFindDto> playerFindDtos= new ArrayList<>();
		
		if(players.isEmpty()) {
			return playerFindDtos;
		}
		
		for (Player player : players) {
			playerFindDtos.add(PlayerFindDto.responseDto(player));
		}
		return playerFindDtos;
		
	}
	
	 //몸값 TOP3 선수 찾기
	@Override
	 public List<PlayerFindDto> findTop3ByOrderByMarketValueDesc() throws Exception {
		
		
		List<Player> playerList = playerDao.findTop3ByOrderByMarketValueDesc();
		List<PlayerFindDto> playerFindDtoList = new ArrayList<>();
		for (int i = 0; i < playerList.size() ; i++) {
			Player player = playerList.get(i);
			PlayerFindDto playerFindDto = PlayerFindDto.responseDto(player);
			playerFindDtoList.add(playerFindDto);
		}
		return playerFindDtoList;
	}


}
