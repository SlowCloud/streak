package com.slowcloud.streak.member.model.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.slowcloud.streak.config.auth.Role;
import com.slowcloud.streak.member.model.dao.MemberRepository;
import com.slowcloud.streak.member.model.dto.Member;

import jakarta.transaction.Transactional;

@Component
public class MemberService implements UserDetailsService {

	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
	}

	public Member getMember(String id) {
		return memberRepository.findById(id);
	}

	@Transactional
	public void save(Member member) {
		member.setRole(Role.ROLE_USER);
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

}
