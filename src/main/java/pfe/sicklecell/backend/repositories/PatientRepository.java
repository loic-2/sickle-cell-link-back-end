package pfe.sicklecell.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pfe.sicklecell.backend.models.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long>{
    
}
