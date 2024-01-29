package com.fifa.user.dto;

import com.fifa.user.entity.Country;

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
public class CountryDto {
	private Long id;
	private String name;
	private Long continentId;
	private String img;
	
	
	  public static CountryDto responseDto(Country country) {
		  return CountryDto.builder().id(country.getId())
				  						.name(country.getName())
				  						.continentId(country.getContinent().getId())
				  						.img(country.getImg())
				  						.build();
	  }
}
