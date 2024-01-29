package com.fifa.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fifa.user.dto.ClubFindDto;
import com.fifa.user.dto.ClubInsertDto;
import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.dto.PlayerInsertDto;
import com.fifa.user.service.ClubService;
import com.fifa.user.service.MemberService;
import com.fifa.user.service.PlayerService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/club")
@RequiredArgsConstructor
public class ClubRestController {
	private final MemberService memberService;
	private final ClubService clubService;
	/*
	 * create_club 페이지에서 가져온 데이터로 club생성하기(action)
	 */
	@PostMapping("/create_club_action")
	@LoginCheck
	public Map create_club_action(@RequestBody ClubInsertDto clubInsertDto, Model model, HttpSession session)
			throws Exception {

		HashMap map = new HashMap<>();
		String sUserId = (String) session.getAttribute("sUserId");
		int result = 2;

		if (sUserId != null) { // 회원일 때

			// 일단 여기서는 insert를 해야지
			MemberFindDto memberFindDto = memberService.findMemberByUserName(sUserId);
			Long memberID = memberFindDto.getId();

			String imgRoad = (String)session.getAttribute("fileName");
			System.out.println("@@@@@현재 RestController인데 session fileName에 들어있는 이미지 이름은"+imgRoad);
			clubInsertDto.setImg(imgRoad);
			
			
			System.out.println("clubInsertDto의 name은" + clubInsertDto.getName());
			System.out.println("clubInsertDto의 countryId은" + clubInsertDto.getCountryId());
			System.out.println("clubInsertDto의 funds은" + clubInsertDto.getFunds());

			ClubFindDto newClubFindDto = clubService.insert(clubInsertDto);
			System.out.println("newClubFindDto는 " + newClubFindDto);
			map.put("result", result);
			return map;
		} else {// sUserId==null일 때
			result = 1;
			map.put("result", result);
			map.put("msg","Member의 UserName은 null입니다.");
			return map;
		}

	}
}
