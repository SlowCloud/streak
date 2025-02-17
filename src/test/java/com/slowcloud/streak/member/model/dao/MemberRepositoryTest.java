package com.slowcloud.streak.member.model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.slowcloud.streak.member.model.dto.Member;

@DataJpaTest
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@DisplayName("MemberRepository가 정상적으로 불러와지는지 확인")
	void autowiredTest() {
		assertNotNull(memberRepository);
	}

	@BeforeEach
	void setup() {
		memberRepository.deleteAll();
	}

	@Test
	@DisplayName("insert가 잘 되는지 확인")
	void insertTest() {
		Member member = buildMemberFixture();
		memberRepository.save(member);

		assertEquals(memberRepository.count(), 1);

		Member savedMember = memberRepository.findAll()
				.get(0);
		assertEquals(savedMember.getName(), member.getName());
	}

	private Member buildMemberFixture() {
		return Member.builder()
				.id("test")
				.name("user")
				.password("password")
				.build();
	}

	@Test
	@DisplayName("이름이 잘 변경되는지 확인")
	void modifyNameTest() {
		Member member = buildMemberFixture();
		memberRepository.save(member);

		String changedName = "changedName";
		memberRepository.changeName(member.getId(), changedName);

		Member savedMember = memberRepository.findAll()
				.get(0);
		assertNotEquals(member.getName(), savedMember.getName());

		System.out.println("성공!!");
		System.out.println(savedMember);
	}

}
