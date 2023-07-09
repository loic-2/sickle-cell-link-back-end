package pfe.sicklecell.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class RegisterDto {
    private String firstname;
    private String lastname;
    private String idNumber;
    private String email;
    private String phone;
    private String address;
    private String login;
    private String password;
    private String matricule;
    private String hospital;
    private String doctorType;
    private String subscription;
    private String role;
}
