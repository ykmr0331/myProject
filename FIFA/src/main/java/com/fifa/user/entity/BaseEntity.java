package com.fifa.user.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // Lombok에서 제공하는 어노테이션으로, 클래스 내의 필드에 대한 기본적인 메서드를 자동으로 생성해줍니다. 이 어노테이션을 사용하면 toString(), equals(), hashCode(), getter, setter 등의 메서드를 간편하게 생성할 수 있습니다.
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass //JPA에서 사용되는 어노테이션으로, 해당 클래스가 엔터티가 아닌데도 엔터티 클래스에 속성을 제공하고 싶을 때 사용됩니다. 이 어노테이션을 통해 해당 클래스의 속성들이 상속되어 하위 엔터티 클래스에서 사용될 수 있습니다. 
public class BaseEntity {
    // @CreationTimestamp 어노테이션은 엔터티가 저장될 때 자동으로 현재 일시를 할당합니다.
    // @Column 어노테이션은 해당 필드에 대한 추가적인 설정을 지정합니다.
    // updatable = false로 설정되어 있어 생성 시간은 변경되지 않도록 합니다.
    @CreationTimestamp
    @Column(updatable = false)
    @ToString.Exclude
    private LocalDateTime createTime;  // 데이터 생성 시간

     @UpdateTimestamp //@UpdateTimestamp 어노테이션이 사용된 이 변수는 JPA(Java Persistence API)에서 제공하는 어노테이션으로, 엔터티가 업데이트될 때 자동으로 현재 일시를 할당합니다. 이를 통해 데이터의 갱신 시간을 추적할 수 있습니다.
    private LocalDateTime updateTime;  // 데이터 갱신 시간
}
