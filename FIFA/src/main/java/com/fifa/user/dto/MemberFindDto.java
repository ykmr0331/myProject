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
public class MemberFindDto {
	private Long id;
	private String userName;
	private String password;
	private String email;
	private String role;
	
	public static MemberFindDto responseDto(Member member) {
		return MemberFindDto.builder()
							.email(member.getEmail())
							.id(member.getId())
							.password(member.getPassword())
							.role(member.getRole())
							.userName(member.getUserName())
							.build();
	}
}
