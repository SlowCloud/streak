package com.slowcloud.streak.member.controller;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;
import com.slowcloud.streak.config.auth.JwtUtil;
import com.slowcloud.streak.member.model.dto.Member;
import com.slowcloud.streak.member.model.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {

	private final MemberService memberService;
	private final JwtUtil jwtUtil;

	@GetMapping("/signin")
	public void getMember(String id, HttpServletResponse response) throws JOSEException {
		Member member = memberService.getMember(id);
		String token = jwtUtil.buildToken(member);

		Cookie cookie = new Cookie("jwt", token);
		response.addCookie(cookie);
	}

	@PostMapping("/signup")
	public ResponseEntity<Void> createMember(@RequestBody Member member) {
		memberService.save(member);
		return ResponseEntity.status(HttpStatus.CREATED)
				.build();
	}

	@PutMapping
	public void modifyMember(Member member) {
		memberService.modify(member);
	}

	@DeleteMapping
	public void deleteMember(String id) {
		memberService.delete(id);
	}

	@GetMapping("/test")
	public String logined(@AuthenticationPrincipal Member member) {
		return "hello, " + member.getName() + "!";
	}

}
