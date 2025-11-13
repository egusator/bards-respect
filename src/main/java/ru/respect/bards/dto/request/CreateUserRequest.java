package ru.respect.bards.dto.request;

import lombok.Builder;
import lombok.Value;
import ru.respect.bards.entities.Role;

import java.util.Set;

@Builder
@Value
public class CreateUserRequest {
    String login;
    String password;
    String email;
    String name;
    Set<Role> roles;
}
