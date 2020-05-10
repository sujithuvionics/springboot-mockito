package com.mockito.demo.serviceimpl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mockito.demo.converter.AccountConverter;
import com.mockito.demo.converter.Converter;
import com.mockito.demo.exceptions.NotFoundException;
import com.mockito.demo.model.Account;
import com.mockito.demo.repo.AccountRepository;
import com.mockito.demo.request.AccountDTO;
import com.mockito.demo.response.AccountResponse;
import com.mockito.demo.service.AccountService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  private final AccountConverter accountConverter;

  @Override
  public String save(AccountDTO accountDto) {
    Account account = new Account();
    account = new Converter<Account, AccountDTO>().convert(account, accountDto);
    try {
    accountRepository.save(account);
    }catch (Exception e) {
     e.printStackTrace();
    }
    return "Account craeted";
  }

  @Override
  public AccountResponse getAccount(Long id) {
    AccountResponse accountResponse = new AccountResponse();
    Account account =
        accountRepository.findById(id).orElseThrow(() -> new NotFoundException("No Account"));
    return new Converter<AccountResponse, Account>().convert(accountResponse, account);
  }

  @Override
  public void updateAccount(Long id, AccountDTO accountDTO) {
    Account account = getAccountData(id);
    account = new Converter<Account, AccountDTO>().convert(account, accountDTO);
    account.setId(account.getId());
    accountRepository.save(account);
  }

  @Override
  public void deleteAccount(Long id) {
    Account accountData = getAccountData(id);
    accountRepository.delete(accountData);
  }

  @Override
  public Page<AccountResponse> getAccounts(Pageable pageable) {
    Page<Account> page = accountRepository.findAll(pageable);
    List<AccountResponse> accountResponse = accountConverter.convert(page.getContent());
    return new PageImpl<>(accountResponse, pageable, page.getTotalElements());
  }


  private Account getAccountData(Long id) {
    return accountRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("No account on this id"));
  }


}
