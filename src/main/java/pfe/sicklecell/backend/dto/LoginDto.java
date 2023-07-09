package pfe.sicklecell.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class LoginDto {
    private String login;
    private String email;
    private Boolean remember;
    private String password;
}
