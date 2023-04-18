package bam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bam.controller.ArticleController;
import bam.controller.MemberController;
import bam.dto.Article;
import bam.dto.Member;

public class App {
	private List<Article> articles; 
	private List<Member> members; 
	
	public App() {
		this.articles = new ArrayList<>();
		this.members = new ArrayList<>();
	}
	
	public void run() {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);
		articleController.makeTestData();
		
		while (true) {
			System.out.println("명령어) ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.equals("exit")) {
				break;
			}
				
			if (cmd.equals("member join")) {
				memberController.doJoin();
				
			} else if (cmd.equals("article write")) {
				articleController.doWrite();
				
			} else if (cmd.startsWith("article list")) {
				articleController.showList(cmd);
				
			} else if (cmd.startsWith("article detail ")) {
				articleController.showDetail(cmd);
				
			} else if (cmd.startsWith("article modify ")) {
				articleController.doModify(cmd);
				
			} else if (cmd.startsWith("article delete ")) {
				articleController.doDelete(cmd);
				
			} else {
				System.out.printf("%s는 존재하지 않는 명령어 입니다.\n", cmd);
			}
		}
		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}
}