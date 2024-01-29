package com.fifa.user.dto;


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
public class TransferMarketInsertDto {
	private Long playerId;
	private Long previousClubId;
	private Long newClubId;
	private Long transferFee;
 
	
	public static TransferMarketInsertDto responseDto(TransferMarket transferMarket) {
		return TransferMarketInsertDto.builder()
										.newClubId(transferMarket.getNewClub().getId())
										.playerId(transferMarket.getPlayer().getId())
										.previousClubId(transferMarket.getPreviousClub().getId())
										.transferFee(transferMarket.getTransferFee())
										.build();

	}
	
}
