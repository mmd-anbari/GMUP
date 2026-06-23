package org.example.gmup.core.model.dto.user;


import org.example.factory_core_manager.core.model.enumerated.UserType;

public record UserSignupDto(
         String firstname,
         String lastname,
         String username,
         String password,
         UserType userType) {
}
