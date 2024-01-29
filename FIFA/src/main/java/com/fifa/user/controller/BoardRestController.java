package com.fifa.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.dto.MemberLoginDto;
import com.fifa.user.entity.Board;
import com.fifa.user.exception.member.PasswordMismatchException;
import com.fifa.user.service.BoardService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardRestController {
	@Autowired
	BoardService boardService;

}
