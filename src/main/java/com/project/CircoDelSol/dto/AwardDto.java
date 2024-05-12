package com.project.CircoDelSol.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AwardDto {
  private Long id;
  private String type;
  private Integer available;
}
