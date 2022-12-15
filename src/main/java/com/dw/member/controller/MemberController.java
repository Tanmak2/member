package com.dw.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dw.member.model.Member;
import com.dw.member.repository.MemberRepo;

@RestController
public class MemberController {
	@Autowired
	MemberRepo repo;
	
	@GetMapping("/api/v1/members")
	public List<Member> callAllMembers(){
		return repo.findAll();
	}
	
	@PostMapping("/api/v1/members")
	public Member callSaveMember(@RequestBody Member member) {
		member = repo.save(member);
		return member;
	}
	
	@DeleteMapping("/api/v1/members/{id}")
	public boolean callDeleteMember(@PathVariable long id) {
		try {
			repo.deleteById(id); //리턴타입이 void
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	@GetMapping("/api/v1/members/{id}")
	public Member callMemberFindById(@PathVariable long id) {
		return repo.findById(id).get();
	}
	
	@PatchMapping("/api/v1/members")
	public Member updateMember(@RequestBody Member member) {
		member = repo.save(member);
		return member;
	}
	
}
