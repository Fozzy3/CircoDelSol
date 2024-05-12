package com.project.CircoDelSol.mapper;

import com.project.CircoDelSol.dto.UserDto;
import com.project.CircoDelSol.entity.Award;
import com.project.CircoDelSol.entity.User;

public class UserMapper {

  public static UserDto maptoUserDto(User user) {
   return new UserDto(
    user.getId(),
    user.getName(),
    user.getIdentity(),
    user.getBank(),
    user.getPrize()
   ); 
  }

  public static User mapToUser(UserDto userDto){
    return new User(
      userDto.getId(),
      userDto.getName(),
      userDto.getIdentity(),
      userDto.getBank(),
      userDto.getPrize()
    );
  }
}
