package br.ufpb.dcx.project.repository;

import br.ufpb.dcx.project.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryDonate extends JpaRepository<Donation, Long> {

    List<Donation> findAllByOrderByDateDonateAsc();

}