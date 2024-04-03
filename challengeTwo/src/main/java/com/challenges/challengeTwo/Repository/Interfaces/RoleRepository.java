package com.challenges.challengeTwo.Repository.Interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenges.challengeTwo.Entity.ERole;
import com.challenges.challengeTwo.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
