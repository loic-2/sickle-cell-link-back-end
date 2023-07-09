package pfe.sicklecell.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pfe.sicklecell.backend.models.Person;

public interface PersonRepository extends JpaRepository<Person,Long>{

    Person findByLogin(String login);

    Person findByPhone(String phone);

    Person findByIdNumber(String idNumber);

    Person findByEmail(String email);
    
}
