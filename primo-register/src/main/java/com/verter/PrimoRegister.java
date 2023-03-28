package com.verter;

import com.verter.model.Role;
import com.verter.model.constant.RoleConst;
import com.verter.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PrimoRegister {
  private final RoleRepository roleRepository;

  public PrimoRegister(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(PrimoRegister.class, args);
  }

  @Bean
  CommandLineRunner runner()  {
    return args -> {
      Role userRole = new Role(RoleConst.USER);
      Role adminRole = new Role(RoleConst.ADMIN);
      roleRepository.saveAll(List.of(userRole, adminRole));
    };
  }
}