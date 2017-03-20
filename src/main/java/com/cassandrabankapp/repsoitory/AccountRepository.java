package com.cassandrabankapp.repsoitory;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.cassandrabankapp.domain.Account;

public interface AccountRepository extends CassandraRepository<Account> {

	@Query("select * from account where accountNumber=?0")
	public Account findByAccountNumber(String accountNumber);

}
