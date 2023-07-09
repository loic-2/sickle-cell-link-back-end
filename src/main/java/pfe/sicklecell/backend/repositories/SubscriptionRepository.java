package pfe.sicklecell.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pfe.sicklecell.backend.models.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long>{

    Subscription findByName(String subscription);
    
}
