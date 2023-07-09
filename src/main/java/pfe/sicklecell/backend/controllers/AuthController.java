package pfe.sicklecell.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pfe.sicklecell.backend.auth.GenerateToken;
import pfe.sicklecell.backend.dto.LoginDto;
import pfe.sicklecell.backend.dto.LoginResponse;
import pfe.sicklecell.backend.dto.RegisterDto;
import pfe.sicklecell.backend.repositories.PersonRepository;
import pfe.sicklecell.backend.responses.RessourceAlreadyExist;
import pfe.sicklecell.backend.responses.RessourceNotFound;
import pfe.sicklecell.backend.services.implementation.AuthentificationImpl;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private AuthentificationImpl authentificationImpl;
    private GenerateToken generateToken;

    public AuthController(AuthentificationImpl authentificationImpl,JwtEncoder jwtEncoder,
                AuthenticationManager authenticationManager,PersonRepository personRepository){
        this.authentificationImpl= authentificationImpl;
        this.generateToken = new GenerateToken(jwtEncoder,authenticationManager,personRepository);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> Authenticate(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<LoginResponse>(this.generateToken.generateToken(loginDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        try {
            this.authentificationImpl.registerPerson(registerDto);
        } catch (RessourceAlreadyExist | RessourceNotFound e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Successfull created", HttpStatus.CREATED);
    }
}
