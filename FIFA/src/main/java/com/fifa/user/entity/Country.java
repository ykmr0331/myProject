package com.fifa.user.entity;




import java.util.List;

import com.fifa.user.dto.CountryDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.ArrayList;
@Entity
@Table(name = "Country")
@EqualsAndHashCode//Lombok 어노테이션으로, 상속받은 부모 클래스의 필드들도 고려하여 equals() 및 hashCode() 메서드를 생성합니다.
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data	
public class Country{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "img", nullable = true)
    private String img;//이미지 파일

    @ManyToOne(cascade = CascadeType.PERSIST) // Country 엔티티를 저장할 때, 해당 Country에 연관된 Continent도 함께 저장되도록 설정한 것
    @JoinColumn(name = "continent_id")
    private Continent continent;
    
	@ToString.Exclude
	@OneToMany(mappedBy = "country")
	@Builder.Default
	private List<Player> playerList = new ArrayList<>();
    
      
//	@ManyToOne
//	@JoinColumn(name = "memberId")
//	@ToString.Exclude
//	private Member member;
	
    public static Country toInsertEntnty(CountryDto countryDto) {
    	return Country.builder().name(countryDto.getName())
    							.build();
    
    }
    

}


