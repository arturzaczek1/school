package pl.arturzaczek.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.school.entitis.Role;
import pl.arturzaczek.school.entitis.SchoolClass;
import pl.arturzaczek.school.entitis.User;
import pl.arturzaczek.school.forms.StudentForm;
import pl.arturzaczek.school.repositories.RoleRepository;
import pl.arturzaczek.school.repositories.SchoolClassRepository;
import pl.arturzaczek.school.repositories.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    public StudentService(UserRepository userRepository, RoleRepository roleRepository, SchoolClassRepository schoolClassRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.schoolClassRepository = schoolClassRepository;
    }

    public String registerStudent(StudentForm studentForm) {
        if (checkIfStudentExist(studentForm.getStudentEmail())) {
            return "Student with same email exist";
        } else {
            User user = new User();
            user.setAddedDate(LocalDateTime.now());
            user.setName(studentForm.getStudentName());
            user.setLastName(studentForm.getStudentLastName());
            user.setEmail(studentForm.getStudentEmail());
            user.setBirthDate(parseDate(studentForm.getBirthDate()));
            getORCreateStudentRole(user);
            userRepository.save(user);
            return "Student create successfully";
        }
    }

    private LocalDate parseDate(String date) {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, customFormatter);
        return localDate;
    }

    public boolean checkIfStudentExist(String email) {
        Optional<User> userOptional = userRepository.findFirstByEmail(email);
        if (userOptional.isPresent()) {
            return true;
        }
        return false;
    }

    private void getORCreateStudentRole(User user) {
        Role role = roleRepository.findByRoleName(Roles.ROLE_STUDENT.getRole())
                .orElseGet(() -> roleRepository.save(new Role(Roles.ROLE_STUDENT.toString())));
        user.addRole(role);
    }

    public List<User> getStudentsList() {
        List<User> allUser = userRepository.findAll();
        List<User> studentsList = allUser.stream()
                .filter(a -> a.getRoleSet().stream().anyMatch(b -> b.getRoleName().equals(Roles.ROLE_STUDENT.toString())))
                .collect(Collectors.toList());
        return studentsList;
    }

    public String connectWithSchoolClass(String student, String class_id) {
        Long classId = Long.parseLong(class_id);
        Long studentId = Long.parseLong(student);
        Optional<User> user = userRepository.findById(studentId);
        SchoolClass sc = schoolClassRepository.findById(classId).get();
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setSchoolClass(sc);
            userRepository.save(user1);
            return "student connected with class";
        }
        return "could not connect";
    }
}
