package com.fifa.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Slf4j
@Controller
public class MainController {

	@LoginCheck
	@GetMapping("/")
	public String Main(Model model, HttpSession session) {
		System.out.println("세션 role안에 들어있는 role타입은 " + session.getAttribute("role"));
		System.out.println("세션 sUserId안에 들어있는 아이디는 " + session.getAttribute("sUserId"));
		try {
			System.out.println("try 안인듯 1");
			model.addAttribute("title", "");
			System.out.println("try 안인듯 2");
			return "/index";
		} catch (Exception e) {
			model.addAttribute("title", "");
			System.out.println("NullPointException 인듯");
			return "/index";
		} 

	}

//	@LoginCheck
//	@GetMapping("/admin")
//	public String admin(Model model, HttpSession session) throws Exception {
//		System.out.println("세션 role안에 들어있는 role타입은 " + session.getAttribute("role"));
//		System.out.println("세션 sUserId안에 들어있는 아이디는 " + session.getAttribute("sUserId"));
//		String role = (String) session.getAttribute("role");
//		try {
//			if (role.equals("Admin")) {
//				System.out.println("관리자인 경우");
//				model.addAttribute("title", "");
//				return "/admin/admin_main";
//			} else if((role !=null) && !(role.equals("Admin"))) {
//				System.out.println("role은 "+role);
//				System.out.println("관리자는 아닌데 회원인경우");
//				return "/index";
//			}else {
//				System.out.println("비회원인 경우");
//				return "/index";
//			}
//		} catch (NullPointerException e) {
//			System.out.println("비회원인 경우");
//			e.printStackTrace();
//			return "/index";
//		}
//	}

	@LoginCheck
	@GetMapping("/404")
	public String error(Model model, HttpSession session) {
		model.addAttribute("title", "");
		return "/404";
	}

}