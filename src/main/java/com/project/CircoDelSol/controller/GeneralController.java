package com.project.CircoDelSol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.CircoDelSol.config.ApiResponse;
import com.project.CircoDelSol.dto.AwardDto;
import com.project.CircoDelSol.dto.AwardModificationRequest;
import com.project.CircoDelSol.dto.UserDto;
import com.project.CircoDelSol.entity.Award;
import com.project.CircoDelSol.service.AwardService;
import com.project.CircoDelSol.service.UserService;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@RestController
public class GeneralController {

  @Autowired
  private UserService userService;

  @Autowired
  private AwardService awardService;


  //For create  new user
  @RequestMapping("/api/user")
  @PostMapping
  public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody UserDto userDto) {
    try {
      UserDto savedUserDto = userService.createUser(userDto);
      ApiResponse<UserDto> apiResponse = new ApiResponse<>(true, savedUserDto, "User created successfully");
      return ResponseEntity.ok(apiResponse);
    } catch (IllegalStateException e) {
      ApiResponse<UserDto> apiResponse = new ApiResponse<>(false, null, "Unauthorized: Identity repeat");
      return ResponseEntity
          .status(HttpStatus.CONFLICT)
          .body(apiResponse);
    }
  }

  @GetMapping("/api/awards")
    public ResponseEntity<ApiResponse<List<AwardDto>>> getAllAwards() {
        ApiResponse<List<AwardDto>> response = awardService.getAllAwards();
        return ResponseEntity.ok(response);
    }
  

  @PostMapping("/api/modify-award")
  public ResponseEntity<ApiResponse<String>> modifyAward(@RequestBody AwardModificationRequest request) {
    if (!"yourSecretPassword".equals(request.getSecretPassword())) {
      // Using the constructor that only requires a message
      ApiResponse<String> apiResponse = new ApiResponse<>(false, "Unauthorized: Incorrect password");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }



  // For add new awards
  // @PostMapping("/api/awards")
  // public ResponseEntity<Award> createAward(@RequestBody Award award) {
  //   Award savedAward = awardService.createAward(award);
  //   return new ResponseEntity<>(savedAward, HttpStatus.CREATED);
  // }


    ApiResponse<String> response = awardService.modifyAwardQuantity(request.getOperation(), request.getQuantity(),
        request.getType());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/api/user/{identity}")
  public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable("identity") Integer identity) {
    ApiResponse<UserDto> response = userService.findByIdentity(identity);
    return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
  }
  
}