package com.fifa.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.dto.MemberInsertDto;
import com.fifa.user.dto.MemberLoginDto;
import com.fifa.user.entity.Member;
import com.fifa.user.exception.EmailMismatchException;
import com.fifa.user.exception.member.ExistedMemberByEmailException;
import com.fifa.user.exception.member.ExistedMemberByUserNameException;
import com.fifa.user.exception.member.MemberNotFoundException;
import com.fifa.user.exception.member.PasswordMismatchException;
import com.fifa.user.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberRestController {
	@Autowired
	MemberService memberService;

	 
//회원가입  
	@PostMapping("/join_rest")
	public Map member_join_action(@RequestBody MemberInsertDto memberInsertDto) throws Exception {// @RequestBody는 본문의 요청을 자바 객체로 매핑하는거
		HashMap map = new HashMap<>();
		// MemberResponseDto memberResponseDto =
		// MemberResponseDto.builder().userName(userName).password(password).build();
		int result = 4;

		try {
			System.out.println("일단 RestController접근했어 여기는 result가 3인 경우");
			memberService.insert(memberInsertDto);
		} catch (ExistedMemberByUserNameException e) {
			System.out.println("일단 RestController접근했어 여기는 result가 1인 경우");
			result = 1;
			map.put("result", result);
			map.put("msg", memberInsertDto.getUserName() + "는 사용중인 아이디입니다.");
			return map;
			
		} catch (ExistedMemberByEmailException e) {
			System.out.println("일단 RestController접근했어 여기는 result가 2인 경우");
			result = 2;
			map.put("result", result);
			map.put("msg", memberInsertDto.getEmail() + "는 사용중인 이메일입니다.");
			return map;
		} catch(PasswordMismatchException e) {
			result = 3;
			map.put("result", result);
			map.put("msg", "비밀번호가 일치하지 않습니다.");
			return map;
		}
		map.put("result", result);
		return map;
	}
	
	//REST API에서 로그인을 처리
	//로그인 Post요청
	@PostMapping("/login_rest")
    public ResponseEntity<Map<String, Object>> memberLogin(@RequestBody MemberLoginDto memberLoginDto, HttpSession session) throws Exception {
        Map<String, Object> response = new HashMap();
        
        try {
            memberService.login(memberLoginDto.getUserName(), memberLoginDto.getPassword());
            MemberFindDto loginUser = memberService.findMemberByUserName(memberLoginDto.getUserName());
            session.setAttribute("sUserId", loginUser.getUserName());
            session.setAttribute("role", loginUser.getRole());
            response.put("result", 2); // 로그인 성공 코드
            System.out.println("로그인성공했습니다. try문임");
        } catch (MemberNotFoundException e) {
            response.put("result", 0); // 아이디 존재 안함
            response.put("msg", memberLoginDto.getUserName() + "는 존재하지 않는 아이디입니다.");
        } catch (PasswordMismatchException e) {
            response.put("result", 1); // 패스워드 불일치
            response.put("msg", "비밀번호가 일치하지 않습니다.");
        }
        System.err.println("로그인성공, try-catch문 다 빠져나옴");
        return ResponseEntity.ok(response);
    }
	
	
	
	
	
	
	//회원찾기
	@PostMapping(value = "/findid_rest", produces = "application/json;charset=UTF-8")
	public Map member_findid_action_rest(@RequestBody MemberFindDto memberFindDto, HttpSession session)
			throws Exception {
		HashMap map = new HashMap<>();
		// MemberResponseDto memberResponseDto =
		// MemberResponseDto.builder().userName(userName).password(password).build();
		int result = 1;

		try {
			memberService.getMemberBy(memberFindDto.getEmail());//주어진 이메일을 기반으로 회원을 찾기
		} catch (MemberNotFoundException e) {//회원을 찾을 수 없는 경우
			result = 0;
			map.put("result", result);
			map.put("msg", memberFindDto.getEmail() + "는 등록되지 않은 이메일입니다.");
			return map;
		}

		map.put("result", result);
		return map;
	}
	
	
	
	
	
	
	
	
	//아이디와 비밀번호 일치여부 확인?
	@PostMapping(value = "/findpass_rest", produces = "application/json;charset=UTF-8")
	public Map member_findpass_action_rest(@RequestBody MemberFindDto memberFindDto, HttpSession session)
			throws Exception {
		HashMap map = new HashMap<>();
		// MemberResponseDto memberResponseDto =
		// MemberResponseDto.builder().userName(userName).password(password).build();
		int result = 2;

		try {
			memberService.isMatchEmailByUserName(memberFindDto.getUserName(), memberFindDto.getEmail());
		} catch (MemberNotFoundException e) {//아이디를 찾을 수 없는 경우
			result = 0;
			map.put("result", result);
			map.put("msg", memberFindDto.getUserName() + "는 존재하지 않는 아이디입니다.");
			return map;
		} catch (EmailMismatchException e) {//이메일이 일치하지 않을 경우
			result = 1;
			map.put("result", result);
			map.put("msg", "해당 아이디에 등록된 이메일이 아닙니다.");
			return map;
		}

		map.put("result", result);
		return map;
	}
	//회원탈퇴 rest action
	@LoginCheck
	@DeleteMapping(value = "/remove_action_rest", produces = "application/json;charset=UTF-8")
	public Map delete_action_rest(@RequestBody MemberLoginDto memberLoginDto, HttpSession session) throws Exception {
		HashMap map = new HashMap<>();
		int result = 2;
		try {
			String sUserId = (String) session.getAttribute("sUserId");
			MemberFindDto member = memberService.findMemberByUserName(sUserId);
			if (member.getPassword().equals(memberLoginDto.getPassword())) {
				memberService.delete(member.getId());
				session.invalidate();
			} else {
				throw new PasswordMismatchException("비밀번호가 일치하지않습니다.");
			}
		} catch (PasswordMismatchException e) {
			result = 1;
			map.put("result", result);
			map.put("msg", "비밀번호가 일치하지않습니다.");
			return map;
		}
		map.put("result", result);
		System.out.println("$$$$$$$$$map은 "+map);
		System.out.println("$$$$$$$$$reesult는 "+result);
		return map;
	}
	
	
	
	
	
	
	
}
