package com.Backend.System.DTO;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    @NotBlank(message = "UserId is required")
    public Long userId;
    @NotBlank(message = "Username is requires")
    public String username;
    @NotBlank(message = "Telehpone is required")
    public String telephone;
}
