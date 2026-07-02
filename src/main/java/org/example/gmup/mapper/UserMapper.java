package org.example.gmup.mapper;

import org.example.gmup.adapter.inbound.dto.user.UserProfileMenu;
import org.example.gmup.adapter.outbound.entity.UserEntity;
import org.example.gmup.core.dto.SaveNewUserCommand;
import org.example.gmup.core.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomainFromSaveNewUser(SaveNewUserCommand saveNewUserCommand);

    User toDomainFromUserEntity(UserEntity userEntity);

    UserEntity toUserEntityFromUser(User user);

    UserProfileMenu fromUserToUserProfileMenu(User user);

}
