package com.slowcloud.streak.util;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;

class JwtsExampleTest {

	void JwsBuildTest() {

		SecretKey key = Jwts.SIG.HS256.key()
				.build();

		String jwt = Jwts.builder()
				.subject("alice")
				.issuer("localhost")
				.claim("email", "test@example.com")
				.signWith(key)
				.compact();

		System.out.println(jwt);

	}

	@Test
	void unpackingJwsTest() {

		SecretKey key = Jwts.SIG.HS256.key()
				.build();

		String jws = Jwts.builder()
				.subject("alice")
				.issuer("localhost")
				.claim("email", "test@example.com")
				.signWith(key)
				.compact();

		JwtParser parser = Jwts.parser()
				.verifyWith(key)
				.build();

		System.out.println(
				parser.parseSignedClaims(jws)
						.getPayload()
						.getSubject()
		);

	}

}
