package bam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bam.controller.ArticleController;
import bam.controller.Controller;
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
		memberController.makeTestData();
		
		while (true) {
			System.out.println("명령어) ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.equals("exit")) {
				break;
			}
			
			String[] cmdBits = cmd.split(" ");
			
			if (cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요.");
				continue;
			}
			
			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];
			
			Controller controller = null;
				
			if (controllerName.equals("member")) {
				controller = memberController;
			} else if (controllerName.equals("article")) {
				controller = articleController;
			} else {
				System.out.printf("%s는 존재하지 않는 명령어 입니다.\n", cmd);
				continue;
			}
			controller.doAction(cmd, methodName);
		}
		
		
		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}
}