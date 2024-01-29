package com.fifa.user.dto;

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
public class MemberUpdateDto {
	private String userName;
	private String password;
	
	
	
	public static MemberUpdateDto responseDto(Member member) {
		return MemberUpdateDto.builder()
							.userName(member.getUserName())
							.password(member.getPassword())
							.build();
	}
}
