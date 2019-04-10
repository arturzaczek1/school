package pl.arturzaczek.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.school.entitis.SchoolClass;
import pl.arturzaczek.school.entitis.User;
import pl.arturzaczek.school.repositories.SchoolClassRepository;
import pl.arturzaczek.school.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeachersService {

    private UserRepository userRepository;
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    public TeachersService(UserRepository userRepository, SchoolClassRepository schoolClassRepository) {
        this.userRepository = userRepository;
        this.schoolClassRepository = schoolClassRepository;
    }

    public List<User> getTeachersList() {
        List<User> allUser = userRepository.findAll();
        List<User> teachersList = allUser.stream()
                .filter(a -> a.getRoleSet().stream().anyMatch(b -> b.getRoleName().equals(Roles.ROLE_TEACHER.toString())))
                .collect(Collectors.toList());
        return teachersList;
    }

    public String addTeacherToClass(String teacher, String class_id) {
        Long classId = Long.parseLong(class_id);
        Long teacherId = Long.parseLong(teacher);
        Optional<SchoolClass> schoolClassOptional = schoolClassRepository.findById(classId);
        Optional<User> teacherOptional = userRepository.findById(teacherId);
        if (schoolClassOptional.isPresent()) {
            SchoolClass schoolClass = schoolClassOptional.get();
            schoolClass.setEducator(teacherOptional.get());
            schoolClassRepository.save(schoolClass);
            return "teacher connected with class";
        }
        return "could not connect";
    }
}
