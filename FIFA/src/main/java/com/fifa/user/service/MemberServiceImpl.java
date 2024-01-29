package com.fifa.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fifa.user.dao.ClubDao;
import com.fifa.user.dao.MemberDao;
import com.fifa.user.dao.PlayerDao;
import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.dto.MemberInsertDto;
import com.fifa.user.dto.MemberUpdateDto;
import com.fifa.user.entity.Member;
import com.fifa.user.entity.Player;
import com.fifa.user.exception.EmailMismatchException;
import com.fifa.user.exception.member.ExistedMemberByEmailException;
import com.fifa.user.exception.member.ExistedMemberByUserNameException;
import com.fifa.user.exception.member.MemberNotFoundException;
import com.fifa.user.exception.member.PasswordMismatchException;
import com.fifa.user.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl  implements MemberService{
	@Autowired
	MemberDao memberDao;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	PlayerDao playerDao;
	@Autowired
	ClubDao clubDao;

	@Override
	public MemberFindDto findMember(Long id) throws Exception {
		Member findMember = memberDao.findMember(id);
		MemberFindDto memberFindDto = MemberFindDto.responseDto(findMember);
		return memberFindDto;
	}
	
	//멤버 userName으로 멤버찾기
	@Override
	public MemberFindDto findMemberByUserName(String  userName) throws Exception {
		Member findMember = memberDao.findMemberByUserName(userName);
		MemberFindDto memberFindDto = MemberFindDto.responseDto(findMember);
		return memberFindDto;
	}
	
	
	@Override
	public List<MemberFindDto> findAll() {
		List<Member> memberList = memberDao.findAll();
		List<MemberFindDto> findDtoList = new ArrayList<MemberFindDto>();
		for (int i = 0; i < memberList.size(); i++) {
			Member member = memberList.get(i);
			MemberFindDto findDto = MemberFindDto.responseDto(member);
			findDtoList.add(findDto);
		}
		return findDtoList;
	}
	
	@Override
	public MemberFindDto insert(MemberInsertDto insertDto) throws Exception {
		if(memberDao.existedMemberByUserName(insertDto.getUserName())) {
			 throw new ExistedMemberByUserNameException(insertDto.getUserName() + " 는 이미 존재하는 아이디 입니다.");
		}else if(memberDao.existedMemberByEmail(insertDto.getEmail())) {
			 throw new ExistedMemberByEmailException(insertDto.getEmail() + " 는 이미 존재하는 email 입니다.");
		}
		Member member = Member.toInsertEntity(insertDto);
		System.out.println("insertedDto를 엔터티로 변환한 거임"+member);
		Member insertedMember = memberDao.insert(member);
		MemberFindDto findDto = MemberFindDto.responseDto(insertedMember);
		return findDto;
	}
	
	
	@Override
	public MemberFindDto update(MemberUpdateDto updateDto) throws Exception {
		Member member = memberDao.update(updateDto);
		MemberFindDto findDto = MemberFindDto.responseDto(member);
		
		return findDto;
	}
	
	@Override
		public MemberFindDto delete(Long id) throws Exception {
//			//선수 , 클럽 , 국가 , 대륙 순서로 지우면 된다.
//			Member member = memberDao.findMember(id);
//			
//			List<Player> players = playerDao.findPlayerByMember_UserName(member.getUserName());
//			for (Player player : players) {
//				playerDao.delete(player.getId());
//			}
			
//////////////////////////////////////////////////////////////////			
			Member member = memberDao.delete(id);
			MemberFindDto findDto = MemberFindDto.responseDto(member);
			return findDto;
		}
	
	@Override
    public boolean login(String userName, String password) throws Exception {
        // 로그인 로직
        Optional<Member> findOptionalMember = memberRepository.findByUserName(userName);
        if (findOptionalMember.isEmpty()) {
            throw new MemberNotFoundException(userName + " 는 존재하지않는 아이디입니다.");
        } else if (findOptionalMember.isPresent()) {
            if (password.equals(findOptionalMember.get().getPassword())) {
            	System.out.println("비밀번호가 일치합니다.");
            } else {
            	System.out.println("내가 입력한 password는 "+ password);
            	System.out.println("실제 password는  "+ findOptionalMember.get().getPassword());
//            	System.out.println("실제 패스워드 타입은 "+findOptionalMember.get().getPassword());
                throw new PasswordMismatchException("비밀번호가 일치하지않습니다.");
            }
        }
        System.out.println("로그인에 성공했습니다.");
        return true;
    }
	
	
	
	//멤버 찾기
    public MemberFindDto getMemberBy(String value) throws Exception {
        // DAO를 통해 특정 회원 정보 조회 후 MemberResponseDto로 변환하여 반환
        return MemberFindDto.responseDto(memberDao.findMemberByEmail(value));
    }
    
    
	public boolean isMatchEmailByUserName(String email, String userName) throws Exception {
		//이메일로 멤버찾고 username으로 멤버 찾아서 멤버 id같으면 true 아니면 false
		Member emailMember = memberDao.findMemberByEmail(email);
		Member userNameMember =memberDao.findMemberByUserName(userName);
		
		Long emailMemberId = emailMember.getId(); 
		Long userNameMemberId = userNameMember.getId();
		if(emailMemberId == userNameMemberId ) {
			return true;
		} else {
			return false;
		}
	}

    //관리자 페이지에서 멤버 전체 찾기
    public Page<Member> memberList(Pageable pageable) throws Exception {
    	return memberRepository.findAll(pageable);
    }

	//관리자에서 페이지 형태로 특정회원 검색 찾기
    public Page<Member> findByUserNameContaining(String userName, Pageable pageable) throws Exception {
    	return memberDao.findByMemberUserName(userName, pageable);
    }
	
	
	
	
	
}
