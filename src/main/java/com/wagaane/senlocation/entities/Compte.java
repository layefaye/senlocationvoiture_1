package com.wagaane.senlocation.entities;

import com.wagaane.senlocation.entities.enums.Role;
import com.wagaane.senlocation.security.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TD_COMPTE")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Compte implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "Com_Id", nullable = false, unique = true)
  private Long id;
  @Column(name = "Com_Email", nullable = false, unique = true, length = 50)
  @Email
  private String email;
  @Column(name = "Com_Password", nullable = false, length = 150)
  private String password;


  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "compte")
  private List<Token> tokens;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(role.name()));
    // Add more roles if needed
    return authorities;
  }


  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
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
