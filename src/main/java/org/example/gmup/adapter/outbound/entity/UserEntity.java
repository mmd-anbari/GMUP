package org.example.gmup.adapter.outbound.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.factory_core_manager.core.model.enumerated.UserType;

@Entity
@Table(name = "user_table")
@Getter
@Setter
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;



}
