package com.rmbartolome.agence.repository.security;

import com.rmbartolome.agence.models.security.ERole;
import com.rmbartolome.agence.models.security.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
