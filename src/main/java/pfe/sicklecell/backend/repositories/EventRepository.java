package pfe.sicklecell.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pfe.sicklecell.backend.models.Event;

public interface EventRepository extends JpaRepository<Event,Long>{
    
}
