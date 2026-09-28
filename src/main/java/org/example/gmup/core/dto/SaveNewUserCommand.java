package org.example.gmup.core.dto;

public record SaveNewUserCommand(
         String firstname,
         String lastname,
         String username,
         String password,
         long storageLimitLeft
) {

    public SaveNewUserCommand withStorageLimitLeft(long storageLimitLeft) {
        return new SaveNewUserCommand(firstname, lastname, username, password, storageLimitLeft);
    }

}
