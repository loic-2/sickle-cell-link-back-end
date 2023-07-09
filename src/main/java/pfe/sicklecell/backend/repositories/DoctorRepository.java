package pfe.sicklecell.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pfe.sicklecell.backend.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long>{

    Doctor findByMatricule(String matricule);
    
}
