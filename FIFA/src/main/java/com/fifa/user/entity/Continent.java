package com.fifa.user.entity;





import java.util.List;

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
@Table(name = "Continent")
@EqualsAndHashCode//Lombok 어노테이션으로, 상속받은 부모 클래스의 필드들도 고려하여 equals() 및 hashCode() 메서드를 생성합니다.
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data	
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//이 어노테이션은 자동 생성된 값에 대한 전략을 지정합니다. IDENTITY 전략은 대부분의 데이터베이스에서 기본 키 값이 자동으로 증가하도록 설정합니다.
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
//	@ManyToOne
//	@JoinColumn(name = "memberId")
//	@ToString.Exclude
//	private Member member; //주문자
	
	
	@ToString.Exclude
	@OneToMany(mappedBy = "continent")
	@Builder.Default
	private List<Country> countryList = new ArrayList<>();
  
}