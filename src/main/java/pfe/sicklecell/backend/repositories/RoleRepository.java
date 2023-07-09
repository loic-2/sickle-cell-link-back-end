package pfe.sicklecell.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pfe.sicklecell.backend.models.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{

    Role findByName(String role);
    
}
