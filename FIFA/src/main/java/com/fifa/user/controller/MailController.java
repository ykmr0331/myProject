package com.fifa.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MailController {

	@GetMapping("/mail")
	public String mail(Model model, HttpSession session) throws Exception{
		return "/memberForm";
	}
}

