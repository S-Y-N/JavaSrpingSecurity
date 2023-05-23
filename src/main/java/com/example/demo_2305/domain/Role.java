package com.example.demo_2305.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="t_role")
public class Role implements GrantedAuthority {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="name", length = 128, nullable = false)
    String name;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<UserDTO> users;
    @Override
    public String getAuthority() {
        return getName();
    }
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
