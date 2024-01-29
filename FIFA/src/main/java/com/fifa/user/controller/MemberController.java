package com.fifa.user.controller;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fifa.user.dao.MemberDao;
import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.dto.MemberInsertDto;
import com.fifa.user.dto.MemberLoginDto;
import com.fifa.user.entity.Board;
import com.fifa.user.entity.KakaoProfile;
import com.fifa.user.entity.OAuthToken;
import com.fifa.user.entity.KakaoProfile.KakaoAccount.Profile;
import com.fifa.user.entity.Member;
import com.fifa.user.exception.member.ExistedMemberByUserNameException;
import com.fifa.user.exception.member.MemberNotFoundException;
import com.fifa.user.exception.member.PasswordMismatchException;
import com.fifa.user.repository.MemberRepository;
import com.fifa.user.service.MemberService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	MemberService memberService;
	@Autowired
	MemberDao memberDao;
	@Autowired
	private HttpServletResponse httpServletResponse;

	// 회원가입 페이지
	@GetMapping("/member_join_form")
	public String member_join_form() {
		return "member/member_join_form";
	}

	// 카카오 아이디 중복 확인 페이지
	@GetMapping("/member_already_join_form")
	public String member_already_join_form() {
		return "member/member_already_join_form";
	}

	// 회원가입 완료 페이지
	@GetMapping("/member_join_complete_page")
	public String member_join_complete_page() {
		return "member/member_join_complete_page";
	}

	// 로그인 페이지 이동
	@GetMapping("/member_login_form")
	public String member_login_form() {
		return "member/member_login_form";
	}

	// 아이디 비밀번호 찾기 이동
	@GetMapping("/member_find_password_form")
	public String member_findpassword_form() {
		return "member/member_find_id_password_form";
	}

	// 로그아웃하기
	@LoginCheck
	@GetMapping("/member_logout_action")
	public String member_logout_action(HttpSession session) {
		/************** login check **************/
		/****************************************/
		System.out.println("FFFFFFFFFFFFFFF" + session.getAttribute("sUserId"));
		session.invalidate();

		return "redirect:/";
	}

	// 회원탈퇴페이지 이동하기
	@LoginCheck
	@GetMapping("/member_remove_form")
	public String member_quit_form() {

		return "member/member_remove_form";
	}

	@LoginCheck
	@GetMapping("auth/kakao/callback")
	public @ResponseBody String kakaoCallback(String code, HttpSession session) throws Exception {// Data를 리턴해주는 컨트롤러 함수

		// POST방식으로 key=value 데이터를 요청(카카오쪽으로) //a태그로 전달하는 방식은 무조건 get방식이라서 a태그로는 안됌
		// Retrofit2
		// OkHttp
		// RestTemplate

		RestTemplate rt = new RestTemplate();// http요청을 편하게 할 수 있음

		// HttpHeader오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		// 지금 제공할 http body데이터가 key-value형태의 데이터라는걸 알려주는거다

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "17809101829a6ec785d3fbede878e981");
		params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
		params.add("code", code);

		// HttpHEader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
		// kakaoTokenRequest가 headers인 header값과 params.add()로 바디 데이터데 들어간 바디들의 값을 가지게
		// 된다.

		// Http요청하기-POST방식으로 -그리고 response변수의 응답받음.
		ResponseEntity<String> response = rt.exchange(// rt는 RestTemplate 타입임
				"https://kauth.kakao.com/oauth/token\r\n", HttpMethod.POST, kakaoTokenRequest, String.class);

		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;

		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println("카카오 엑세스 토큰: " + oauthToken.getAccess_token());

		RestTemplate rt2 = new RestTemplate();// http요청을 편하게 할 수 있음

		// HttpHeader오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");// 지금 제공할 http body데이터가
																						// key-value형태의 데이터라는걸 알려주는거다

		// HttpBody 오브젝트 생성필요 없음. 이유가 body데이터가 없으니까

		// HttpHEader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
		// kakaoTokenRequest가 headers인 header값과 params.add()로 바디 데이터데 들어간 바디들의 값을 가지게
		// 된다.

		// Http요청하기-POST방식으로 -그리고 response변수의 응답받음.
		ResponseEntity<String> response2 = rt2.exchange(// rt는 RestTemplate 타입임
				"https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoProfileRequest2, String.class);

		System.out.println(response2.getBody());

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;

		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// User 오브젝트: username, password
		System.out.println("카카오 아이디(번호): " + kakaoProfile.getId());
		System.out.println("카카오 프로필사진 동의확인?: " + kakaoProfile.getKakao_account().getProfile_image_needs_agreement());
		System.out.println("카카오 닉네임 동의확인?: " + kakaoProfile.getKakao_account().getProfile_nickname_needs_agreement());

		System.out.println("피파 유저네임" + kakaoProfile.getId());
		UUID garbagePassword = UUID.randomUUID();
		System.out.println("피파 패스워드" + garbagePassword);

		System.out.println("카카오 아이디 타입" + kakaoProfile.getId().TYPE);

		UUID randomEmail = UUID.randomUUID();
		String uniqueString = randomEmail.toString();// UUID를 문자열로 변환

		Member member = Member.builder().userName(Long.toString(kakaoProfile.getId()))
				.password(garbagePassword.toString()).email(uniqueString).build();
		System.out.println("@@@@@@@@@@@@member는 " + member);

		MemberInsertDto memberInsertDto = MemberInsertDto.responseDto(member);
		System.out.println("@@@@@@@@@@@@memberInsertDto는 " + memberInsertDto);

// 가입자 혹은 비가입자 체크해서 처리		
		if (memberDao.existedMemberByUserName(memberInsertDto.getUserName())) {
			MemberFindDto existedMemberFindDto = memberService.findMemberByUserName(memberInsertDto.getUserName());

			// 이미 가입자인 경우
			System.out.println("@@@@@@이미 존재하는 id가 있습니다.");
			System.out.println("member.getPassword()는(내가 입력한거) " + member.getPassword());
			System.out.println("memberInsertDto.getPassword()는  " + memberInsertDto.getPassword());
			System.out.println("UUID타입 패스워드는(garbagePassword): " + garbagePassword);
			System.out.println("UUID타입 패스워드는(garbagePassword.toString()): " + garbagePassword.toString());
			// 그러면 Login을 해야지

//			boolean loginTrue =memberService.login(memberInsertDto.getUserName(),member.getPassword());
			boolean loginTrue = memberService.login(existedMemberFindDto.getUserName(),
					existedMemberFindDto.getPassword());
			System.out.println("@@@@@@여기까지!@!@!@!@!@!@!@");

			session.setAttribute("sUserId", memberInsertDto.getUserName());
			System.out.println("세션 sUserId안에 들어있는 아이디는 " + session.getAttribute("sUserId"));
			httpServletResponse.sendRedirect("/"); // 메인페이지로 리다이렉트
			return null; // @ResponseBody를 사용하면서 리다이렉션을 수행하기 위해 null을 반환

		} else {
			// 아직 비회원인 경우
			System.out.println("@@@@@@@@@@@@@@@아직 여기는 괜찮음 1번");
			MemberFindDto insertedMember = memberService.insert(memberInsertDto);
			System.out.println("@@@@@@@@@@@@@@@아직 여기는 괜찮음 2번");
			boolean loginTrue = memberService.login(insertedMember.getUserName(), insertedMember.getPassword());
			session.setAttribute("sUserId", member.getUserName());
			System.out.println("세션 sUserId안에 들어있는 아이디는 " + session.getAttribute("sUserId"));
			httpServletResponse.sendRedirect("/"); // 메인페이지로 리다이렉트
			return null; // @ResponseBody를 사용하면서 리다이렉션을 수행하기 위해 null을 반환
		}

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 관리자 페이지에서 회원 목록페이지 가기
	@GetMapping("/admin_member")
	public String admin_member(Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			String searchKeyword, HttpSession session) throws Exception {
		String role = (String) session.getAttribute("role");
		System.out.println("아직 try문 밖임 role은 "+ role);
		try {
			System.out.println("아직 try문 안인데 아직 admin 확인 안함  role은 "+ role);
			if (role.equals("Admin")) {
				Page<Member> list = null;
				List<MemberFindDto> allMemberFindDto = memberService.findAll();
				System.out.println("role이 admin인거 확인했지만 아직 searchKeyword가 null인지 여부 확인 전 searchKeyword는 "+searchKeyword );
				if (searchKeyword != null && !(searchKeyword.equals(""))) {// searchKeyword가 null이 아닐때
					System.out.println("searchKeyword가 null이 아닙니다.");
					System.out.println("searchKeyword는 "+searchKeyword);
					System.out.println(searchKeyword);
					for (int i = 0; i < allMemberFindDto.size(); i++) {
						System.out.println("searchKeyword가 null이 아닐 때 모든 member를 조회중입니다.");
						MemberFindDto memberFindDto = allMemberFindDto.get(i);
						System.out.println(i + "번째 회원은 " + memberFindDto);
						if (memberFindDto.getUserName().equals(searchKeyword)) {
							// 입력한 계정의 아이디와 일치하는 계정이 있다면
							System.out.println("검색어와 일치하는 계정이 있을 경우");
							list = memberService.findByUserNameContaining(searchKeyword, pageable);
							System.out.println("페이지 타입 회원은 " + list + "입니다.");
							break;
						} else {// 입력한 계정의 아이디와 일치하는 계정이 없으면
							System.out.println("검색어와 일치하는 계정이 없습니다.");
							list = null;
							System.out.println("검색어와 일치하는 계정이 없습니다. list는 " + list);
						}
						System.out.println();
						System.out.println();
					}
					
				} else { // searchKeyword가 null일 때
					System.out.println("검색어가 없을 경우 모두 나오게 합니다.");
					list = memberRepository.findAll(pageable);
					System.out.println("검색어가 없을 경우 list는 "+list);
				}

				int nowPage = 0;
				int startPage = 0;
				int endPage = 0;
				if (list != null && !list.isEmpty()) {
					System.out.println("list가 null이 아닌 경우 list는 "+list+"입니다.");
					nowPage = list.getPageable().getPageNumber() + 1;
					startPage = Math.max(nowPage - 4, 1);
					endPage = Math.min(nowPage + 5, list.getTotalPages());
					System.out.println("nowPage는 "+nowPage);
					System.out.println("startPage는 "+startPage);
					System.out.println("endPage는 "+endPage);
				} else {
					// list가 null인 경우, 페이지를 1페이지로 설정
					System.out.println("list가 null인 경우입니다. list는 "+list);
					nowPage = 1;
					startPage = 1;
					endPage = 1;
				}

				model.addAttribute("list", list);
				model.addAttribute("nowPage", nowPage);
				model.addAttribute("startPage", startPage);
				model.addAttribute("endPage", endPage);
				return "/member/admin_member";
			} else {// Admin이 아닌 경우
				return "/index";
			}
		} catch (Exception e) { // role타입이 null인 경우(비회원일 때
			e.printStackTrace();
			return "/404";
		}
	}

	// 관리자 회원 페이지에서 페이지 상세보기
	@GetMapping("/member_admin_view")
	public String member_admin_view(Model model, Long id, HttpSession session) throws Exception {
		String role = (String) session.getAttribute("role");

		try {
			if (role.equals("Admin")) {
				System.out.println("role 타입은 " + role + "입니다.");
				MemberFindDto selectedMemberFindDto = memberService.findMember(id);// 클릭한 게시글임
				Member selectedMember = Member.toFindEntity(selectedMemberFindDto);
				model.addAttribute("member", selectedMember);
				return "member/admin_member_view";
			} else {
				return "/index";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "/404";

		}

	}

}
