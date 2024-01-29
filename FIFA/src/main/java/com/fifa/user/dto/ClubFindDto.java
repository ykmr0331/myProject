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
public class ClubFindDto {
	private Long id;
	private String name;
	private Long countryId;
	private Long funds;
	private String img;
	
	 public static ClubFindDto responseDto(Club club) {
		  return ClubFindDto.builder()
				  .id(club.getId())
				  .countryId(club.getCountry().getId())
				  .name(club.getName())
				  .funds(club.getFunds())
				  .img(club.getImg())
				  .build();
	  
	  
	  
	  }
}
