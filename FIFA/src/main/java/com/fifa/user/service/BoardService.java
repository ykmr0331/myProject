package com.fifa.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fifa.user.dao.BoardDao;
import com.fifa.user.dto.BoardInsertDto;
import com.fifa.user.dto.BoardUpdateDto;
import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.entity.Board;
import com.fifa.user.entity.Member;
import com.fifa.user.repository.BoardRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private MemberService memberService; 
	@Autowired 
	private BoardDao boardDao;
	
	//게시판 추가하기
	public Board write(BoardInsertDto boardInsertDto,Boolean secret ,String sUserId)throws Exception {
		
		if(!(sUserId.equals(""))) {//sUserId가 null이 아닐 때
			Member member = Member.toFindEntity(memberService.findMemberByUserName(sUserId));
			Board board = Board.toResponseEntity(boardInsertDto);
			board.setMember(member);
			board.setSecret(secret);
			System.out.println("bordInsertDto를 넣은 board는>> "+board);
			
			Board insertedBoard = boardRepository.save(board);
			System.out.println("Board객체 db에 저장 성공, 저장된 Board객체는 >>"+insertedBoard);
			return insertedBoard;
		} else {//sUserId가 null일 때
			return null;
		}

	}
	
	//게시판 업데이트하기
	public Board update(BoardUpdateDto boardUpdateDto,Boolean secret, Integer id)throws Exception {
		Board board = this.boardView(id);
		board.setContent(boardUpdateDto.getContent());
		board.setTitle(boardUpdateDto.getTitle());
		board.setSecret(secret);
		Board updatedBoard = boardRepository.save(board);
		System.out.println("게시판 업데이트 된거 디비에 들어감");
		return updatedBoard;
	}

	// 게시글 리스트 처리
	public List<Board> boardList() {
		return boardRepository.findAll();
	}
	
    // 특정 게시글 불러오기
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }
    
    //특정 게시물 삭제하기
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }
    
    //페이지에 있는 게시물List찾기
    public Page<Board> boardList(Pageable pageable){
        return  boardRepository.findAll(pageable);
    }
    //일반 회원 게시글에서 검색으로 찾기
    public  Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
    	return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }
    // 관리자  게시글에서
    public  Page<Board> boardMemberSearchList(String searchKeyword, Pageable pageable) throws Exception{
    	return boardDao.findByMemberUserName(searchKeyword, pageable);
    }
    

    
    
}
