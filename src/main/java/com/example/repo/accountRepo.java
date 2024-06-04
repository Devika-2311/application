package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.account;


@Repository
public interface accountRepo extends JpaRepository<account,String > {
	
	@Modifying
	@Query(value="update account t set t.balance=t.balance+:amount where t.username=:username",nativeQuery=true)
	public void deposit(@Param("username") String username,@Param("amount") float amount);
	
	
	@Modifying
	@Query(value="update  account set balance=balance-:amount where username=:username",nativeQuery=true)
	void withdrawal(@Param("username") String username,@Param("amount") float amount);
	
	@Modifying
	@Query(value="update  account set password=+:password where username=:username",nativeQuery=true)
	public void changePassword(@Param("username") String username,@Param("password") String password);
	
	@Query(value="select count(*) from account where username=:username and password=:password", nativeQuery=true)
	int login(@Param("username") String username, @Param("password") String password);

	
	
	@Modifying
	@Query(value="update  account set email=+:email where username=:username",nativeQuery=true)
	public void changeEmail(@Param("username") String username,@Param("email") String email);
	
	@Modifying
	@Query(value="update  account set phone=+:phone where username=:username",nativeQuery=true)
	public void changePhone(@Param("username") String username,@Param("phone") String phone);
	@Modifying
	@Query(value="update  account set address=+:address where username=:username",nativeQuery=true)
	public void changeAddress(@Param("username") String username,@Param("address") String address);

}
