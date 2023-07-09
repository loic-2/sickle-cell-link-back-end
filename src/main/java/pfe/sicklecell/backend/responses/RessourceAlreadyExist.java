package pfe.sicklecell.backend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RessourceAlreadyExist extends Exception {
    private String message ="These informations are already used";
}
