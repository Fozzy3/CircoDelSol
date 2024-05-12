package com.project.CircoDelSol.service;

import com.project.CircoDelSol.config.ApiResponse;
import com.project.CircoDelSol.dto.UserDto;

public interface UserService {
  UserDto createUser(UserDto userDto);

  ApiResponse<String> modifyAwardQuantity(String operation, int quantity, String type);

  UserDto findByType(Integer identity);

  ApiResponse<UserDto> findByIdentity(Integer identity); 

}
