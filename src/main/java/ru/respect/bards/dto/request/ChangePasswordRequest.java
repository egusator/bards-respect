package ru.respect.bards.dto.request;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ChangePasswordRequest {
    String password;
}
