package br.ufpb.dcx.project.repository;

import br.ufpb.dcx.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<User, Long> {
}
