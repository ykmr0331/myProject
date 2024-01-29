package com.fifa.user.dto;

import java.math.BigDecimal;

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
public class ClubInsertDto {
	private String name;
	private Long funds;
	private Long countryId;
	private String img;
	
}
