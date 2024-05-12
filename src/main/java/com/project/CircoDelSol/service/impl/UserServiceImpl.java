package com.project.CircoDelSol.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.CircoDelSol.config.ApiResponse;
import com.project.CircoDelSol.dto.UserDto;
import com.project.CircoDelSol.entity.User;
import com.project.CircoDelSol.mapper.UserMapper;
import com.project.CircoDelSol.reposity.UserRepository;
import com.project.CircoDelSol.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl  implements UserService{
  
  private UserRepository userRepository;

  @Override
  public UserDto createUser(UserDto userDto){
      // Check if user with the same identity already exists
      Optional<User> existingUser = userRepository.findByIdentity(userDto.getIdentity());
      if (existingUser.isPresent()) {
          // Throw an exception or handle it based on your application's requirements
          throw new IllegalStateException("Identity already exists.");
      }
  
      User user = UserMapper.mapToUser(userDto);
      User savedUser = userRepository.save(user);
      return UserMapper.maptoUserDto(savedUser);
  }

  @Override
  public ApiResponse<String> modifyAwardQuantity(String operation, int quantity, String type) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'modifyAwardQuantity'");
  }

  @Override
  public UserDto findByType(Integer identity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByType'");
  }

  @Override
  public ApiResponse<UserDto> findByIdentity(Integer identity) {
    Optional<User> user = userRepository.findByIdentity(identity);
    if (user.isPresent()) {
      UserDto userDto = UserMapper.maptoUserDto(user.get());
      return new ApiResponse<>(true, userDto, "User found");
    } else {
      return new ApiResponse<>(false, null, "User with identity " + identity + " not found");
    }
  }
}
