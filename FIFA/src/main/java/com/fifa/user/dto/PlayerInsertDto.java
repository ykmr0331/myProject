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
public class PlayerInsertDto {
	private String name;
	private Long countryId;
	private Long clubId;
	private String position;
	private BigDecimal marketValue;
	private String img;
	
	public static PlayerInsertDto responseDto(Player player) {
		return PlayerInsertDto.builder()
							.name(player.getName())
							.countryId(player.getCountry().getId())
							.clubId(player.getClub().getId())
							.position(player.getPosition())
							.marketValue(player.getMarketValue())
							.img(player.getImg())
							.build();
	}
}
