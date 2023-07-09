package pfe.sicklecell.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pfe.sicklecell.backend.models.Planning;

public interface PlanningRepository extends JpaRepository<Planning,Long>{
    
}
