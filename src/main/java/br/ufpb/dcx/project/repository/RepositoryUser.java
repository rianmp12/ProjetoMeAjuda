package br.ufpb.dcx.project.repository;

import br.ufpb.dcx.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryUser extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
