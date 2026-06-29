package org.example.gmup.core.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.factory_core_manager.core.model.enumerated.UserType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public User(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }
    private Long id ;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private List<Long> fileList;



}
