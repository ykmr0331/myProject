package com.fifa.user.dto;

import java.math.BigDecimal;

import com.fifa.user.entity.Player;

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
public class PlayerUpdateDto {
	private Long id;
	private Long countryId;
	private Long clubId;
	private String position;
	private BigDecimal marketValue;
	
	public static PlayerUpdateDto responseDto(Player player) {
		return PlayerUpdateDto.builder()
				.id(player.getId())
				.countryId(player.getCountry().getId())
				.clubId(player.getClub().getId())
				.position(player.getPosition())
				.marketValue(player.getMarketValue())
				.build();
	}
}
