package com.fifa.user.dto;



import com.fifa.user.entity.Continent;

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
public class ContinentDto {
	private Long id;
	private String name;
	
	  public static ContinentDto responseDto(Continent continent) {
		  return ContinentDto.builder().id(continent.getId()).name(continent.getName()).build();
	  }
}
