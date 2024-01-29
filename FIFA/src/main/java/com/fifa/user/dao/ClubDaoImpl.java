package com.fifa.user.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fifa.user.dto.ClubInsertDto;
import com.fifa.user.dto.ClubUpdateDto;
import com.fifa.user.entity.Club;
import com.fifa.user.entity.Country;
import com.fifa.user.entity.Member;
import com.fifa.user.entity.Player;
import com.fifa.user.exception.ClubNotFoundException;
import com.fifa.user.repository.ClubRepository;

@Repository 
/* 	Spring에서 DAO(Data Access Object) 클래스에 붙이는 어노테이션으로, 
        1.데이터 액세스 관련 예외를 일관된 방식으로 처리
        2.DAO 클래스를 Spring Bean으로 등록*/
public class ClubDaoImpl implements ClubDao{
	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private CountryDao countryDao;
	
	/*
	 * id로 클럽찾기
	 */
	public  Club findClub(Long id) throws Exception, ClubNotFoundException {
		if(clubRepository.findById(id).isPresent()) {
			return clubRepository.findById(id).get(); 
		}
		throw new ClubNotFoundException("해당 id로  Club을 찾을 수 없습니다");
	}
	
	//멤버 id로 모든 Player 찾기
//	public List<Club>findClubByMember_UserName(String userName) throws Exception {
//		if(userName!=null) {
//			List<Club> clubs= clubRepository.findClubByMember_UserName(userName);
//			return clubs;
//			
//		} else {
//			throw new Exception("일치하는 id가 없습니다.");
//		}
//	}

	
	
	
	
	
	
	
	/*
	 * 모든 Club찾기
	 */
	public List<Club> findAll() {
		return clubRepository.findAll();
	}

	/*
	 * Club insert하기
	 */
	public Club insert(ClubInsertDto clubInsertDto) throws Exception {
		Club club = Club.toInsertEntnty(clubInsertDto);
		System.out.println("@@@@@@@@@@@DaoImpl에서 toInsertEntity로 반정도만들어진 club엔터티"+club);
//		Member member = memberDao.findMember(clubInsertDto.getMemberId());
//		club.setMember(member);
		Country country = countryDao.findCountry(clubInsertDto.getCountryId());
		club.setCountry(country);
		String img = clubInsertDto.getImg();
		club.setImg(img);
		System.out.println("@@@@@@@@@@@DaoImpl에서 member와 country가 추가된 club엔터티"+club);
		return clubRepository.save(club);
	}
	
	/*
	 * Club 업데이트하기
	 */
	public Club update(ClubUpdateDto clubUpdateDto) throws Exception, ClubNotFoundException{
		Optional<Club> findOptionalClub = clubRepository.findById(clubUpdateDto.getId());// club아이디로 클럽 찾아서
		Club updatedClub = null;
		if(findOptionalClub.isPresent()) {
			Club club = findOptionalClub.get();
			club.setFunds(clubUpdateDto.getFunds());// 이건 바뀔 수 있음
			club.setName(clubUpdateDto.getName());// 법적으로는 가능하지만 현실적으로 할일 없음
			updatedClub=clubRepository.save(club);
			return updatedClub;
		} else {
			throw new ClubNotFoundException("해당 id로  Club을 찾을 수 없습니다");
		}
	}
	
	public Club delete(Long id) throws Exception, ClubNotFoundException{
		Optional<Club> findOptionalClub = clubRepository.findById(id);
		if(findOptionalClub.isPresent()) {
			Club club = findOptionalClub.get();
			clubRepository.delete(club);
			return club;
		}else {
			throw new ClubNotFoundException("해당 id로  Club을 찾을 수 없습니다");
		}
	}
	
	//자금 Top3 클럽
	public List<Club> findTop3ByOrderByFundsValueDesc() throws Exception {
		return clubRepository.findTop3ByOrderByFundsValueDesc();
	}
	
	
}
