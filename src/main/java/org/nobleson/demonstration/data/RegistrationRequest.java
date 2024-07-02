package org.nobleson.demonstration.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nobleson.demonstration.enums.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    private String password;
    private String userID;
    private String username;
    private Role role;
}
