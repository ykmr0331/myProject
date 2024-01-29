package com.fifa.user.dto;

import com.fifa.user.entity.Club;


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
public class ClubUpdateDto {
	private Long id;
	private Long funds;
	private String name;
	
	public static ClubUpdateDto responseDto(Club club) {
		
		return ClubUpdateDto.builder()
							.id(club.getId())
							.funds(club.getFunds())
							.name(club.getName())
							.build();
		
	}
}
