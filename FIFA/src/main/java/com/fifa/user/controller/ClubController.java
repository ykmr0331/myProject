package com.fifa.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ClubController {
	private final ContinentService continentService;
	private final ClubService clubService;
	private final CountryService countryService;
	private final MemberService memberService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); // 파일 업로드하기 위해 필요한거

	@Resource(name = "fileService") // 파일업로드하기 위해 필요한거
	FileService fileService;

	
	//클러찾기(대륙선택) 페이지
	@LoginCheck
	@GetMapping("/find_continent_club")
	public String find_continent_club(Model model, HttpSession session) throws Exception{
		// 회원이든 아니든 상관없어
			String sUserId = (String)session.getAttribute("sUserId");
			System.out.println("@@@@@@@@@@sUserId는 "+sUserId);
		//모든 클럽 찾기
			List<ContinentDto> continentDtoList = continentService.findAll();
			System.out.println("@@@@@@@@continentDtoList는 "+continentDtoList);
			model.addAttribute("continentDtoList", continentDtoList);
			System.out.println("@@@@@@@@@model은 "+model.getAttribute("continentDtoList"));
			//멤버의 대륙리스트를 모델이 넣었다.
			return "club/find_continent_club";

		
	}
	

	
	
	//클럽찾기(국가선택) 페이지
	@LoginCheck	
	@GetMapping("/find_country_club{continentDtoId}")
	public String find_country_club(@PathVariable String continentDtoId,Model model, HttpSession session) throws Exception{
		
		// 회원이든 아니든 상관없어
			String sUserId = (String)session.getAttribute("sUserId");
			System.out.println("@@@@@@@@@@sUserId는 "+sUserId);
			//모든 국가를 찾아야지
			
			// 일단 List<Country> countryList = new ArrayList<>(); 객체를 새로 만든다
			List<CountryDto> countryIncludeContinent =new ArrayList<>();
			
			List<CountryDto> countryDtoList = countryService.findAll();
			System.out.println("@@@@@@@   @PathVariable의 String타입의 continentDtoId는 "+ continentDtoId);
			System.out.println("@@@@@@@   @PathVariable의 continentDtoId의 타입은 "+ continentDtoId.getClass());
			
			//모든 국가를 for문으로 돌리는데       
			for (CountryDto countryDto : countryDtoList) {
				System.out.println("@@@@@@@   countryDto의 continentId의 타입은 "+String.valueOf(countryDto.getContinentId()).getClass());
				if( String.valueOf(countryDto.getContinentId()).equals(continentDtoId)) {//국가의 continentId가 PathVariable Long continentId 에 속하면
					countryIncludeContinent.add(countryDto); //countryIncludeContinent.add한다.
					System.out.println("#$#$#$#$삽입된 countryDto"+countryDto);
				}  else {
					System.out.println(String.valueOf(countryDto.getContinentId())+"는 "+continentDtoId+"와 일치하지 않습니다.");
				}
			}
			//그렇게 List<Country>를 만든다음 model.addAttribute한 다음 find_country_player에서 목록으로 보여준다
			model.addAttribute("countryIncludeContinent", countryIncludeContinent);
			System.out.println("@@@@@@@@@@countryIncludeContinent는   "+countryIncludeContinent);

			
			return "club/find_country_club";

	}	
	
	
	
	
	//클럽목록페이지
	@LoginCheck
	@GetMapping("/find_club{countryDtoName}")
	public String find_club(@PathVariable String countryDtoName,  Model model, HttpSession session) throws Exception{
		// 회원이든 아니든 상관없어
			String sUserId = (String)session.getAttribute("sUserId");
			System.out.println("@@@@@@@@@@sUserId는 "+sUserId);
		//userName으로 국가목록을 찾아야지.단!!!!!!! 국가는 모두 continentId로 찾은 continent소속이어야한다.
			
			// 일단 List<Country> countryList = new ArrayList<>(); 객체를 새로 만든다
			List<ClubShowDto> clubIncludeCountry =new ArrayList<>();
			
			List<ClubShowDto> clubShowDtoList = clubService.findAll();
			
			//모든 국가를 for문으로 돌리는데       
			for (ClubShowDto clubShowDto : clubShowDtoList) {

				
				if( String.valueOf(clubShowDto.getCountryName()).equals(countryDtoName)) {//국가의 continentId가 PathVariable Long continentId 에 속하면
					clubIncludeCountry.add(clubShowDto); //countryIncludeContinent.add한다.
				}  else {
					System.out.println(String.valueOf(clubShowDto.getCountryName())+"는 "+countryDtoName+"와 일치하지 않습니다.");
				}
			}
			//그렇게 List<Country>를 만든다음 model.addAttribute한 다음 find_country_player에서 목록으로 보여준다
			model.addAttribute("clubShowDtoList", clubIncludeCountry);
			System.out.println("@@@@@@@@@@playerIncludeCountry는   "+clubIncludeCountry);

			
			return "club/find_club";

	}
	
	
	

	
	//검색으로 클럽찾기
	@LoginCheck
	@GetMapping("/club_search")
	public String search(@RequestParam(value = "keyword")String keyword, Model model,HttpSession session)throws Exception {
		// 회원이든 아니든 상관없어
			String sUserId = (String)session.getAttribute("sUserId");
			System.out.println("@@@@@@@@@@sUserId는 "+sUserId);
			System.out.println("%%%%%%%%%%%keyword는 "+keyword);
			
			
			List<ClubShowDto> clubShowDtoList = clubService.searchClubs(keyword);
			model.addAttribute("clubShowDtoList", clubShowDtoList);
			return "club/find_club";
	}
	
	
	
	// 관리자 페이지에서 클럽 생성홈페이지(form)
	@LoginCheck
	@GetMapping("/create_club")
	public String create_club(Model model, HttpSession session) throws Exception {

		String sUserId = (String) session.getAttribute("sUserId");

		if (sUserId != null) {
			MemberFindDto memberFindDto = memberService.findMemberByUserName(sUserId);
			Long memberId = memberFindDto.getId();
			model.addAttribute("memberId", memberId);// 이거 action보낼 때 player의 memberId로 쓸꺼임

			// memberUserName으로 country List랑 Club List가져와야됌 나중에 countryId랑 clubId가져와야돼서 그래

			List<CountryDto> countryDtoList = countryService.findAll();
			model.addAttribute("allCountryDtoList", countryDtoList);

			return "club/create_club";

		} else {
			System.out.println("로그인해주세요");
			return "/index";
		}
	}
	
	
	
	
	// 클럽생성완료페이지
	@LoginCheck
	@GetMapping("/create_club_complete")
	public String create_club_complete(Model model, HttpSession session) throws Exception {
		if (session.getAttribute("sUserId") != null) {// 회원인 경우
			return "club/create_club_complete";
		} else {
			System.out.println("로그인해주세요");
			return "/index";
		}

	}
	
	
	// 클럽사진 업로드하기
	@PostMapping("/register_club/action")
	@LoginCheck	
	public  String clubImgRegisterAction (MultipartHttpServletRequest multiRequest, HttpSession session) {
		try {
			FileUploadResult uploadResult = fileService.uploadFile(multiRequest);
			
			
			// uploadResult에서 필요한 정보를 꺼내서 세션에 저장 또는 다른 작업 수행
	        String realImageRoad = "/images/"+uploadResult.getFileName();
			session.setAttribute("fileName", realImageRoad);
	        System.out.println("@@@@@현재Controller의 사진업로드하기 인데 uploadResult.getFileName()는"+uploadResult.getFileName());
	       
	        String realFileName = (String)session.getAttribute("fileName");
	        System.out.println("@@@@@현재Controller의 사진업로드하기 인데 session fileName에 들어있는 이미지 이름은"+realFileName);
			
			String complete = "업로드 완료, 아래 입력란들을 작성해주세요";
			session.setAttribute("complete", complete);
			 return "redirect:/create_club";
			//다음 메서드 호출
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("#Exception Message : {}", e.getMessage());
			}
			 return "404";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
