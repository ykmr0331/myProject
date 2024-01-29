package com.fifa.user.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fifa.user.service.ClubService;
import com.fifa.user.service.ContinentService;
import com.fifa.user.service.CountryService;
import com.fifa.user.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class videoController {

	@RequestMapping(value="api/videoUrl", method = RequestMethod.POST)
	public ResponseEntity<?> videoUrl() throws Exception {
		
		String videoFilePath = "videos/haya_haya.mp4";
		HashMap<String, String> videoUrl = new HashMap();
		videoUrl.put("url", videoFilePath);
		
		return new ResponseEntity<HashMap<String, String>>(videoUrl, HttpStatus.OK);
	}
}
