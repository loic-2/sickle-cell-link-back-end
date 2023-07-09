package pfe.sicklecell.backend.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import pfe.sicklecell.backend.models.Doctor;
import pfe.sicklecell.backend.models.EduInformation;
import pfe.sicklecell.backend.models.Event;
import pfe.sicklecell.backend.models.Patient;
import pfe.sicklecell.backend.models.Planning;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(
      RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Doctor.class);
        config.exposeIdsFor(Event.class);
        config.exposeIdsFor(Planning.class);
        config.exposeIdsFor(EduInformation.class);
        config.exposeIdsFor(Patient.class);
        config.exposeIdsFor(Planning.class);
    }
}