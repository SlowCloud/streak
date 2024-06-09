package com.slowcloud.streak.member.model.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.slowcloud.streak.config.auth.Role;
import com.slowcloud.streak.member.model.dao.MemberRepository;
import com.slowcloud.streak.member.model.dto.Member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	private Member getMember(String id) {
		return memberRepository.findById(id);
	}

	@Transactional
	public void save(Member member) {
		member.setRole(Role.ROLE_USER);
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberRepository.save(member);
	}

	@Transactional
	public void modify(Member member) {
		memberRepository.changeName(member.getId(), member.getName());
		memberRepository.changePassword(member.getId(), member.getPassword());
	}

	@Transactional
	public void delete(String id) {
		memberRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getMember(username);
	}

	public Member signin(Member member) {
		Member savedMember = getMember(member.getId());
		if (savedMember == null) {
			throw new RuntimeException();
		}
		if (!passwordEncoder.matches(member.getPassword(), savedMember.getPassword())) {
			throw new RuntimeException();
		}
		return savedMember;
	}

}
