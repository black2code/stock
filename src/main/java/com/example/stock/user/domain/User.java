package com.example.stock.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "birthday")
    private String birthday;

    private LocalDate createdAt;

    private LocalDate updatedAt;


    @Builder User(String name, String password, String birthday, String auth) {
        this.name = name;
        this.password = password;
        this.birthday = birthday;
    }

    @Override // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override  // 계정 만료 여부
    public boolean isAccountNonExpired() {
        // 로직
        return true;  // 만료 되지 않음
    }

    @Override // 잠금 여부
    public boolean isAccountNonLocked() {
        // 로직
        return true; // 잠금 되지 않음
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 패스 워드 만료 로직
        return true; // 만료되지 않음
    }

    @Override // 계정 사용 가능 여부
    public boolean isEnabled() {
        // 로직
        return true; // 사용 가능
    }
}
