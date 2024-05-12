package com.project.CircoDelSol.service;

import java.util.List;

import com.project.CircoDelSol.config.ApiResponse;
import com.project.CircoDelSol.dto.AwardDto;
import com.project.CircoDelSol.dto.UserDto;
import com.project.CircoDelSol.entity.Award;

public interface AwardService {
  Award createAward(Award awardDto);

  ApiResponse<List<AwardDto>> getAllAwards();

  ApiResponse<String> modifyAwardQuantity(String operation, int quantity, String type);

}
