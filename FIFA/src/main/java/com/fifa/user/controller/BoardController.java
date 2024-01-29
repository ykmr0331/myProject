package com.fifa.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.web.PageableDefault;

import com.fifa.user.dto.BoardInsertDto;
import com.fifa.user.dto.BoardUpdateDto;
import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.entity.Board;
import com.fifa.user.entity.Member;
import com.fifa.user.service.BoardService;
import com.fifa.user.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Controller
@RequiredArgsConstructor
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private MemberService memberService;

//게시글 작성페이지로 가기
	@GetMapping("/board_write") // localhost:8090/board/write
	public String boardwriteForm(HttpSession session, Model model) throws Exception {
		String sUserId = (String) session.getAttribute("sUserId");
		if (!(sUserId.equals("") || sUserId == null)) {
			System.out.println("session에 들어 있는 sUserId는 " + sUserId);
			return "/board/boardwrite";
		} else {
			model.addAttribute("message", "로그인시 게시글을 작성할 수 있습니다.");
			model.addAttribute("SearchUrl", "/board_list");
			return "/board/message";
		}

	}

//게시글 작성(action)
	@PostMapping("/board/writepro")
	public String boardWritePro(@RequestParam(name = "secret", defaultValue = "false") Boolean secret,
			BoardInsertDto boardInsertDto, HttpSession session) throws Exception {
		// 두가지 경우를 가지는데 회원인경우 비회원인 경우
		String sUserId = (String) session.getAttribute("sUserId");
		System.out.println("secret은 " + secret + "입니다.");
		System.out.println("session에 담겨있는 sUserId는 " + sUserId + "입니다.");
		if (sUserId != null) {// 회원인 경우
			MemberFindDto memberFindDto = memberService.findMemberByUserName(sUserId);
			System.out.println("boardInsertDto는 " + boardInsertDto);
			Board insertedboard = boardService.write(boardInsertDto, secret, sUserId);
			return "redirect:/board_list";
		} else {// 비회원인 경우
			Board insertedboard = boardService.write(boardInsertDto, secret, sUserId);
			return "redirect:/board_list";
		}

	}

//게시글 목록페이지 가기
	@GetMapping("/board_list")
	public String boardList(Model model,
			//@PageableDefault은 Spring Framework에서 페이지네이션 처리를 위해 사용되는 어노테이션 중 하나입니다. 
			//이 어노테이션은 메소드의 매개변수로 사용되어 해당 메소드에 전달된 Pageable 객체를 설정
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			String searchKeyword) {
		Page<Board> list = null;

		if (searchKeyword == null) {
			list = boardService.boardList(pageable);
		} else {
			list = boardService.boardSearchList(searchKeyword, pageable);
		}

		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, list.getTotalPages());

		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "/board/boardList";
	}

//페이지 상세보기	
	@GetMapping("/board_view")
	public String boardView(Model model, Integer id, HttpSession session) throws Exception {
		Board selectedBoard = boardService.boardView(id);
		String role = (String) session.getAttribute("role");
		String sUserId = (String) session.getAttribute("sUserId");

		Long loginMemberId = null;

		if (sUserId == null && role == null) {// 비회원이면
			loginMemberId = null;

			Member boardMember = selectedBoard.getMember();// 게시글 작성자
			Long boardMemberId = boardMember.getId();

			if (selectedBoard.getSecret()) {// 리스트에서 클릭한 게시글이 비밀글이라면 그냥 못보고, 비밀글이 아니면 그냥 다 볼수 있음
				System.out.println("클릭한 게시글이 비밀글일 때");
				model.addAttribute("message", "비밀글은 작성자만 볼 수 있습니다.");
				model.addAttribute("SearchUrl", "/board_list");
				return "/board/message";
			} else {// 비밀글이 아니라면
				model.addAttribute("board", boardService.boardView(id));
				return "board/boardview";
			}

		} else { // 회원이면
			MemberFindDto memberFindDto = memberService.findMemberByUserName(sUserId);// 현재 로그인한 멤버
			loginMemberId = memberFindDto.getId();

			Member boardMember = selectedBoard.getMember();// 게시글 작성자
			Long boardMemberId = boardMember.getId();

			if (selectedBoard.getSecret()) {// 리스트에서 클릭한 게시글이 비밀글이라면
				if (role.equals("Admin") || loginMemberId == boardMemberId) {// 관리자인 경우 또는 로그인계정이 게시글 올린사람과 같은경우
					model.addAttribute("board", boardService.boardView(id));
					return "board/boardview";
				} else {
					model.addAttribute("message", "비밀글은 작성자만 볼 수 있습니다.");
					model.addAttribute("SearchUrl", "/board_list");
					return "/board/message";
				}
			} else {// 비밀글이 아니라면
				model.addAttribute("board", boardService.boardView(id));
				return "board/boardview";
			}
		}

	}

//삭제하기
	@PostMapping("/board_delete")
	public String boardDelete(Integer id, HttpSession session, Model model) {
		String sUserId = (String) session.getAttribute("sUserId");
		Board board = boardService.boardView(id);
		if (sUserId.equals(board.getMember().getUserName())) {
			boardService.boardDelete(id);
			return "redirect:/board_list";
		} else if (sUserId.equals("")) {
			model.addAttribute("message", "로그인시 게시글을 작성할 수 있습니다.");
			model.addAttribute("SearchUrl", "/board_list");
			return "/board/message";
		} else {
			model.addAttribute("message", "로그인된 계정과 게시물을 작성한 계정이 일치하지 않습니다.");
			model.addAttribute("SearchUrl", "/board_list");
			return "/board/message";
		}
	}

