package com.project.CircoDelSol.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.project.CircoDelSol.config.ApiResponse;
import com.project.CircoDelSol.dto.AwardDto;
import com.project.CircoDelSol.entity.Award;
import com.project.CircoDelSol.mapper.AwardMapper;
import com.project.CircoDelSol.reposity.AwardRepository;
import com.project.CircoDelSol.service.AwardService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AwardServiceImpl implements AwardService {

  private AwardRepository awardRepository;

  @Override
  public Award createAward(Award awardDto) {
    Award award = AwardMapper.mapToAward(awardDto);
    Award savedAward = awardRepository.save(award);
    return AwardMapper.mapToAward(savedAward);
  }

  @Override
  public ApiResponse<String> modifyAwardQuantity(String operation, int quantity, String type) {
    Optional<Award> awardOptional = awardRepository.findByType(type);
    if (!awardOptional.isPresent()) {
      return new ApiResponse<>(false, "Award type not found");
    }
    Award award = awardOptional.get();
    if ("add".equalsIgnoreCase(operation)) {
      award.setAvailable(award.getAvailable() + quantity);
    } else if ("subtract".equalsIgnoreCase(operation)) {
      int newQuantity = award.getAvailable() - quantity;
      if (newQuantity < 0) {
        return new ApiResponse<>(false, "Insufficient awards available");
      }
      award.setAvailable(newQuantity);
    } else {
      return new ApiResponse<>(false, "Invalid operation");
    }
    awardRepository.save(award);
    return new ApiResponse<>(true, "Award quantity updated successfully");
  }

  @Override
  public ApiResponse<List<AwardDto>> getAllAwards() {
    List<Award> awards = awardRepository.findAll();
    List<AwardDto> awardDtos = awards.stream()
        .map(AwardMapper::maptoAwardDto)
        .collect(Collectors.toList());
    return new ApiResponse<>(true, awardDtos, "All awards fetched successfully");
  }

}
