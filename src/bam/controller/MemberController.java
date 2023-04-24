package bam.controller;

import java.util.List;
import java.util.Scanner;

import bam.dto.Member;
import bam.util.Util;

public class MemberController extends Controller {
	
	private List<Member> members;
	private Scanner sc;
	private int lastMemberId;
	private String cmd;
	
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
		this.lastMemberId = 0;
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;
		
		switch (methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("명령어를 확인해주세요.");
			break;
		}
	}
	
	private void doJoin() {
		
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
	
	private void doLogin() {
		
		System.out.println("== 로그인 ==");
		
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();
		
		Member foundMember = getMemberByLoginId(loginId);
		if (foundMember == null) {
			System.out.println("일치하는 회원이 없습니다.");
			return;
		}
		if (foundMember.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해 주세요.");
			return;
		}
		
		loginedMember = foundMember;
		System.out.printf("%s님 환영합니다.\n",foundMember.name);
	}
	
	private void doLogout() {
		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}
	
	private boolean isloginIdDupCheck(String loginId) {
		
		if (getMemberByLoginId(loginId) != null) {
			return true;
		}
		return false;
	}
	
	private Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}
	public void makeTestData() {
		System.out.println("회원 테스트 데이터3개를 생성합니다.");
		for (int i = 1; i <= 3; i++) {
			int id = ++lastMemberId;
			String loginId = "test" + i ;
			String loginPw = "test" + i ;
			String name = "사용자" + i ;
			
			members.add(new Member(id, loginId, loginPw, name, new Util().getNowDateStr() ));
		}
	}
}
