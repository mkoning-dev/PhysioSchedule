package com.physioschedule.dto;

import com.physioschedule.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private User.Role role;
}
