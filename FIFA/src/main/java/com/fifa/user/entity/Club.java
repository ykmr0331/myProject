package com.fifa.user.entity;



import com.fifa.user.dto.ClubDto;
import com.fifa.user.dto.ClubInsertDto;

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

@Entity(name="Club")
@Table(name ="Club")
@EqualsAndHashCode//Lombok 어노테이션으로, 상속받은 부모 클래스의 필드들도 고려하여 equals() 및 hashCode() 메서드를 생성합니다.
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data				// Lombok에서 제공하는 어노테이션으로, 클래스 내의 필드에 대한 기본적인 메서드를 자동으로 생성해줍니다. 이 어노테이션을 사용하면 toString(), equals(), hashCode(), getter, setter 등의 메서드를 간편하게 생성할 수 있습니다.
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//이 어노테이션은 자동 생성된 값에 대한 전략을 지정합니다. IDENTITY 전략은 대부분의 데이터베이스에서 기본 키 값이 자동으로 증가하도록 설정합니다.
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    
    @Column(name = "img", nullable = true)
    private String img;//이미지 파일
    
    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST) //Club 엔티티를 저장할 때, 해당 Club에 연관된 Country도 함께 저장되도록 설정한 것
    @JoinColumn(name = "country_id")
    private Country country;

    @NotNull
    @Column(name = "funds")
    private Long funds;
    
    public static Club toInsertEntnty(ClubInsertDto clubInsertDto) {
    	return Club.builder().funds(clubInsertDto.getFunds())
    							.name(clubInsertDto.getName())
    							.build();
    }
    
//	@ManyToOne
//	@JoinColumn(name = "memberId")
//	@ToString.Exclude
//	private Member member; 
}