// 수정하기 페이지로 넘어가는거
	@GetMapping("/board_modify{id}")
	public String boardModify(@PathVariable("id") Integer id, Model model, HttpSession session) throws Exception {

		Board board = boardService.boardView(id);
		String sUserId = (String) session.getAttribute("sUserId");
		if (sUserId.equals(board.getMember().getUserName())) {
			model.addAttribute("board", boardService.boardView(id));
			board = boardService.boardView(id);
			return "/board/boardmodify";
		} else {
			// 비회원이거나 현재로그인한 멤버가 게시글 쓴 멤버랑 다른 경우
			model.addAttribute("message", "게시글 작성자가 아닙니다.");
			model.addAttribute("SearchUrl", "/board_list");
			return "/board/message";
		}
	}

// 수정하기 action
	@PostMapping("/board/update/{id}")
	public String boardUpdate(@PathVariable("id") Integer id,
			@RequestParam(name = "secret", defaultValue = "false") Boolean secret, BoardUpdateDto boardUpdateDto,
			Model model, HttpSession session) throws Exception {

		Board board = boardService.boardView(id);// 불러왔어
		String sUserId = (String) session.getAttribute("sUserId");
		System.out.println("sUserId는 " + sUserId);
		System.out.println("board.getMember().getUserName()" + board.getMember().getUserName());

		if (sUserId.equals(board.getMember().getUserName())) {// 현재로그인한 멤버가 게시글 쓴 멤버랑 같은 경우
			Board updatedBoard = boardService.update(boardUpdateDto, secret, id);
//			MemberFindDto memberFindDto = memberService.findMemberByUserName(sUserId);
//			Member member = Member.toFindEntity(memberFindDto);
//			board.setMember(member);
			model.addAttribute("message", "글 수정 완료.");
			model.addAttribute("SearchUrl", "/board_list");

			return "board/message";
		} else {
			// 비회원이거나 현재로그인한 멤버가 게시글 쓴 멤버랑 다른 경우
			model.addAttribute("message", "게시글 작성자가 아닙니다.");
			model.addAttribute("SearchUrl", "/board_list");
			return "board/message";
		}

	}

	// 관리자 페이지에서 회원별 게시글 목록페이지 가기
	@GetMapping("/board_member")
	public String board_member(Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			String searchKeyword, HttpSession session) throws Exception {
		String role = (String) session.getAttribute("role");

		try {

			if (role.equals("Admin")) {
				Page<Board> list = null;
				List<MemberFindDto> allMemberFindDto = memberService.findAll();

// 키워드가 null인 경우				
				if (searchKeyword == null || searchKeyword.equals("")) {
					list = boardService.boardList(pageable);
// 키워드가 존재하는 경우				
				} else {
					for (int i = 0; i < allMemberFindDto.size(); i++) {
						MemberFindDto memberFindDto = allMemberFindDto.get(i);
						// 입력한 계정의 아이디와 일치하는 계정이 있다면
						if (memberFindDto.getUserName().equals(searchKeyword)) {
							System.out.println("입력한 계정의 아이디와 일치하는 계정이 있는 경우 ");
							System.out.println("memberFindDto.getUserName()은 " + memberFindDto.getUserName());
							System.out.println("searchKeyword는  " + searchKeyword);
							list = boardService.boardMemberSearchList(searchKeyword, pageable);
							break;
							// 입력한 계정의 아이디와 일치하는 계정이 없으면
						} else {
							list = null;
						}
					} // for문 끝

				} // 키워드 존재여부 끝

				if (list != null) { // list가 null이 아니라면
					int nowPage = list.getPageable().getPageNumber() + 1;
					int startPage = Math.max(nowPage - 4, 1);
					int endPage = Math.min(nowPage + 5, list.getTotalPages());

					model.addAttribute("list", list);
					model.addAttribute("nowPage", nowPage);
					model.addAttribute("startPage", startPage);
					model.addAttribute("endPage", endPage);
					return "/admin/board_member";
				} else {
					int nowPage = 1;
					int startPage = 1;
					int endPage = 1;

					model.addAttribute("list", list);
					model.addAttribute("nowPage", nowPage);
					model.addAttribute("startPage", startPage);
					model.addAttribute("endPage", endPage);
					return "/admin/board_member";
				}

			} else {// role이 Admin이 아닐 때
				return "/index";
			}

		} catch (Exception e) { // role타입이 null인 경우(비회원일 때
			e.printStackTrace();
			return "/404";
		}

	}

	// 관리자페이지에서 페이지 상세보기
	@GetMapping("/board_admin_view")
	public String board_admin_view(Model model, Integer id, HttpSession session) throws Exception {
		String role = (String) session.getAttribute("role");

		try {
			if (role.equals("Admin")) {
				System.out.println("role 타입은 " + role + "입니다.");
				Board selectedBoard = boardService.boardView(id);// 클릭한 게시글임
				model.addAttribute("board", boardService.boardView(id));
				return "board/board_admin_view";
			} else {
				return "/index";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "/404";

		}

	}

	// 관리자 페이지에서 글 삭제하기
	@PostMapping("/board_admin_delete")
	public String board_admin_delete(Integer id, HttpSession session, Model model) {
		String role = (String) session.getAttribute("role");
		System.out.println("role은 " + role + "입니다.");
		Board board = boardService.boardView(id);

		if (role.equals("Admin")) {
			boardService.boardDelete(id);
			return "redirect:/board_list";
		} else {
			model.addAttribute("message", "삭제권한은 관리자에게 있습니다.");
			model.addAttribute("SearchUrl", "/board_member");
			return "/board/message";
		}

	}
}
