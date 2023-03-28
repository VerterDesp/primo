package com.verter.model;

import com.verter.config.ConstraintsConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usr", uniqueConstraints = @UniqueConstraint(name = "uk_usr_email", columnNames = "email"))
@Entity
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Size(min = ConstraintsConstants.USER_NAME_MIN_SIZE, max = ConstraintsConstants.USER_NAME_MAX_SIZE)
  private String firstName;

  @Size(min = ConstraintsConstants.USER_NAME_MIN_SIZE, max = ConstraintsConstants.USER_NAME_MAX_SIZE)
  private String lastName;

  @Size(min = ConstraintsConstants.USER_EMAIL_MIN_SIZE, max = ConstraintsConstants.USER_EMAIL_MAX_SIZE)
  private String email;

  private String phoneNumber;

  @Column(nullable = false)
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "usr_role",
    joinColumns = @JoinColumn(name = "usr_id", foreignKey = @ForeignKey(name = "usr_role_usr_id_fkey")),
    inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "usr_role_role_id_fkey")))
  private List<Role> roles;

  public User(String firstName, String lastName, String email, String phoneNumber, String password, List<Role> roles) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.roles = roles;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
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
