package com.fifa.user.dto;

import java.math.BigDecimal;

import com.fifa.user.entity.Member;

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
public class MemberInsertDto {
	//userName, password, email, 
	private String userName;
	private String password; 
	private String email;
	
	public static MemberInsertDto responseDto(Member member) {
		return MemberInsertDto.builder()
							.email(member.getEmail())
							.password(member.getPassword())
							.userName(member.getUserName())
							.build();
							
	}
}
