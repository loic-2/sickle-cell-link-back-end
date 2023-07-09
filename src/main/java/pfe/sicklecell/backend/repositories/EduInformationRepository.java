package pfe.sicklecell.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pfe.sicklecell.backend.models.EduInformation;

public interface EduInformationRepository extends JpaRepository<EduInformation,Long>{
    
}
