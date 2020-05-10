package com.mockito.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mockito.demo.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
