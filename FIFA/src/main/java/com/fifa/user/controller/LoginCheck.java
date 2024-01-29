package com.fifa.user.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) //이 어노테이션이 유지되는 기간을 설정합니다. RUNTIME은 런타임 시에도 유지됨을 의미
@Target(METHOD)//이 어노테이션이 적용될 대상을 설정합니다. 여기서는 METHOD로 설정하여 메서드에만 적용될 수 있도록 지정했습니다.
public @interface LoginCheck {

}
