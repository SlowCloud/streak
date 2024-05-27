package com.slowcloud.streak.member.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slowcloud.streak.member.model.dto.Member;
import com.slowcloud.streak.member.model.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

	private MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	@GetMapping
	public Member getMember(String id) {
		return memberService.getMember(id);
	}

	@PostMapping
	public void createMember(Member member) {
		memberService.save(member);
	}

	@PutMapping
	public void modifyMember(Member member) {
		memberService.modify(member);
	}

	@DeleteMapping
	public void deleteMember(String id) {
		memberService.delete(id);
	}

}
