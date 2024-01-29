package com.fifa.user.dto;

import java.math.BigDecimal;

import com.fifa.user.entity.Board;
import com.fifa.user.entity.Player;

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
public class BoardUpdateDto {
	 private String title;
	 private String content;
	
	public static BoardUpdateDto responseDto(Board board) {
		return BoardUpdateDto.builder()
				.content(board.getContent())
				.title(board.getTitle())
				.build();
	}
}
