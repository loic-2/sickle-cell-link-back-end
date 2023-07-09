package pfe.sicklecell.backend.services.implementation;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pfe.sicklecell.backend.models.Person;
import pfe.sicklecell.backend.responses.RessourceNotFound;
import pfe.sicklecell.backend.services.Authentification;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

    private Authentification authentification;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Person person = authentification.loadUserByUsername(username);
            UserDetails userDetails = User
                                        .withUsername(person.getEmail())
                                        .password(person.getPassword())
                                        .roles(person.getRole().getName())
                                        .build();
            return userDetails;
        } catch (RessourceNotFound e) {
            throw new UsernameNotFoundException(String.format("User %s doesn't exist", username));
        }
    }
    
}
