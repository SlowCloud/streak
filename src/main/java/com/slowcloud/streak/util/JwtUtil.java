package com.slowcloud.streak.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.slowcloud.streak.member.model.dto.Member;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

	@Value("${jwt.key}")
	private SecretKey key;
	private JwtParser parser;

	public JwtUtil() {
		super();
		parser = Jwts.parser().verifyWith(key).build();
	}

	public boolean isExpired(String jwt) {
		return parser.parseSignedClaims(jwt).getPayload().getExpiration().after(new Date());
	}

	public String buildJwt(Member member) {
		return Jwts.builder().subject("jwt").claim("userId", member.getId()).signWith(key).compact();
	}

}
