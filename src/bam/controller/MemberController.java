package bam.controller;

import java.util.List;
import java.util.Scanner;

import bam.dto.Member;
import bam.util.Util;

public class MemberController {
	
	private List<Member> members;
	private Scanner sc;
	private int lastMemberId;
	
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
		this.lastMemberId = 0;
	}
	
	public void doJoin() {
		System.out.println("== 회원가입 ==");
		
		String loginId = null;
		while (true) {
			
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			
			if (isloginIdDupCheck(loginId)) {
				System.out.printf("%s는 이미 사용중인 아이디 입니다.\n", loginId);
				continue;
			}
			break;
		}
		
		String loginPw = null;
		
		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비밀번호 확인 : ");
			String loginPwCheck = sc.nextLine();
			
			if (loginPw.equals(loginPwCheck) == false) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			break;
		}
		
		System.out.printf("이름 : ");
		String name = sc.nextLine();
		
		int id = ++this.lastMemberId;
		members.add(new Member(id, loginId, loginPw, name, new Util().getNowDateStr()));
		
		System.out.printf("%s회원님이 가입되었습니다.\n", name);
	}
	
	private boolean isloginIdDupCheck(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return true; 
			}
		}
		return false;
	}
}
