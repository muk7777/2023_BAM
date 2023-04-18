package bam.dto;

public class Member {
	public int id;
	public String memberId;
	public String memberPw;
	public String name;
	public String regDate;
	
	public Member(int id, String memberId, String memberPw, String name, String regDate) {
		this.id = id;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.name = name;
		this.regDate = regDate;
	}
}
