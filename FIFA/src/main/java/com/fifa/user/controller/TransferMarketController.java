package com.fifa.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fifa.user.dto.ClubShowDto;
import com.fifa.user.dto.CountryDto;
import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.dto.TransferMarketFindDto;
import com.fifa.user.service.ClubService;
import com.fifa.user.service.ContinentService;
import com.fifa.user.service.CountryService;
import com.fifa.user.service.MemberService;
import com.fifa.user.service.PlayerService;
import com.fifa.user.service.TransferMarketService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TransferMarketController {
	private final TransferMarketService tService;
	private final MemberService memberService;
	private final ClubService clubService;
	private final PlayerService playerService;

	// 이적시장목록 페이지
	@LoginCheck
	@GetMapping("/transfer_list")
	public String transfer_list(Model model, HttpSession session) throws Exception {
		String sUserId = (String) session.getAttribute("sUserId");
		List<TransferMarketFindDto> transferMarketFindDtoList = tService.findAll();
		// 그렇게 List<Country>를 만든다음 model.addAttribute한 다음 find_country_player에서 목록으로
		// 보여준다
		model.addAttribute("transferMarketFindDtoList", transferMarketFindDtoList);
		return "transfermarket/transfer_list";
	}

	// 이적시장 생성홈페이지(form)
	@LoginCheck
	@GetMapping("/create_transfer")
	public String create_transfer(Model model, HttpSession session) throws Exception {

		String sUserId = (String) session.getAttribute("sUserId");
		String role = (String) session.getAttribute("role");

		if (role.equals("Admin")) {
			MemberFindDto memberFindDto = memberService.findMemberByUserName(sUserId);
			Long memberId = memberFindDto.getId();
			model.addAttribute("memberId", memberId);// 이거 action보낼 때 player의 memberId로 쓸꺼임

			List<PlayerFindDto> playerFindDtoList = playerService.findAll();
			model.addAttribute("playerFindDtoList", playerFindDtoList);

			List<ClubShowDto> clubShowDtoList = clubService.findAll();
			model.addAttribute("clubShowDtoList", clubShowDtoList);
			return "transfermarket/transfer_registration";

		} else {
			System.out.println("Admin 계정이 아닙니다.");
			return "/index";
		}
	}

	// 이적시장 생성완료페이지
	@LoginCheck
	@GetMapping("/create_transfer_complete")
	public String create_transfer_complete(Model model, HttpSession session) throws Exception {
		String role = (String) session.getAttribute("role");

		if (role.equals("Admin")) {
			return "transfermarket/transfer_registration_complete";
		}else {
			System.out.println("Admin 계정이 아닙니다.");
			return "/404";
		}
		

	}

}
