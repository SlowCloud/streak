package com.slowcloud.streak.config.auth;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

class SignedJWTTest {

	private static final String SALT = "alsdkfjlskadjflkdsafjioqwenqroiocuvzxicovuzxcmfnasdlkfnlqwerjio";
	private static JWSSigner signer;
	private static JWSVerifier verifier;

	@BeforeAll
	public static void setup() throws JOSEException {
		signer = new MACSigner(SALT);
		verifier = new MACVerifier(SALT);
	}

	@Description("Signed JWT 생성 테스트")
	@Test
	void jwtBuildTest() throws JOSEException {
		JWTClaimsSet claims = new JWTClaimsSet.Builder().claim("username", "test")
				.build();
		SignedJWT jwt = buildSignedJWT(claims);

		String token = jwt.serialize();
		System.out.println(token);
	}

	private SignedJWT buildSignedJWT(JWTClaimsSet claims) throws JOSEException {
		JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT)
				.build();
		SignedJWT jwt = new SignedJWT(header, claims);
		jwt.sign(signer);
		return jwt;
	}

	@Description("생성된 Signed JWT 복호화 테스트")
	@Test
	void verifyTest() throws JOSEException, ParseException {
		JWTClaimsSet claims = new JWTClaimsSet.Builder().claim("username", "test")
				.build();
		SignedJWT jwt = buildSignedJWT(claims);

		String token = jwt.serialize();

		SignedJWT jwt2 = SignedJWT.parse(token);
		JWTClaimsSet verifiedClaims = jwt2.getJWTClaimsSet();
		Map<String, Object> claimsmap = verifiedClaims.getClaims();
		System.out.println(claimsmap);

		assertTrue(jwt2.verify(verifier));
	}

}
