package ru.respect.bards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.respect.bards.dto.request.ChangeUserDataRequest;
import ru.respect.bards.dto.request.CreateUserRequest;
import ru.respect.bards.dto.response.UserResponse;
import ru.respect.bards.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(
            @RequestBody ChangeUserDataRequest changeUserDataRequest,
            @PathVariable long id) {
        return userService.updateUser(changeUserDataRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<UserResponse> changePassword(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(null);
    }

}
