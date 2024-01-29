package com.fifa.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fifa.user.dto.ClubFindDto;
import com.fifa.user.dto.ClubShowDto;
import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.dto.TransferMarketFindDto;
import com.fifa.user.service.ClubService;
import com.fifa.user.service.PlayerService;
import com.fifa.user.service.TransferMarketService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	@Autowired
	private PlayerService playerService;
	@Autowired
	private ClubService clubService;
	@Autowired
	private TransferMarketService tService;
	
	@LoginCheck
	@GetMapping("/admin")
	public String admin(Model model, HttpSession session) throws Exception {
		System.out.println("세션 role안에 들어있는 role타입은 " + session.getAttribute("role"));
		System.out.println("세션 sUserId안에 들어있는 아이디는 " + session.getAttribute("sUserId"));
		String role = (String) session.getAttribute("role");
		try {
			if (role.equals("Admin")) {
				System.out.println("관리자인 경우");
				
				
				
				List<PlayerFindDto> top3Player = playerService.findTop3ByOrderByMarketValueDesc();
				model.addAttribute("top3Player",top3Player );
				model.addAttribute("title", "");
				
				List<ClubShowDto> top3Club= clubService.findTop3ByOrderByFundsValueDesc();
				model.addAttribute("top3Club",top3Club );
				model.addAttribute("title", "");
				
				List<TransferMarketFindDto> top3TransferMarket= tService.findTop3ByOrderByMarketValueDesc();
						
				model.addAttribute("top3TransferMarket",top3TransferMarket );
				model.addAttribute("title", "");
				
				
				return "/admin/admin_main";
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			} else if((role !=null) && !(role.equals("Admin"))) {
				System.out.println("role은 "+role);
				System.out.println("관리자는 아닌데 회원인경우");
				return "/index";
			}else {
				System.out.println("비회원인 경우");
				return "/index";
			}
		} catch (NullPointerException e) {
			System.out.println("비회원인 경우");
			e.printStackTrace();
			return "/index";
		}
	}
	
}
