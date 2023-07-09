package pfe.sicklecell.backend.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Doctor extends Person{
    @Column(unique = true, nullable = false)
    private String matricule;
    @Column(nullable = false)
    private String hospital;
    @Column(nullable = false)
    private String doctorType;
    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    private List<Planning> plannings;
    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    private List<Information> informations;
}
