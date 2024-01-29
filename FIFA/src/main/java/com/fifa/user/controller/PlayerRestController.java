package com.fifa.user.controller;



import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.dto.PlayerInsertDto;
import com.fifa.user.service.FileService;
import com.fifa.user.service.MemberService;
import com.fifa.user.service.PlayerService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerRestController {
	private final MemberService memberService;
	private final PlayerService playerService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); // 파일 업로드하기 위해 필요한거
	
	@Resource(name = "fileService") // 파일업로드하기 위해 필요한거
	FileService fileService;

	/*
	 * create_player 페이지에서 가져온 데이터로 player생성하기(action)
	 */
	@PostMapping("/create_player_action")
	@LoginCheck
	public Map create_player_action(@RequestBody PlayerInsertDto playerInsertDto, Model model, HttpSession session)
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
			playerInsertDto.setImg(imgRoad);
			System.out.println("playerInsertDto의 img는 " + playerInsertDto.getImg());
			System.out.println("playerInsertDto의 name은" + playerInsertDto.getName());
			System.out.println("playerInsertDto의 countryId은" + playerInsertDto.getCountryId());
			System.out.println("playerInsertDto의 clubId는 " + playerInsertDto.getClubId());
			System.out.println("playerInsertDto의 position은" + playerInsertDto.getPosition());
			System.out.println("playerInsertDto의 marketValue은" + playerInsertDto.getMarketValue());

			PlayerFindDto newPlayerFindDto = playerService.insert(playerInsertDto);
			System.out.println("newPlayerFindDto는 " + newPlayerFindDto);
			map.put("result", result);
			return map;
		} else {// sUserId==null일 때
			result = 1;
			map.put("result", result);
			map.put("msg","Member의 UserName은 null입니다.");
			return map;
		}

	}
	
//	// 사진 업로드하기
//	@PostMapping("/register/action")
//	@LoginCheck	
//	public  ResponseEntity<String> playerImgRegisterAction (MultipartHttpServletRequest multiRequest, HttpSession session) {
//		try {
//			session.setAttribute(null, session);
//			fileService.uploadFile(multiRequest);
//			
//			//다음 메서드 호출
//			return secondMethod();
//		} catch (Exception e) {
//			if (logger.isErrorEnabled()) {
//				logger.error("#Exception Message : {}", e.getMessage());
//			}
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
//		}
//	}


	

}
