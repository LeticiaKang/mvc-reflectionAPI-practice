package org.example.model;

import java.util.Objects;


// 클래스에 대한 모든 정보를 출력하는 테스트 메서드를 만들기 위해 User를 생성함
public class User {
    private String userId;
    private String name;

    public User(String userId, String name){
        this.userId = userId;
        this.name = name;
    }

    public boolean equalsUser(User user){
        return this.equals(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name);
    }
}
