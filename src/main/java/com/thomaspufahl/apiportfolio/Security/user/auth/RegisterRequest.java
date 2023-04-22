package com.thomaspufahl.apiportfolio.Security.user.auth;

import com.thomaspufahl.apiportfolio.Security.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
//    private String firstname;
//    private String lastname;
    private String email;
    private String password;
    private Role role;

    public Role setRoleWithDefaultValue() {
        if (getRole()!=null) {
            return getRole();
        }
        return Role.USER;
    }
}
