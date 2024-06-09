package com.slowcloud.streak.member.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.slowcloud.streak.member.model.dto.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	@Modifying(clearAutomatically = true)
	@Query("update Member m set m.password = ?2 where m.id = ?1")
	void changePassword(String id, String password);

	@Modifying(clearAutomatically = true)
	@Query("update Member m set m.name = :name where m.id = :id")
	void changeName(@Param("id") String id, @Param("name") String name);
	
	Member findById(String id);
	void deleteById(String id);

}
