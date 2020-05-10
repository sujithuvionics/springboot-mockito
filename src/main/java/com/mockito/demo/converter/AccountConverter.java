package com.mockito.demo.converter;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.mockito.demo.model.Account;
import com.mockito.demo.response.AccountResponse;

@Component
public class AccountConverter implements Converter<Account, AccountResponse> {

  @Override
  public AccountResponse convert(Account source) {
    AccountResponse target = new AccountResponse();
    target.setAccountType(source.getAccountType());
    target.setAddress(source.getAddress());
    target.setEmail(source.getEmail());
    target.setId(source.getId());
    target.setName(source.getName());
    return target;
  }

  public List<AccountResponse> convert(List<Account> accounts) {
    System.err.println("--------------------------------------");
    return accounts.stream().map(this::convert).collect(Collectors.toList());
  }

}
