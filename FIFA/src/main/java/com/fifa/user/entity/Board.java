package com.fifa.user.entity;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fifa.user.dto.BoardInsertDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode//Lombok 어노테이션으로, 상속받은 부모 클래스의 필드들도 고려하여 equals() 및 hashCode() 메서드를 생성합니다.
@Builder
@Data
@NoArgsConstructor// 매개변수가 없는 기본 생성자를 생성합니다.
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자를 생성합니다
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //
	private Integer id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false, length = 1000)
	private String content;
	
	@Column(name = "secret", nullable = false)
	private Boolean secret;

	@ManyToOne
	@JoinColumn(name = "memberId")
	@ToStringExclude
	private Member member;

	public static Board toResponseEntity(BoardInsertDto boardInsertDto) {
		return Board.builder().content(boardInsertDto.getContent())
								.title(boardInsertDto.getTitle())
								.build();

	}

}