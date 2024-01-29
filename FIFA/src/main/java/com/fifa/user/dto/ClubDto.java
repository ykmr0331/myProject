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
public class ClubDto {
	private String name;
	private Long countryId;
	private Long funds;
	
	  public static ClubDto responseDto(Club club) {
		  return ClubDto.builder()
				  .countryId(club.getCountry().getId())
				  .name(club.getName())
				  .funds(club.getFunds())
				  .build();
	  
	  
	  
	  }
}
