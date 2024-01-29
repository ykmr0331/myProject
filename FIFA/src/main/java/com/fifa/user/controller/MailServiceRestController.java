package com.fifa.user.controller;



import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fifa.user.service.RegisterMail;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/restMail")
@RequiredArgsConstructor
public class MailServiceRestController {

	@Autowired
	RegisterMail registerMail;

	
	@PostMapping(value = "/confirm", produces = "application/json;charset=UTF-8")
	public Map mailConfirm(@RequestParam String mail) throws Exception {
		HashMap map = new HashMap<>();
		int result = 3;
		System.out.println("mail의 타입은"+mail.getClass().getName());
		try {
			System.out.println("@@@@@@@@@@@@@@@@ mail은 " + mail); // ykmr0331@naver.com 제대로 나옴
			
			String code = registerMail.sendSimpleMessage(mail);////////////////////////////////////////////////////////////////////////////////
			System.out.println("사용자에게 발송한 인증코드 ==> " + code);
			map.put("code",code);
		} catch (MessagingException e) {
			result = 1;
			map.put("result", result);
			map.put("msg", "MessagingException입니다.");
		} catch (UnsupportedEncodingException e) {
			result = 2;
			map.put("result", result);
			map.put("msg", "UnsupportedEncodingException입니다.");
		}
		
		map.put("result", result);
		return map;

	}
}