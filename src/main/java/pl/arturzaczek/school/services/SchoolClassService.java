package pl.arturzaczek.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.school.entitis.SchoolClass;
import pl.arturzaczek.school.entitis.User;
import pl.arturzaczek.school.forms.SchoolClassRegisterForm;
import pl.arturzaczek.school.repositories.SchoolClassRepository;
import pl.arturzaczek.school.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolClassService {

    private SchoolClassRepository schoolClassRepository;
    private UserRepository userRepository;

    @Autowired
    public SchoolClassService(SchoolClassRepository schoolClassRepository, UserRepository userRepository) {
        this.schoolClassRepository = schoolClassRepository;
        this.userRepository = userRepository;
    }

    public List<SchoolClass> getAllSC() {
        List<SchoolClass> schoolClassList = schoolClassRepository.findAll();
        return schoolClassList;
    }

    public String registerSC(SchoolClassRegisterForm scRegisterForm) {
        if (checkIfClassExist(scRegisterForm.getClassName())) {
            return "school class already exist";
        } else {
            SchoolClass sc = new SchoolClass();
            sc.setClassName(scRegisterForm.getClassName());
            sc.setClassProfile(scRegisterForm.getClassProfile());
            schoolClassRepository.save(sc);
            return "school class registered successfully";
        }
    }

    private boolean checkIfClassExist(String className) {
        Optional<SchoolClass> optionalSchoolClass = schoolClassRepository.findByClassName(className);
        if (optionalSchoolClass.isPresent()) {
            return true;
        } else
            return false;
    }

}
