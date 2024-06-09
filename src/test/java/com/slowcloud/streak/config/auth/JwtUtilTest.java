package com.slowcloud.streak.config.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;

import com.nimbusds.jose.JOSEException;
import com.slowcloud.streak.member.model.dao.MemberRepository;
import com.slowcloud.streak.member.model.dto.Member;
import com.slowcloud.streak.member.model.service.MemberService;

@DataJpaTest
@Import({ JwtUtil.class, MemberService.class })
class JwtUtilTest {

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	MemberRepository memberRepository;

	@BeforeEach
	void setup() {
		memberRepository.deleteAll();
		memberRepository.save(buildMemberFixture());
	}

	@Test
	void tokenBuildTest() throws JOSEException {
		Member member = buildMemberFixture();
		String token = jwtUtil.buildToken(member);

		assertNotNull(token);
		assertNotEquals(token.length(), 0);

		System.out.println(token);

	}

	@Test
	void tokenValidateTest() throws JOSEException, ParseException {
		Member member = buildMemberFixture();
		String token = jwtUtil.buildToken(member);

		assertTrue(jwtUtil.validateToken(token));
	}

	@Test
	void tokenDecodingTest() throws JOSEException, ParseException {
		Member member = buildMemberFixture();
		String token = jwtUtil.buildToken(member);

		Authentication authentication = jwtUtil.getAuthentication(token);
		assertEquals(authentication.getPrincipal(), member.getId());
		assertEquals(authentication.getCredentials(), member.getPassword());
	}

	private Member buildMemberFixture() {
		return Member.builder()
				.id("test")
				.name("user")
				.password("password")
				.build();
	}

}
