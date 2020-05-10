package com.mockito.demo.controller;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mockito.demo.request.AccountDTO;
import com.mockito.demo.response.AccountResponse;
import com.mockito.demo.service.AccountService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;
  
  @ResponseStatus(code = HttpStatus.CREATED)
  public void saveAccountDetails(@RequestBody @Valid AccountDTO accountDto) {
    accountService.save(accountDto);
  }

  @GetMapping("{id}")
  public AccountResponse getAccount(@PathVariable Long id) {
    return accountService.getAccount(id);
  }

  @PutMapping("{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
    accountService.updateAccount(id, accountDTO);
  }

  @DeleteMapping("{id}")
  public void deleteAccount(@PathVariable Long id) {
    accountService.deleteAccount(id);
  }

  @GetMapping
  public Page<AccountResponse> getAccounts(@PageableDefault(size = 10) Pageable pageable) {
    return accountService.getAccounts(pageable);
  }
  
}
