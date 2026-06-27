package org.example.gmup.core.dto;

public record SaveNewUserCommand(
         String firstname,
         String lastname,
         String username,
         String password
) {
}
