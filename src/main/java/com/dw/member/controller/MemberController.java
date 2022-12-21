package com.dw.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.member.model.Member;
import com.dw.member.repository.MemberRepo;
import com.dw.member.service.MainService;
import com.dw.member.utils.APIResponse;

@RestController
public class MemberController {
	@Autowired
	MemberRepo repo;

	@Autowired
	MainService service;

	@PostMapping("/api/v1/login-test")
	public boolean callLogin(@RequestBody Member member, HttpServletRequest request) {
		Member m = repo.findByuserIdAndUserPassword(member.getUserId(), member.getUserPassword());
		if (m != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", m.getName());
			return true;
		} else {
			return false;
		}
	}

	@PostMapping(value = "/api/v1/login")
	public boolean callLogin2(@ModelAttribute Member member, HttpServletRequest request) {
		Member m = repo.findByuserIdAndUserPassword(member.getUserId(), member.getUserPassword());
		if (m != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", m.getName());
			return true;
		} else {
			return false;
		}
	}

	// 전체 조회
	@GetMapping("/member")
	public APIResponse<List<Member>> callAllMembers() {
		// findAll == select * from <테이블 이름>
		List<Member> list = repo.findAll();
		int size = (int) repo.count();
		return new APIResponse<>(size, list);
	}

	// 전체 조회 (정렬 기능 추가)
	@GetMapping("/member/sort/{column}")
	public APIResponse<List<Member>> callAllMembers(@PathVariable String column) {
		// findAll == select * from <테이블 이름>
		List<Member> list = repo.findAll(Sort.by(Sort.Direction.ASC, column));
		int size = (int) repo.count();
		return new APIResponse<>(size, list);
	}

	// 전체 조회 (페이징 처리, 정렬 추가)
	// 사용법: /member/pagination?offset=0&pageSize=5&column=age
	@GetMapping("/member/pagination")
	public APIResponse<Page<Member>> callAllMembers(@RequestParam int offset, @RequestParam int pageSize,
			@RequestParam String column) {

		Page<Member> members = repo
				.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(column)));
		int size = members.getSize();

		return new APIResponse<>(size, members);
	}

	@PostMapping("/api/v1/members")
	public Member callSaveMember(@RequestBody Member member) {
		member = repo.save(member);
		return member;
	}

	@DeleteMapping("/api/v1/members/{id}")
	public boolean callDeleteMember(@PathVariable long id) {
		try {
			repo.deleteById(id); // 리턴타입이 void
			return true;
		} catch (Exception e) {
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

	@PostMapping("/api/v1/valid-recaptcha")
	public boolean validRecaptcha(HttpServletRequest request) {
		String response = request.getParameter("g-recaptcha-response");
		boolean isRecaptcha = service.verifyRecaptcha(response);
		return isRecaptcha;
	}

}
