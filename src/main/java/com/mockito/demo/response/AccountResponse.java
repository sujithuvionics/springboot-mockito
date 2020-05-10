package com.mockito.demo.response;

import com.mockito.demo.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse {
  
  private Long id;
  
  private String name;

  private String email;
  
  private String address;
  
  private AccountType accountType;
}
