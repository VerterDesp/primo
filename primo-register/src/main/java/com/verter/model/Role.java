package com.verter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Role implements GrantedAuthority {

  @Id
  private String id;

  @ManyToMany(mappedBy = "roles")
  private List<User> users;

  public Role(String id) {
    this.id = id;
  }

  @Override
  public String getAuthority() {
    return id;
  }
}
