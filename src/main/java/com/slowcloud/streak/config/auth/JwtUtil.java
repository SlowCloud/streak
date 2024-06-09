package com.slowcloud.streak.config.auth;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

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
import com.slowcloud.streak.member.model.dto.Member;
import com.slowcloud.streak.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;

@Component
public class JwtUtil {

	private static final String USERNAME = "username";

	private static final long EXPIRE_TIME = 1000 * 60 * 60; // 1시간

	private JWSSigner signer;
	private JWSVerifier verifier;

	private final MemberService memberService;

	public JwtUtil(@Value("${jwt.key}") String salt, MemberService memberService) throws JOSEException {
		super();
		this.memberService = memberService;
		signer = new MACSigner(salt);
		verifier = new MACVerifier(salt);
	}

	/**
	 * JWT로부터 Authentication을 생성해서 반환하는 메소드.
	 * 
	 * @param token
	 * @return authentication
	 * @throws ParseException
	 * @throws TokenVerifyException
	 * @throws JOSEException
	 */
	public Authentication getAuthentication(String token) throws ParseException {
		SignedJWT jwt = SignedJWT.parse(token);

		JWTClaimsSet jwtClaimsSet = jwt.getJWTClaimsSet();

		String userId = jwtClaimsSet.getStringClaim(USERNAME);

		Member member = memberService.getMember(userId);
		
//		SimpleGrantedAuthority

		return new UsernamePasswordAuthenticationToken(member, null);
	}

	/**
	 * Member로부터 토큰을 만들어 반환하는 메소드.
	 * 
	 * @param member
	 * @return token
	 * @throws JOSEException
	 */
	public String buildToken(Member member) throws JOSEException {
		JWTClaimsSet claims = new JWTClaimsSet.Builder().claim(USERNAME, member.getId())
				.expirationTime(getExpirationTime())
				.build();

		JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT)
				.build();

		SignedJWT jwt = new SignedJWT(header, claims);

		jwt.sign(signer);
		return jwt.serialize();
	}

	/**
	 * 토큰의 유효성을 검사하는 메소드. 잘못된 토큰이 오지는 않았는지, 기간이 지나지는 않았는지 확인 후, 올바르다면 참, 올바르지 않으면
	 * 거짓을 반환한다.
	 * 
	 * @param token
	 * @return valid
	 * @throws ParseException
	 * @throws JOSEException
	 */
	public boolean validateToken(String token) throws ParseException, JOSEException {
		SignedJWT jwt = SignedJWT.parse(token);

		if (!jwt.verify(verifier)) {
			return false;
		}
		if (
			jwt.getJWTClaimsSet()
					.getExpirationTime()
					.before(new Date())
		) {
			return false;
		}

		return true;
	}

	private Date getExpirationTime() {
		Date now = new Date();
		return new Date(now.getTime() + EXPIRE_TIME);
	}

}
