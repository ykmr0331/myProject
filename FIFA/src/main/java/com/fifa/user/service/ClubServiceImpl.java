package com.fifa.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fifa.user.dao.ClubDao;
import com.fifa.user.dao.CountryDao;
import com.fifa.user.dto.ClubDto;
import com.fifa.user.dto.ClubFindDto;
import com.fifa.user.dto.ClubInsertDto;
import com.fifa.user.dto.ClubShowDto;
import com.fifa.user.dto.ClubUpdateDto;
import com.fifa.user.dto.CountryDto;
import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.entity.Club;
import com.fifa.user.entity.Country;
import com.fifa.user.entity.Player;
import com.fifa.user.repository.ClubRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

	@Autowired
	ClubDao clubDao;
	@Autowired
	CountryService countryService;
	@Autowired
	CountryDao countryDao;
	@Autowired
	ClubRepository clubRepository;

	// Club id로 Club찾기
	@Override
	public ClubFindDto findClub(Long clubId) throws Exception {
		Club findClub = clubDao.findClub(clubId);
		ClubFindDto clubFindDto = ClubFindDto.responseDto(findClub);

		return clubFindDto;
	}

//	//멤버 id로 모든 Club찾기
//	@Override
//	public List<ClubShowDto> findClubByMember_UserName(String userName) throws Exception {
//		List<Club> allClub = clubDao.findClubByMember_UserName(userName);
//		List<ClubShowDto> allClubShowDto= new ArrayList<ClubShowDto>();
//		for (int i = 0; i < allClub.size(); i++) {
//			Club club = allClub.get(i);
//			
//			ClubShowDto clubShowDto= ClubShowDto.responseDto(club);
//			allClubShowDto.add(clubShowDto);
//		}
//		return allClubShowDto;
//	}
//모든 Club찾기
	@Override
	public List<ClubShowDto> findAll() {
		List<Club> allClub = clubDao.findAll();
		List<ClubShowDto> allClubShowDto = new ArrayList<ClubShowDto>();
		for (int i = 0; i < allClub.size(); i++) {
			Club club = allClub.get(i);

			ClubShowDto clubShowDto = ClubShowDto.responseDto(club);
			allClubShowDto.add(clubShowDto);

		}
		return allClubShowDto;
	}

	@Override
	public ClubFindDto insert(ClubInsertDto clubInertDto) throws Exception {

		Club insertedClub = clubDao.insert(clubInertDto);
		System.out.println("@@@@@@@@@@@ServiceImpl에서  완전히 만들어진 club엔터티" + insertedClub);
		ClubFindDto insertedClubDto = ClubFindDto.responseDto(insertedClub);
		System.out.println("@@@@@@@@@@@ServiceImpl에서  완전히 만들어진 club엔터티를 FindDto로 변형한거" + insertedClubDto);
		return insertedClubDto;

	}

	// 클럽 업데이트하기
	@Override
	public ClubUpdateDto update(ClubUpdateDto clubUpdateDto) throws Exception {

		Club updatedClub = clubDao.update(clubUpdateDto);
		ClubUpdateDto updatedClubDto = ClubUpdateDto.responseDto(updatedClub);
		return updatedClubDto;
	}

	@Override
	public ClubFindDto delete(Long id) throws Exception {
		Club deletedClub = clubDao.delete(id);
		ClubFindDto clubFindDto = ClubFindDto.responseDto(deletedClub);
		return clubFindDto;
	}

	@Override
	public List<ClubShowDto> searchClubs(String keyword) throws Exception {
		List<Club> clubs = clubRepository.findByNameContaining(keyword);

		List<ClubShowDto> clubShowDtos = new ArrayList<>();

		if (clubs.isEmpty()) {
			return clubShowDtos;
		}

		for (Club club : clubs) {
			clubShowDtos.add(ClubShowDto.responseDto(club));
		}
		return clubShowDtos;

	}

	// 자금 TOP3 클럽
	@Override
	public List<ClubShowDto> findTop3ByOrderByFundsValueDesc() throws Exception {
		
		
		List<Club> clubList = clubDao.findTop3ByOrderByFundsValueDesc();
		List<ClubShowDto> clubShowDtoList = new ArrayList<>();
		for (int i = 0; i < clubList.size() ; i++) {
			Club club= clubList.get(i);
			ClubShowDto clubShowDto = ClubShowDto.responseDto(club);
			clubShowDtoList.add(clubShowDto);
		}
		return clubShowDtoList;
	}

	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
