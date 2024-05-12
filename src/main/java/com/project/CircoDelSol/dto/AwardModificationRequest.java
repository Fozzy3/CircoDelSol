package com.project.CircoDelSol.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AwardModificationRequest {
  private String operation; 
  private int quantity;
  private String type;
  private String secretPassword;
}
