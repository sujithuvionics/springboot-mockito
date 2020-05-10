package com.mockito.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.mockito.demo.converter.AccountConverter;
import com.mockito.demo.enums.AccountType;
import com.mockito.demo.model.Account;
import com.mockito.demo.repo.AccountRepository;
import com.mockito.demo.request.AccountDTO;
import com.mockito.demo.response.AccountResponse;
import com.mockito.demo.serviceimpl.AccountServiceImpl;

@SpringBootTest
class MockitoTestForServiceApplicationTests {

  @Mock
  private AccountRepository accountRepository;

  @Mock
  private AccountConverter accountConverter;

  @InjectMocks
  private AccountServiceImpl accountService;

  private Account account;

  @BeforeEach
  void createAccount() {
    account = createDummyAccount();
  }

  @Test
  void saveAccount() {
    when(accountRepository.save(account)).thenReturn(account);
    String save = accountService.save(new AccountDTO());
    assertEquals(save, "Account craeted");
  }

  @Test
  void getAValidAccount() {
    when(accountRepository.findById(1l)).thenReturn(Optional.of(account));
    AccountResponse accountResponse = accountService.getAccount(1l);
    assertEquals(accountResponse.getAccountType(), account.getAccountType());
    assertEquals(accountResponse.getAddress(), account.getAddress());
    assertEquals(accountResponse.getEmail(), account.getEmail());
    assertNotNull(accountResponse.getId());
    assertEquals(accountResponse.getName(), account.getName());
  }

  @Test
  void getPageOfAccount() {
    int totalElements = 1000;
    PageRequest pageRequest = PageRequest.of(10, 100);
    PageImpl<Account> pagedResponse = new PageImpl<Account>(Arrays.asList(account), pageRequest, totalElements);
    when(accountRepository.findAll(pageRequest)).thenReturn(pagedResponse);
    when(accountConverter.convert(Arrays.asList(account)))
        .thenReturn(Arrays.asList(createDummyAccountResponse()));
    Page<AccountResponse> accounts = accountService.getAccounts(pageRequest);
    assertEquals(accounts.getContent().size(), 1);
    assertEquals(accounts.getPageable().getPageNumber(), 10);
    assertEquals(accounts.getTotalElements() - 1, totalElements);
  }
  
  
  private Account createDummyAccount() {
    Account account = new Account();
    account.setId(100000000l);
    account.setAccountType(AccountType.CREDIT);
    account.setAddress("Test Address");
    account.setEmail("test@gmail.com");
    account.setName("My Test name");
    return account;
  }

  private AccountResponse createDummyAccountResponse() {
    AccountResponse account = new AccountResponse();
    account.setId(100000000l);
    account.setAccountType(AccountType.CREDIT);
    account.setAddress("Test Address");
    account.setEmail("test@gmail.com");
    account.setName("My Test name");
    return account;
  }

}
