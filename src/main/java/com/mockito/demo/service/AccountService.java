package com.mockito.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mockito.demo.request.AccountDTO;
import com.mockito.demo.response.AccountResponse;

public interface AccountService {

  String save(AccountDTO accountDto);

  AccountResponse getAccount(Long id);

  void updateAccount(Long id, AccountDTO accountDTO);

  void deleteAccount(Long id);

  Page<AccountResponse> getAccounts(Pageable pageable);

}
