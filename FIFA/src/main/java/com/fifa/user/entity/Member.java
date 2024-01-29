package com.fifa.user.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fifa.user.dto.MemberFindDto;
import com.fifa.user.dto.MemberInsertDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "member") // JPA에서 해당 클래스를 "Member"라는 이름의 엔터티로 매핑합니다.
@Data // 클래스 내의 필드에 대한 기본적인 메서드를 자동으로 생성해줍니다. 이 어노테이션을 사용하면 toString(), equals(),
		// hashCode(), getter, setter 등의 메서드를 간편하게 생성할 수 있습니다.
@NoArgsConstructor// 매개변수가 없는 기본 생성자를 생성합니다.
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자를 생성합니다
@Builder // Lombok 어노테이션으로, 빌더 패턴을 사용할 수 있도록 합니다.
@DynamicUpdate // @DynamicUpdate는 Hibernate에서 제공하는 어노테이션으로, 엔티티를 업데이트할 때 변경된 필드만 고려하여 SQL 쿼리를
				// 생성하는 기능을 활성화하는데 사용됩니다.
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String userName;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(unique = true, nullable = true)
	private String email;

	@Builder.Default
	private String role = "Member"; // 기본값으로 "Admin" 설정

	@ToString.Exclude
	@OneToMany(mappedBy = "member")
	@Builder.Default
	private List<Board> BoardList = new ArrayList<Board>();
	
	

//	@ToString.Exclude
//	@OneToMany(mappedBy = "member")
//	@Builder.Default
//	private List<Continent> continentList = new ArrayList<>();
//	
//	
//	@ToString.Exclude
//	@OneToMany(mappedBy = "member")
//	@Builder.Default
//	private List<Country> countryList = new ArrayList<>();
//	
//	@ToString.Exclude
//	@OneToMany(mappedBy = "member")
//	@Builder.Default
//	private List<Club> clubList = new ArrayList<>();
//	
//	@ToString.Exclude
//	@OneToMany(mappedBy = "member")
//	@Builder.Default
//	private List<Player> playerList = new ArrayList<>();
//	
//	@ToString.Exclude
//	@OneToMany(mappedBy = "member")
//	@Builder.Default
//	private List<TransferMarket> transferMarketList = new ArrayList<>();

	public static Member toInsertEntity(MemberInsertDto insertDto) {
		return Member.builder().email(insertDto.getEmail()).password(insertDto.getPassword())
				.userName(insertDto.getUserName()).build();
	}

	public static Member toFindEntity(MemberFindDto findDto) {
		return Member.builder().id(findDto.getId()).email(findDto.getEmail()).password(findDto.getPassword())
				.userName(findDto.getUserName()).build();
	}

}
