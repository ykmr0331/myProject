package com.fifa.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fifa.user.dto.PlayerInsertDto;
import com.fifa.user.dto.PlayerUpdateDto;
import com.fifa.user.entity.Club;
import com.fifa.user.entity.Country;
import com.fifa.user.entity.Member;
import com.fifa.user.entity.Player;
import com.fifa.user.exception.PlayerNotFoundException;
import com.fifa.user.repository.PlayerRepository;

@Repository
public class PlayerDaoImpl implements PlayerDao {
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private CountryDao countryDao;
	@Autowired
	private ClubDao clubDao;
	@Autowired
	private MemberDao memberDao;

	// id로 Player찾기
	public Player findPlayer(Long id) throws Exception, PlayerNotFoundException {
		if (playerRepository.findById(id).isPresent()) {
			Player findPlayer = playerRepository.findById(id).get();
			return findPlayer;
		}
		throw new PlayerNotFoundException("선수가 존재하지 않습니다.(PlayerDaoImpl)");

	}

	public List<Player> findAll() {
		return playerRepository.findAll();
	}

	// 멤버 id로 모든 Player찾기
//		public List<Player>findPlayerByMember_UserName(String userName) throws Exception {
//			if(userName!=null) {
//				List<Player> players= playerRepository.findPlayerByMember_UserName(userName);
//				return players;
//				
//			} else {
//				throw new Exception("일치하는 id가 없습니다.");
//			}
//		}

	// insert
	public Player insert(PlayerInsertDto playerInsertDto) throws Exception {
		Player player = Player.toResponseEntity(playerInsertDto);
		// countryId, clubId
		Country country = countryDao.findCountry(playerInsertDto.getCountryId());
		Club club = clubDao.findClub(playerInsertDto.getClubId());

		player.setImg(playerInsertDto.getImg());
		player.setClub(club);
		player.setClubName(club.getName());
		player.setCountry(country);
		player.setCountryName(country.getName());
		player.setMarketValue(playerInsertDto.getMarketValue());
		player.setName(playerInsertDto.getName());
		player.setPosition(playerInsertDto.getPosition());
		return playerRepository.save(player);
	};

	// update
	public Player update(PlayerUpdateDto playerUpdateDto) throws Exception {
		Optional<Player> findOptionalPlayer = playerRepository.findById(playerUpdateDto.getId());
		if (findOptionalPlayer.isPresent()) {
			Player player = findOptionalPlayer.get();

			Club playerClub = clubDao.findClub(playerUpdateDto.getClubId());
			Country playerCountry = countryDao.findCountry(playerUpdateDto.getCountryId());
			player.setClub(playerClub);
			player.setCountry(playerCountry);
			player.setMarketValue(playerUpdateDto.getMarketValue());
			player.setPosition(playerUpdateDto.getPosition());
			Player updatedPlayer = playerRepository.save(player);
			return updatedPlayer;
		} else {
			throw new Exception("존재하지 않은 Player입니다.");
		}

	}

	// delete
	public Player delete(Long id) throws Exception, PlayerNotFoundException {
		Optional<Player> findOptionalPlayer = playerRepository.findById(id);
		if (findOptionalPlayer.isPresent()) {
			Player player = findOptionalPlayer.get();
			playerRepository.delete(player);
			return player;
		} else {
			throw new PlayerNotFoundException("선수가 존재하지 않습니다.(PlayerDaoImpl)");
		}
	}

	// 몸값 TOP3 선수 찾기
	public List<Player> findTop3ByOrderByMarketValueDesc() throws Exception {
		List<Player> top3Player = playerRepository.findTop3ByOrderByMarketValueDesc();
		List<Player> realTop3Player = new ArrayList<Player>();
		for (int i = 0; i <3; i++) {
			Player player = top3Player.get(i);
			realTop3Player.add(player);
		}
		
		return top3Player;
	}
}
