package org.example;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;



public class ReflectonTest {

    /**
     * 목표 : @Controller 애노테이션이 설정돼 있는 모든 클래스를 찾아서 출력한다.
     */

    private static final Logger logger = LoggerFactory.getLogger(ReflectonTest.class);

    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));
        logger.debug("beans: [{}]", beans);
    }


    private Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        /**
         *  reflections(org.example) 밑에 있는 파일 중
         *  Controller라는 애노테이션이 붙어 있는 모든 클래스를 찾는다.
         *  찾은 모든 클래스는 bean(HashSet)에 담는다.
         */
//        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));
//        beans.addAll(reflections.getTypesAnnotatedWith(Service.class));
        return beans;
    }

    @Test
    void showClass() {   // User에 사용된 모든 정보를 출력하는 코드
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        logger.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all declared Constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all declared methods: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    @Test
    void load() throws ClassNotFoundException { // Heap영역에 있는 클래스 타입을 가져오는 3가지 방법
        Class<User> clazz = User.class;
        // 결과 : clazz: [class org.example.model.User]

        User user = new User("serverwizard", "홍길동");
        Class<? extends User> clazz2 = user.getClass();
        // 결과 : clazz: [class org.example.model.User]

        Class<?> clazz3 = Class.forName("org.example.model.User");
        // 결과 : clazz: [class org.example.model.User]

        logger.debug("clazz: [{}]", clazz);
        logger.debug("clazz2: [{}]", clazz2);
        logger.debug("clazz3: [{}]", clazz3);

        // 동일한 객체인지 확인한다.
        // // 결과 : 모두 Pass
        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz == clazz3).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
    }
}
