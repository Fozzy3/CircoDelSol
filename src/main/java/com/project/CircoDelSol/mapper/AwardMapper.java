package com.project.CircoDelSol.mapper;
import com.project.CircoDelSol.dto.AwardDto;
import com.project.CircoDelSol.entity.Award;

public class AwardMapper {

  public static AwardDto maptoAwardDto(Award award) {
   return new AwardDto(
    award.getId(),
    award.getType(),
    award.getAvailable()
   ); 
  }

  public static Award mapToAward(Award awardDto){
    return new Award(
      awardDto.getId(),
      awardDto.getType(),
      awardDto.getAvailable()   
         
    );
  } 
}
