package ru.respect.bards.dto.response;

import lombok.Builder;
import lombok.Value;
import ru.respect.bards.entities.Role;

import java.util.Set;

@Builder
@Value
public class UserResponse {
    Long id;
    String login;
    String email;
    String name;
    Set<Role> roles;
}
