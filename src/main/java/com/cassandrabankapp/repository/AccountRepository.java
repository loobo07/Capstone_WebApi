package com.cassandrabankapp.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.cassandrabankapp.domain.Account;

public interface AccountRepository extends CassandraRepository<Account> {
	
	@Query("select * from account where accountNumber=?0")
	public Account findByAccountNumber(String accountNumber);

	@Query("SELECT count(*) FROM account WHERE accountNumber=?0")
	public int existingAccount(String accountNumber);
	
	@Query("UPDATE account SET balance = ?0 WHERE accountNumber=?1")
	public void updateBalance(double balance, String acountNumber);
}
