package com.cassandrabankapp.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.cassandrabankapp.domain.Member;

public interface MemberRepository extends CassandraRepository<Member> {
	
	@Query("select * from member where username = ?0")
	public Member findByUsername(String username);

	@Query("select * from member where accountNumber = ?0")
	public List<Member> findByAccount(String accountNumber);

}
