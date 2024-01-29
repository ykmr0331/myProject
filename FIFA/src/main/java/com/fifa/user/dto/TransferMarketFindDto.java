package com.fifa.user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fifa.user.entity.TransferMarket;

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
public class TransferMarketFindDto {
	private Long id;
	private String playerName;
	private String previousClubName;
	private String newClubName;
	private Long transferFee;
	private LocalDateTime createDate;
	
	public static TransferMarketFindDto responseDto(TransferMarket transferMarket) {
		return TransferMarketFindDto.builder()
										.id(transferMarket.getId())
										.newClubName(transferMarket.getNewClub().getName())
										.playerName(transferMarket.getPlayer().getName())
										.previousClubName(transferMarket.getPreviousClub().getName())
										.transferFee(transferMarket.getTransferFee())
										.createDate(transferMarket.getCreateDate())
										.build();

	}
}
