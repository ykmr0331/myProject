package com.fifa.user.entity;

import java.math.BigDecimal;

import com.fifa.user.dto.PlayerInsertDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Player")
@EqualsAndHashCode//Lombok 어노테이션으로, 상속받은 부모 클래스의 필드들도 고려하여 equals() 및 hashCode() 메서드를 생성합니다.
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data	
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "img", nullable = true)
    private String img;//이미지 파일
    
    @Column(name = "club_name")
    private String clubName;
    
    @Column(name = "country_name")
    private String countryName;

    @ManyToOne(cascade = CascadeType.PERSIST) //Player 엔티티를 저장할 때, 해당 Player에 연관된 country도 함께 저장되도록 설정한 것
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(cascade = CascadeType.PERSIST) //Player 엔티티를 저장할 때, 해당 Player에 연관된 Club도 함께 저장되도록 설정한 것
    @JoinColumn(name = "club_id")
    private Club club;

    @Column(name = "position")
    private String position;

    @Column(name = "market_value")
    private BigDecimal marketValue;
    
    
    public static Player toResponseEntity(PlayerInsertDto  playerInsertDto) {
    	return Player.builder().marketValue(playerInsertDto.getMarketValue())
    							.name(playerInsertDto.getName())
    							.marketValue(playerInsertDto.getMarketValue())
    							.position(playerInsertDto.getPosition())
    							.build();
    }
    
//    @ManyToOne(cascade = CascadeType.PERSIST) //Player 엔티티를 저장할 때, 해당 Player에 연관된 Member도 함께 저장되도록 설정한 것
//	@JoinColumn(name = "member_id")
//	@ToString.Exclude
//	private Member member; 
    			
}