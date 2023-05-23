package com.example.demo_2305.domain;

import com.example.demo_2305.Annotations.ValidEmail;
import com.example.demo_2305.Annotations.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidPassword
@Table(name =  "t_user",indexes = {
        @Index(columnList = "firstName", unique = true),
        @Index(columnList = "lastName", unique = true),
        @Index(columnList = "email", unique = true),
        @Index(columnList = "password", unique = true),
        @Index(columnList = "firstName, lastName , password, email", unique = true),
})
public class UserDTO implements UserDetails {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @NotNull
    @NotEmpty
    @Column(name = "firstName",length = 128,nullable = false)
    private String firstName;

    @NotNull
    @NotEmpty
    @Column(name = "lastName",length = 128, nullable = false)
    private String lastName;

    @NotNull
    @NotEmpty
    @Column(name = "password",length = 512, nullable = false)
    private String password;
    @Transient
    private String confirmPassword;

    @NotNull
    @NotEmpty
    @ValidEmail
    @Column(name = "email",length = 128, nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
