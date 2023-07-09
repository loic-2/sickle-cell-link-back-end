package pfe.sicklecell.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pfe.sicklecell.backend.models.Information;

public interface InformationRepository extends JpaRepository<Information,Long>{
    
}
