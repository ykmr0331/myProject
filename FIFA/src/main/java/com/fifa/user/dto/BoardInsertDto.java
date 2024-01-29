package com.fifa.user.dto;

import java.math.BigDecimal;

import com.fifa.user.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BoardInsertDto {
	 private String title;
	 private String content;
	 public static BoardInsertDto responseDto(Board board) {
		 return BoardInsertDto.builder()
				 				.title(board.getTitle())
				 				.content(board.getContent())
				 				.build();
	 }
}
