package com.fifa.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.fifa.user.entity.Board;
import com.fifa.user.repository.BoardRepository;

@Repository 
public class BoardDaoImpl implements BoardDao{
	@Autowired
	BoardRepository boardRepository;
	 
	@Override
	public Page<Board> findByMemberUserName(String userName,Pageable pageable) throws Exception {
		return boardRepository.findByMemberUserName(userName, pageable);
	}
}
