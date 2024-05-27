package com.slowcloud.streak.member.model.service;

import org.springframework.stereotype.Service;

import com.slowcloud.streak.member.model.dao.MemberRepository;
import com.slowcloud.streak.member.model.dto.Member;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MemberService {

	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
	}

	public Member getMember(String id) {
		return memberRepository.getReferenceById(id);
	}

	public void save(Member member) {
		memberRepository.save(member);
	}

	public void modify(Member member) {
		memberRepository.changeName(member.getId(), member.getName());
		memberRepository.changePassword(member.getId(), member.getPassword());
	}

	public void delete(String id) {
		memberRepository.deleteById(id);
	}

}
