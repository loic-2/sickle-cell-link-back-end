package pfe.sicklecell.backend.mapping;

import pfe.sicklecell.backend.dto.LoginResponse;
import pfe.sicklecell.backend.dto.RegisterDto;
import pfe.sicklecell.backend.models.Doctor;
import pfe.sicklecell.backend.models.Patient;
import pfe.sicklecell.backend.models.Person;

public class MapPerson {
    public static Person mapRegisterDtoToPerson(RegisterDto registerDto){
        if (registerDto.getMatricule()!=null && registerDto.getDoctorType()!= null) {
            Doctor doctor= new Doctor();
            doctor.setAddress(registerDto.getAddress());
            doctor.setEmail(registerDto.getEmail());
            doctor.setLogin(registerDto.getLogin());
            doctor.setFirstname(registerDto.getFirstname());
            doctor.setHospital(registerDto.getHospital());
            doctor.setLastname(registerDto.getLastname());
            doctor.setIdNumber(registerDto.getIdNumber());
            doctor.setMatricule(registerDto.getMatricule());
            doctor.setDoctorType(registerDto.getDoctorType());
            doctor.setPassword(registerDto.getPassword());
            doctor.setPhone(registerDto.getPhone());
            return doctor;
        } else {
            Patient patient= new Patient();
            patient.setAddress(registerDto.getAddress());
            patient.setEmail(registerDto.getEmail());
            patient.setLogin(registerDto.getLogin());
            patient.setFirstname(registerDto.getFirstname());
            patient.setLastname(registerDto.getLastname());
            patient.setIdNumber(registerDto.getIdNumber());
            patient.setPassword(registerDto.getPassword());
            patient.setPhone(registerDto.getPhone());
            return patient;
        }
    }

    /* 
    public static LoginResponse mapPersonToLoginResponse(Person person){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAddress(person.getAddress());
        loginResponse.setEmail(person.getEmail());
        loginResponse.setFirstname(person.getFirstname());
        loginResponse.setLastname(person.getLastname());
        loginResponse.setIdNumber(person.getIdNumber());
        loginResponse.setPhone(person.getPhone());
        if (person.getRole()!=null) {
            loginResponse.setRole(person.getRole().getName());
        }
        if (person instanceof Doctor) {
            Doctor doctor = (Doctor) person;
            loginResponse.setMatricule(doctor.getMatricule());
            loginResponse.setHospital(doctor.getHospital());
            loginResponse.setDoctorType(doctor.getDoctorType());
        }
        if (person instanceof Patient) {
            Patient patient = (Patient) person;
            loginResponse.setSubscription(patient.getSubscription().getName());
        }
        return loginResponse;
    } */

    public static LoginResponse mapAuthToLoginResponse(String token,String role,Long id){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(token);
        loginResponse.setRole(role);
        loginResponse.setId(id);
        return loginResponse;
    }
}
