package pfe.sicklecell.backend.services;

import org.springframework.stereotype.Service;

import pfe.sicklecell.backend.dto.LogoutDto;
import pfe.sicklecell.backend.dto.RegisterDto;
import pfe.sicklecell.backend.models.Person;
import pfe.sicklecell.backend.models.Role;
import pfe.sicklecell.backend.responses.RessourceAlreadyExist;
import pfe.sicklecell.backend.responses.RessourceNotFound;

@Service
public interface Authentification {
    //LoginResponse login(LoginDto loginDto) throws RessourceNotFound;
    void registerPerson(RegisterDto registerDto) throws RessourceAlreadyExist, RessourceNotFound;
    void logout(LogoutDto logoutDto);
    void resetPassword(String email);
    Person loadUserByUsername(String email) throws RessourceNotFound;
    Role getUserRole(String email) throws RessourceNotFound;
}
