package pfe.sicklecell.backend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RessourceNotFound extends Exception{

    private String message;
    
}
