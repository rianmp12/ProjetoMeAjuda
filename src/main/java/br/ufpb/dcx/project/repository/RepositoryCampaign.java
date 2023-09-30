package br.ufpb.dcx.project.repository;

import br.ufpb.dcx.project.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCampaign extends JpaRepository<Campaign,Long> {
}
