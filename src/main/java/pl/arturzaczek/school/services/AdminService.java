package pl.arturzaczek.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.school.dtos.UserDto;
import pl.arturzaczek.school.entitis.User;
import pl.arturzaczek.school.repositories.UserRepository;

import java.util.Optional;

@Service
public class AdminService {

    private UserRepository userRepository;

    @Autowired
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserDto(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            UserDto userDto = UserDto.builder()
                    .id(userOptional.get().getId())
                    .name(userOptional.get().getName())
                    .lastName(userOptional.get().getLastName())
                    .addedDate(userOptional.get().getAddedDate())
                    .birthDate(userOptional.get().getBirthDate())
                    .email(userOptional.get().getEmail())
                    .roleSet(userOptional.get().getRoleSet()).build();
            return userDto;
        }
        return UserDto.builder().name("unknown user").build();
    }
}
