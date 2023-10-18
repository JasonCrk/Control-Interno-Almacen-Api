package com.ADS2.controlinternoalmacenapi.model;

import com.ADS2.controlinternoalmacenapi.model.enums.Role;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String avatarUrl;

    @Column(nullable = false, columnDefinition = "VARCHAR(30)")
    private String firstName;

    @Column(nullable = false, columnDefinition = "VARCHAR(30)")
    private String lastName;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(200)")
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(8)")
    private String dni;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(11)")
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "assigned")
    private List<Memorandum> assignedMemorandums;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
