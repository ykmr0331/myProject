package com.fifa.user.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fifa.user.dto.PlayerInsertDto;
import com.fifa.user.dto.TransferMarketInsertDto;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EqualsAndHashCode//Lombok 어노테이션으로, 상속받은 부모 클래스의 필드들도 고려하여 equals() 및 hashCode() 메서드를 생성합니다.
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data	
@Table(name = "TransferMarket")
public class TransferMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST) //TransferMarket 엔티티를 저장할 때, 해당 TransferMarket에 연관된 Player도 함께 저장되도록 설정한 것
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(cascade = CascadeType.PERSIST) //TransferMarket 엔티티를 저장할 때, 해당 TransferMarket에 연관된 Club도 함께 저장되도록 설정한 것
    @JoinColumn(name = "previous_club_id")
    private Club previousClub;

    @ManyToOne(cascade = CascadeType.PERSIST) //TransferMarket 엔티티를 저장할 때, 해당 TransferMarket에 연관된 Club도 함께 저장되도록 설정한 것
    @JoinColumn(name = "new_club_id")
    private Club newClub;


    @Column(name = "transfer_fee")
    private Long transferFee;
    
    
	@Column(updatable = false)
	@CreationTimestamp// 해당 필드의 값이 자동으로 생성되는 시간을 나타내기 위해 사용
	private LocalDateTime createDate; //주문날짜

    public static TransferMarket toResponseEntity(TransferMarketInsertDto  dto) {
    	return TransferMarket.builder()
    						  .transferFee(dto.getTransferFee())
    						  .build();
    }
    
 

}