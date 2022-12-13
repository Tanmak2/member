package com.dw.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.member.model.Member;

public interface MemberRepo extends JpaRepository<Member, Long>{
	
}
