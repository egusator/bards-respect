package ru.respect.bards.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.respect.bards.dto.request.ChangeUserDataRequest;
import ru.respect.bards.dto.request.CreateUserRequest;
import ru.respect.bards.dto.response.UserResponse;
import ru.respect.bards.entities.User;
import ru.respect.bards.repository.RoleRepository;
import ru.respect.bards.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public UserResponse createUser(CreateUserRequest userDto) {
        userRepository.findByLogin(userDto.getLogin()).ifPresent(u -> {
            throw new IllegalArgumentException("User with login \"" + u.getLogin() + "\" already exists");
        });

        userRepository.findByEmail(userDto.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("User with email \"" + u.getLogin() + "\" already exists");
        });

        User user = User.builder()
                .login(userDto.getLogin())
                .name(userDto.getName())
                .roles(userDto.getRoles())
                .email(userDto.getEmail())
                .build();

        user.setPassword("default");

        userRepository.save(user);

        return UserResponse.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .login(userDto.getLogin())
                .roles(userDto.getRoles())
                .build();
    }

    public List<UserResponse> getAllUsers() {
        List<User> foundUsers = userRepository.findAll();
        return foundUsers.stream()
                .map(user ->
                        UserResponse.builder()
                                .id(user.getId())
                                .email(user.getEmail())
                                .roles(user.getRoles())
                                .login(user.getLogin())
                                .name(user.getName())
                                .build()
                ).toList();
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User by id:\"" + id + "\" does not exists")
        );

        return UserResponse.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }

    @Transactional
    public UserResponse updateUser(ChangeUserDataRequest userDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User by id:\"" + id + "\" does not exists")
        );

        userRepository.findByLogin(userDto.getLogin()).ifPresent(u -> {
            throw new IllegalArgumentException("User with login \"" + u.getLogin() + "\" already exists");
        });

        userRepository.findByEmail(userDto.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("User with email \"" + u.getLogin() + "\" already exists");
        });

        User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .login(userDto.getLogin())
                .roles(userDto.getRoles())
                .build();

        userRepository.save(user);

        return UserResponse.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .login(userDto.getLogin())
                .roles(userDto.getRoles())
                .build();
    }

    @Transactional
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

}
