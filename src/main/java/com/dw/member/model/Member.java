package com.dw.member.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dw_member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	@Column(length = 30)
	private String name;
	@Column
	private int age;

	@Column(length = 40)
	private String userId;

	@Column
	private String userPassword;
	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Dept dept;
}
