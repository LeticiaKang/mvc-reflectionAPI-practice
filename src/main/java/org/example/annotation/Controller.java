package org.example.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 클래스, enum 등에 붙일 수 있음
@Target({ElementType.TYPE})
// 유지기간은 runtime기간까지로 설정
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

}
