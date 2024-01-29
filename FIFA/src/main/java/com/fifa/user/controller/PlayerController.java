package com.fifa.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fifa.user.dto.ClubShowDto;
import com.fifa.user.dto.ContinentDto;
import com.fifa.user.dto.CountryDto;
import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.service.ClubService;
import com.fifa.user.service.ContinentService;
import com.fifa.user.service.CountryService;
import com.fifa.user.service.FileService;
import com.fifa.user.service.FileUploadResult;
import com.fifa.user.service.MemberService;
import com.fifa.user.service.PlayerService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PlayerController {
	private final MemberService memberService;
	private final ContinentService continentService;
	private final CountryService countryService;
	private final PlayerService playerService;
	private final ClubService clubService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); // 파일 업로드하기 위해 필요한거

	@Resource(name = "fileService") // 파일업로드하기 위해 필요한거
	FileService fileService;

	// 선수찾기(대륙선택) 페이지
	@LoginCheck
	@GetMapping("/find_continent_player")
	public String find_continent_player(Model model, HttpSession session) throws Exception {
		// 회원이든 회원 아니든 다 됌

		String sUserId = (String) session.getAttribute("sUserId");
		System.out.println("@@@@@@@@@@sUserId는 " + sUserId);
		// userName으로 continentDtoList찾기
		List<ContinentDto> continentDtoList = continentService.findAll();
		System.out.println("@@@@@@@@continentDtoList는 " + continentDtoList);
		model.addAttribute("continentDtoList", continentDtoList);
		System.out.println("@@@@@@@@@model은 " + model.getAttribute("continentDtoList"));
		// 멤버의 대륙리스트를 모델이 넣었다.
		return "player/find_continent_player";

	}

	// 선수찾기(국가선택) 페이지
	@LoginCheck
	@GetMapping("/find_country_player{continentDtoId}")
	public String find_country_player(@PathVariable String continentDtoId, Model model, HttpSession session)
			throws Exception {

		// 회원이든 회원 아니든 다 됌
		String sUserId = (String) session.getAttribute("sUserId");
		System.out.println("@@@@@@@@@@sUserId는 " + sUserId);
		// userName으로 국가목록을 찾아야지.단!!!!!!! 국가는 모두 continentId로 찾은 continent소속이어야한다.

		// 일단 List<Country> countryList = new ArrayList<>(); 객체를 새로 만든다
		List<CountryDto> countryIncludeContinent = new ArrayList<>();

		List<CountryDto> countryDtoList = countryService.findAll();
		System.out.println("@@@@@@@   @PathVariable의 String타입의 continentDtoId는 " + continentDtoId);
		System.out.println("@@@@@@@   @PathVariable의 continentDtoId의 타입은 " + continentDtoId.getClass());

		// 모든 국가를 for문으로 돌리는데
		for (CountryDto countryDto : countryDtoList) {
			System.out.println(
					"@@@@@@@   countryDto의 continentId의 타입은 " + String.valueOf(countryDto.getContinentId()).getClass());
			if (String.valueOf(countryDto.getContinentId()).equals(continentDtoId)) {// 국가의 continentId가
																						// PathVariable Long
																						// continentId 에 속하면
				countryIncludeContinent.add(countryDto); // countryIncludeContinent.add한다.
				System.out.println("#$#$#$#$삽입된 countryDto" + countryDto);
			} else {
				System.out
						.println(String.valueOf(countryDto.getContinentId()) + "는 " + continentDtoId + "와 일치하지 않습니다.");
			}
		}
		// 그렇게 List<Country>를 만든다음 model.addAttribute한 다음 find_country_player에서 목록으로
		// 보여준다
		model.addAttribute("countryIncludeContinent", countryIncludeContinent);
		System.out.println("@@@@@@@@@@countryIncludeContinent는   " + countryIncludeContinent);

		return "player/find_country_player";

	}

	// 선수목록페이지
	@LoginCheck
	@GetMapping("/find_player{countryDtoId}")
	public String find_player(@PathVariable String countryDtoId, Model model, HttpSession session) throws Exception {

		/// 회원이든 회원 아니든 다 됌
		String sUserId = (String) session.getAttribute("sUserId");
		System.out.println("@@@@@@@@@@sUserId는 " + sUserId);
		// userName으로 국가목록을 찾아야지.단!!!!!!! 국가는 모두 continentId로 찾은 continent소속이어야한다.

		// 일단 List<Country> countryList = new ArrayList<>(); 객체를 새로 만든다
		List<PlayerFindDto> playerIncludeCountry = new ArrayList<>();

		List<PlayerFindDto> playerDtoList = playerService.findAll();

		// 모든 국가를 for문으로 돌리는데
		for (PlayerFindDto playerFindDto : playerDtoList) {

			if (String.valueOf(playerFindDto.getCountryId()).equals(countryDtoId)) {// 국가의 continentId가 PathVariable
																					// Long continentId 에 속하면
				playerIncludeCountry.add(playerFindDto); // countryIncludeContinent.add한다.
				System.out.println("@@@@@@@@@@playerfindDto의 img는 " + playerFindDto.getImg());
				System.out.println("#$#$#$#$삽입된 countryDto" + playerFindDto);
			} else {
				System.out.println(String.valueOf(playerFindDto.getCountryId()) + "는 " + countryDtoId + "와 일치하지 않습니다.");
			}
		}
		// 그렇게 List<Country>를 만든다음 model.addAttribute한 다음 find_country_player에서 목록으로
		// 보여준다
		model.addAttribute("playerFindDtoList", playerIncludeCountry);
		System.out.println("@@@@@@@@@@playerIncludeCountry는   " + playerIncludeCountry);

		return "player/find_player";

	}

	// 검색으로 선수 찾기
	@LoginCheck
	@GetMapping("/player_search")
	public String search(@RequestParam(value = "keyword") String keyword, Model model, HttpSession session)
			throws Exception {

		// 회원이든 회원 아니든 다 됌
		String sUserId = (String) session.getAttribute("sUserId");
		System.out.println("@@@@@@@@@@sUserId는 " + sUserId);
		System.out.println("%%%%%%%%%%%keyword는 " + keyword);
		List<PlayerFindDto> playerFindDtoList = playerService.searchPlayers(keyword);
		model.addAttribute("playerFindDtoList", playerFindDtoList);
		return "player/find_player";
	}

	// 선수 생성홈페이지(form)
	@LoginCheck
	@GetMapping("/create_player")
	public String create_player(Model model, HttpSession session) throws Exception {

		String sUserId = (String) session.getAttribute("sUserId");
		String complete = (String)session.getAttribute("complete");
	
		if (sUserId != null) {
			MemberFindDto memberFindDto = memberService.findMemberByUserName(sUserId);
			Long memberId = memberFindDto.getId();
			model.addAttribute("memberId", memberId);// 이거 action보낼 때 player의 memberId로 쓸꺼임

			// memberUserName으로 country List랑 Club List가져와야됌 나중에 countryId랑 clubId가져와야돼서 그래

			List<CountryDto> allCountryDtoList = countryService.findAll();
			model.addAttribute("allCountryDtoList", allCountryDtoList);

			List<ClubShowDto> clubShowDtoList = clubService.findAll();
			model.addAttribute("clubShowDtoList", clubShowDtoList);

			model.addAttribute("complete", complete); // 업로드하기 누른 후 업로드 완료 나오게하려고
			
		  	String realFileName = (String)session.getAttribute("fileName");
			System.out.println("@@@@@현재Controller의 선수생성홈페이지 인데 session fileName에 들어있는 이미지 이름은"+realFileName);
			model.addAttribute("fileName", realFileName); 
	

			return "player/create_player";

		} else {
			System.out.println("로그인해주세요");
			return "/index";
		}
	}

