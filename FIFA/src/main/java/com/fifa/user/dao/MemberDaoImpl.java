package com.fifa.user.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.fifa.user.dto.MemberInsertDto;
import com.fifa.user.dto.MemberUpdateDto;
import com.fifa.user.entity.Club;
import com.fifa.user.entity.Member;
import com.fifa.user.exception.ClubNotFoundException;
import com.fifa.user.exception.member.MemberNotFoundException;
import com.fifa.user.repository.MemberRepository;

@Repository
/*
 * Spring에서 DAO(Data Access Object) 클래스에 붙이는 어노테이션으로, 1.데이터 액세스 관련 예외를 일관된 방식으로
 * 처리 2.DAO 클래스를 Spring Bean으로 등록
 */
public class MemberDaoImpl implements MemberDao {

	@Autowired
	MemberRepository repository;

	@Override
	public Member findMember(Long id) throws Exception {
		if (repository.findById(id).isPresent()) {
			return repository.findById(id).get();
		}
		throw new MemberNotFoundException("해당 id로  Member를 찾을 수 없습니다");
	}

	@Override
	public Member findMemberByUserName(String userName) throws Exception {
		Optional<Member> optionalMember = repository.findByUserName(userName);
		if (optionalMember.isPresent()) {
			return optionalMember.get();
		}
		throw new MemberNotFoundException("해당 userName으로  Member를 찾을 수 없습니다");
	}

	@Override
	// email로 멤버찾기
	public Member findMemberByEmail(String email) throws Exception {
		System.out.println("findMemberByEmail에서 email은 "+email);
		if (email.contains("@")) {
			if (repository.findByEmail(email).isPresent()) {// Optional.isPresent() 메서드는 Optional 객체 안에 값이 존재하는지 확인합니다.
															// 값이 존재하면 true를 반환하고, 값이 없으면 false를 반환합니다.
				Member member =  repository.findByEmail(email).get();// get() 메서드를 사용하여 실제 값을 가져옵니다
				return member;
			} else {
				throw new MemberNotFoundException("해당 이메일로 찾을 수 없습니다");
			}
			
		} else {
			throw new Exception("이메일 형식이 아닙니다.");
		}
	}

	@Override
	public List<Member> findAll() {
		return repository.findAll();
	}

	@Override
	public Member insert(Member member) throws Exception {
		try {
			return repository.save(member);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Member update(MemberUpdateDto updateDto) throws Exception {
		Optional<Member> optionalMember = repository.findByUserName(updateDto.getUserName());
		if (optionalMember.isPresent()) {
			Member findMember = optionalMember.get();
			findMember.setPassword(updateDto.getPassword());
			Member updatedMember = repository.save(findMember);
			return updatedMember;
		}
		throw new MemberNotFoundException("해당 userName으로  Member를 찾을 수 없습니다");
	}

	@Override
	public Member delete(Long id) throws Exception {
		Optional<Member> optionalMember = repository.findById(id);
		if (optionalMember.isPresent()) {
			Member member = optionalMember.get();
			repository.delete(member);
			return member;
		} else {
			throw new MemberNotFoundException("해당 id로  멤버를 찾을 수 없습니다");
		}
	}

	public boolean existedMemberByUserName(String userName) throws Exception {// UserName으로 Member가 존재하는지 확인하는거
		if (repository.findByUserName(userName).isPresent()) {
			return true;
		}
		return false;
	}

	public boolean existedMemberByEmail(String email) throws Exception {
		if (repository.findByEmail(email).isPresent()) {
			return true;
		}
		return false;
	}

	//  회원의 id로 특정회원 페이지 형태로 찾기찾기
    public Page<Member> findByMemberUserName(String userName, Pageable pageable) throws Exception {
    	return repository.findByUserNameContaining(userName, pageable);
    }
	
	
	
	
	
}
