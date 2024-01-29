package com.fifa.user.dao;

import java.util.List;

import com.fifa.user.entity.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface BoardDao {

    //특정 회원의 게시글들 찾기
    Page<Board> findByMemberUserName(String userName, Pageable pageable) throws Exception;
    	
    
}