//position   marketValue  country  club
	// 선수생성완료페이지
	@LoginCheck
	@GetMapping("/create_player_complete")
	public String create_player_complete(Model model, HttpSession session) throws Exception {
		if (session.getAttribute("sUserId") != null) {// 회원인 경우
			
			return "player/create_player_complete";
		} else {
			System.out.println("로그인해주세요");
			return "/index";
		}

	}

	// 사진 업로드하기
	@PostMapping("/register/action")
	@LoginCheck	
	public  String playerImgRegisterAction (MultipartHttpServletRequest multiRequest, HttpSession session) {
		try {
			FileUploadResult uploadResult = fileService.uploadFile(multiRequest);
			System.out.println("uploadResult는 "+uploadResult);
			System.out.println("multiRequest는 "+multiRequest);
			System.out.println("uploadResult는 "+uploadResult);
			
			// uploadResult에서 필요한 정보를 꺼내서 세션에 저장 또는 다른 작업 수행
	        String realImageRoad = "/images/"+uploadResult.getFileName();
	        
			session.setAttribute("fileName", realImageRoad);
	        System.out.println("@@@@@현재Controller의 사진업로드하기 인데 uploadResult.getFileName()는"+uploadResult.getFileName());
	       
	        String realFileName = (String)session.getAttribute("fileName");
	        System.out.println("@@@@@현재Controller의 사진업로드하기 인데 session fileName에 들어있는 이미지 이름은"+realFileName);
	        System.out.println(".확장자를 제외한 파일명은 "+uploadResult.getFileName());
			
			String complete = "업로드 완료, 아래 입력란들을 작성해주세요";
			session.setAttribute("complete", complete);
			 return "redirect:/create_player";
			//다음 메서드 호출
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("#Exception Message : {}", e.getMessage());
			}
			 return "404";
		}
	}

}
