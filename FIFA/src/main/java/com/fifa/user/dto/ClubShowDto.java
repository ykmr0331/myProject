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
public class ClubShowDto {
	private Long id;
	private String name;
	private String countryName;
	private Long funds;
	private String img;
	
	 public static ClubShowDto responseDto(Club club) {
		  return ClubShowDto.builder()
				  .id(club.getId())
				  .countryName(club.getCountry().getName())
				  .name(club.getName())
				  .funds(club.getFunds())
				  .img(club.getImg())
				  .build();
	  
	  
	  
	  }
}
