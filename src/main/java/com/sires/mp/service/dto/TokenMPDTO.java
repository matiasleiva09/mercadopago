package com.sires.mp.service.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenMPDTO {
   private String code;
   private String status;
   private LoginResponseDTO response;
   private boolean firstToken;
}
