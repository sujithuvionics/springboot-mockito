package com.mockito.demo.request;

import javax.validation.constraints.NotNull;
import com.mockito.demo.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

  @NotNull(message = "Name can't be empty")
  private String name;

  @NotNull(message = "Email can't be empty")
  private String email;
  
  @NotNull(message = "Address can't be empty")
  private String address;
  
  @NotNull(message = "Account can't be empty")
  private AccountType accountType;
  
}
