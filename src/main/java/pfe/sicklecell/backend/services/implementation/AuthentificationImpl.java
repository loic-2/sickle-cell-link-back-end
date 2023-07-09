package pfe.sicklecell.backend.services.implementation;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pfe.sicklecell.backend.dto.LogoutDto;
import pfe.sicklecell.backend.dto.RegisterDto;
import pfe.sicklecell.backend.mapping.MapPerson;
import pfe.sicklecell.backend.models.Doctor;
import pfe.sicklecell.backend.models.Patient;
import pfe.sicklecell.backend.models.Person;
import pfe.sicklecell.backend.models.Role;
import pfe.sicklecell.backend.models.Subscription;
import pfe.sicklecell.backend.repositories.DoctorRepository;
import pfe.sicklecell.backend.repositories.PatientRepository;
import pfe.sicklecell.backend.repositories.PersonRepository;
import pfe.sicklecell.backend.repositories.RoleRepository;
import pfe.sicklecell.backend.repositories.SubscriptionRepository;
import pfe.sicklecell.backend.responses.RessourceAlreadyExist;
import pfe.sicklecell.backend.responses.RessourceNotFound;
import pfe.sicklecell.backend.services.Authentification;

@Service
public class AuthentificationImpl implements Authentification{

    PersonRepository personRepository;
    PatientRepository patientRepository;
    DoctorRepository doctorRepository;
    SubscriptionRepository subscriptionRepository;
    BCryptPasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public AuthentificationImpl(PersonRepository personRepository,PatientRepository patientRepository,
    DoctorRepository doctorRepository,SubscriptionRepository subscriptionRepository,RoleRepository roleRepository){
        this.personRepository = personRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder= new BCryptPasswordEncoder();
    }

    /*@Override
    public LoginResponse login(LoginDto loginDto) throws RessourceNotFound{
        if (this.personRepository.findByEmail(loginDto.getEmail()) instanceof Doctor) {
            Doctor doctor= (Doctor) this.personRepository.findByEmail(loginDto.getEmail());
            if (passwordEncoder.matches(loginDto.getPassword(), doctor.getPassword())) {
                return MapPerson.mapPersonToLoginResponse(doctor);
            }
        }
        if (this.personRepository.findByEmail(loginDto.getEmail()) instanceof Patient) {
            Patient patient= (Patient) this.personRepository.findByEmail(loginDto.getEmail());
            if (passwordEncoder.matches(loginDto.getPassword(), patient.getPassword())) {
                return MapPerson.mapPersonToLoginResponse(patient);
            }
        }
        throw new RessourceNotFound("Identifiants invalides");

    }*/

    @Override
    public void registerPerson(RegisterDto registerDto) throws RessourceAlreadyExist, RessourceNotFound{
        Person person= MapPerson.mapRegisterDtoToPerson(registerDto);
        if (personRepository.findByLogin(person.getLogin())==null && personRepository.findByEmail(person.getEmail())==null
            && personRepository.findByPhone(person.getPhone())==null && personRepository.findByIdNumber(person.getIdNumber())==null) {
                person.setPassword(passwordEncoder.encode(person.getPassword()));
                if (registerDto.getRole() != null) {
                    person.setRole(this.roleRepository.findByName(registerDto.getRole()));
                }
                if (person instanceof Doctor) {
                Doctor doctor= (Doctor) person;
                if(doctorRepository.findByMatricule(doctor.getMatricule())==null){
                    doctorRepository.save(doctor);
                }else{
                    throw new RessourceAlreadyExist();
                }
            }
            if (person instanceof Patient) {
                Patient patient= (Patient) person;
                Subscription subscription = null;
                if (registerDto.getSubscription() != null) {
                    subscription= subscriptionRepository.findByName(registerDto.getSubscription());
                }
                if (subscription != null) {
                    patient.setSubscription(subscription);
                    patientRepository.save(patient);
                }else{
                    throw new RessourceNotFound("Unknown Subscription");
                }
            }
        }else{
            throw new RessourceAlreadyExist();
        }
    }

    @Override
    public void logout(LogoutDto logoutDto) {
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }

    @Override
    public void resetPassword(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'resetPassword'");
    }

    @Override
    public Person loadUserByUsername(String email) throws RessourceNotFound {
        Person person = this.personRepository.findByEmail(email);
        if(person == null) throw new RessourceNotFound("User doesn't exist");
        return person;
    }

    @Override
    public Role getUserRole(String email) throws RessourceNotFound {
        Person person = this.personRepository.findByEmail(email);
        if(person == null) throw new RessourceNotFound("User doesn't exist");
        return person.getRole();
    }
    
}
