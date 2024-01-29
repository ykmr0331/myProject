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
public class PlayerFindDto {
	private Long id;
	private String name;
	private Long countryId;
	private Long clubId;
	private String position;
	private BigDecimal marketValue;
	private String clubName;
	private String countryName;
	private String img;
	
	public static PlayerFindDto responseDto(Player player) {
		return PlayerFindDto.builder()
							.id(player.getId())
							.name(player.getName())
							.countryId(player.getCountry().getId())
							.clubId(player.getClub().getId())
							.position(player.getPosition())
							.marketValue(player.getMarketValue())
							.clubName(player.getClubName())
							.countryName(player.getCountryName())
							.img(player.getImg())
							.build();
	}
	
}
