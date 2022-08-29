package io.kodikuldeep.repository;

import java.util.Optional;

import io.kodikuldeep.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, String> {
	Optional<AppUser> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
