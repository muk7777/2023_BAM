package bam.dto;

public class Member {
	public int id;
	public String loginId;
	public String loginPw;
	public String name;
	public String regDate;

	public Member(int id, String loginId, String loginPw, String name, String regDate) {
		this.id = id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.regDate = regDate;
	}
}
