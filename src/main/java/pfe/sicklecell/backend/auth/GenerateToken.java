package pfe.sicklecell.backend.auth;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import pfe.sicklecell.backend.dto.LoginDto;
import pfe.sicklecell.backend.dto.LoginResponse;
import pfe.sicklecell.backend.mapping.MapPerson;
import pfe.sicklecell.backend.models.Doctor;
import pfe.sicklecell.backend.models.Patient;
import pfe.sicklecell.backend.models.Person;
import pfe.sicklecell.backend.repositories.PersonRepository;

public class GenerateToken {
    
    private JwtEncoder jwtEncoder;

    private AuthenticationManager authenticationManager;

    @Autowired
    private PersonRepository personRepository;

    public GenerateToken(JwtEncoder jwtEncoder,AuthenticationManager authenticationManager,PersonRepository personRepository){
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
        this.personRepository = personRepository;
    }

    public LoginResponse generateToken(LoginDto loginDto){
        //AuthenticationManager authenticationManager;
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );
        Map<String,Object> map = getRole(loginDto.getEmail());
        String role = map.get("role").toString();
        Long id = (Long) map.get("id");
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet =  JwtClaimsSet.builder()
                                                    .subject(authentication.getName())
                                                    .issuedAt(instant)
                                                    .expiresAt(instant.plus(35, ChronoUnit.MINUTES))
                                                    .claim("scope", role)
                                                    .issuer("Sickle Cell Link Inc.")
                                                    .build();
        String token = this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        return MapPerson.mapAuthToLoginResponse(token, role,id);
    }

    private Map<String,Object> getRole(String email){
        Map<String,Object> result = new HashMap<>();
        String role = null;
        Person person = this.personRepository.findByEmail(email);
        Long id = person.getId();
        if (person instanceof Doctor) {
            role = "doctor";
        }
        if (person instanceof Patient) {
            role = "patient";
        }
        result.put("role", role);
        result.put("id", id);
        return result;
    }
}
