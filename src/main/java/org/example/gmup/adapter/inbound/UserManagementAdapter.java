package org.example.gmup.adapter.inbound;

import org.example.gmup.core.dto.SaveNewUserCommand;
import org.example.gmup.mapper.UserMapper;
import org.example.gmup.port.inbound.user.SignUpUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserManagementAdapter {

    private SignUpUC signUpUC;

    private UserMapper userMapper;

    @Autowired
    public UserManagementAdapter(UserMapper userMapper , SignUpUC signUpUC) {
        this.userMapper = userMapper;
        this.signUpUC = signUpUC;
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SaveNewUserCommand saveNewUserCommand) {

        signUpUC.signUp(saveNewUserCommand);

        return ResponseEntity.ok("Sign up successful");

    }

}
