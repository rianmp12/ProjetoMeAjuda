package br.ufpb.dcx.project.repository;

import br.ufpb.dcx.project.model.Campaign;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCampaign extends JpaRepository<Campaign,Long> {
    List<Campaign> findByStatusTrueRemovedFalse();
}
