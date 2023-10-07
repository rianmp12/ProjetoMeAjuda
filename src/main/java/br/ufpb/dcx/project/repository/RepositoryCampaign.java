package br.ufpb.dcx.project.repository;

import br.ufpb.dcx.project.model.Campaign;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepositoryCampaign extends JpaRepository<Campaign,Long> {
    List<Campaign> findByStatusTrueAndRemovedFalseOrderByTitleAsc();
    List<Campaign> findByStatusTrueAndRemovedFalseOrderByCreationDateAsc();
    List<Campaign> findByStatusFalseAndRemovedFalseOrderByCreationDateAsc();
    @Query("Select C from Campaign C where C.meta <= (Select coalesce(Sum(D.donate),0) from C.collected D)")
    List<Campaign> findByAchieveGoal();
}

