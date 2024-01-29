package com.fifa.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.dto.PlayerInsertDto;
import com.fifa.user.dto.TransferMarketFindDto;
import com.fifa.user.dto.TransferMarketInsertDto;
import com.fifa.user.service.MemberService;
import com.fifa.user.service.PlayerService;
import com.fifa.user.service.TransferMarketService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferMarketRestController {
	private final MemberService memberService;
	private final TransferMarketService transferMarketService;
	private final PlayerService playerService;
	
	/*
	 *  transfer_registration 페이지에서 가져온 데이터로 transfermarket 생성하기(action)
	 */
	@PostMapping("/create_transfer_action")
	@LoginCheck
	public Map create_transfer_action(@RequestBody TransferMarketInsertDto transferMarketInsertDto, Model model, HttpSession session)
			throws Exception {
		//그리고 previousClubId와 newClubId는 서로 달라야돼
		
		
		
		Long previousClubId = transferMarketInsertDto.getPreviousClubId(); //transferMarketInsertDto의 previousClubId
		
		Long newClubId = transferMarketInsertDto.getNewClubId();
		
		Long playerId = transferMarketInsertDto.getPlayerId();
		PlayerFindDto playerFindDto = playerService.findPlayer(playerId);//playerId로 찾은 player
		Long playerClubId = playerFindDto.getClubId();
		
		HashMap map = new HashMap<>();
		String sUserId = (String) session.getAttribute("sUserId");
		String role = (String) session.getAttribute("role");
		int result = 4;

		if (sUserId != null) { // 관리자일 때

			
			if(previousClubId != playerClubId) {
				//만약에 transferMarketInsertDto의 previousClubId와  transferMarketInsertDto의 playerId로 찾은 player의 clubId가 일치하지 않으면
				result = 1;
				map.put("result", result);
				map.put("msg","previousClub과 player의 club이 일치하지 않습니다.");
				return map;
			} else if(previousClubId == newClubId) {
				result = 2;
				map.put("result", result);
				map.put("msg","previousClub과 newClub이 같습니다.");
				return map;
			} else {

				// 일단 여기서는 insert를 해야지
//				MemberFindDto memberFindDto = memberService.findMemberByUserName(sUserId);
//				Long memberID = memberFindDto.getId();
//				transferMarketInsertDto.setMemberId(memberID);

				System.out.println("transferMarketInsertDto의 playerId은" + transferMarketInsertDto.getPlayerId());
				System.out.println("transferMarketInsertDto의 previousClubId은" + transferMarketInsertDto.getPreviousClubId());
				System.out.println("transferMarketInsertDto의 transferFee는 " + transferMarketInsertDto.getTransferFee());
				System.out.println("transferMarketInsertDto의 newClubId은" + transferMarketInsertDto.getNewClubId());
//				System.out.println("transferMarketInsertDto의 memberId은" + transferMarketInsertDto.getMemberId());

				TransferMarketFindDto newTransferMarketFindDto= transferMarketService.insert(transferMarketInsertDto);
				System.out.println("newTransferMarketFindDto는 " + newTransferMarketFindDto);
				map.put("result", result);
				return map;
			}
			
			
			
		} else {// 관리자가 아닐 때
			result = 3;
			map.put("result", result);
			map.put("msg","관리자가 아닙니다.");
			return map;
		}

	}
	
}
