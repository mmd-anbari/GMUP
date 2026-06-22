package org.example.gmup.core.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.factory_core_manager.core.model.enumerated.UserType;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private Long id ;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private UserType userType;

    public boolean isValidPassword() {
        return password != null && !password.isEmpty();
    }

}
