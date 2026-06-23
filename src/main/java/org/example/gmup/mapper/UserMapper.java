package org.example.gmup.mapper;

import org.example.gmup.core.model.User;
import org.example.gmup.core.model.dto.user.UserSignupDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toModel(UserSignupDto userSignupDto);
}
