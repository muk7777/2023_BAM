package bam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bam.dto.Article;
import bam.util.Util;

public class App {
	private List<Article> articles; 
	private int lastArticleId;
	
	public App() {
		articles = new ArrayList<>();
		lastArticleId = 0;
	}
	
	public void run() {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		makeTestData();
		
		
		while (true) {
			System.out.println("명령어) ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.equals("exit")) {
				break;
			}
				
			if (cmd.equals("article write")) {
				System.out.println("== 게시물 작성하기 ==");
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				
				int id = ++lastArticleId;
				Article article = new Article(id, new Util().getNowDateStr(), title, body);
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다.\n", lastArticleId);
				
			} else if (cmd.startsWith("article list")) {
				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다.");
					continue;
				}
				
				String title = cmd.substring(12).trim();
				List<Article> printArticle = articles; 
				
				if (title.length() > 0) {
					System.out.printf("검색어 : %s\n", title);
					printArticle = new ArrayList<>();
					for (Article article : articles) {
						if (article.title.contains(title)) {
							printArticle.add(article);
						}
					}
				}
				
				if (printArticle.size() == 0) {
					System.out.println("일치하는 게시물이 없습니다.");
					continue;
				}
				
				
				System.out.println("== 게시물 목록보기 ==");
				System.out.println("번호	|	제목	|	작성날짜");
				for (int i = printArticle.size() - 1; i >= 0; i--) {
					Article article = printArticle.get(i);
					System.out.printf("%d	|	%s	|	%s\n", article.id, article.title, article.regDate);
				}
				
			} else if (cmd.startsWith("article modify ")) {
				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다.");
					continue;
				}
				
				String[] cmdBite = cmd.split(" ");
				
				if (cmdBite[2].equals("")) {
					System.out.println("게시물 번호가 입력되지 않았습니다.");
					continue;
				}
				
				if (CheckNumber(cmdBite[2]) == false) {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}
				
				int id = Integer.parseInt(cmdBite[2]);
				Article foundArticle = getArticleById(id);
			
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				System.out.println("== 게시물 수정하기 ==");
				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine().trim();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine().trim();
				
				
				if (title.equals("") == false) {
					foundArticle.title = title;
				}
				if (body.equals("") == false) {
					foundArticle.body = body;
				}
				
				System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
				
			} else if (cmd.startsWith("article delete ")) {
				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다.");
					continue;
				}
				
				int id = Integer.parseInt(cmd.substring(14).trim());
				
				
				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				articles.remove(foundArticle);
				System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
				
			} else if (cmd.startsWith("article detail ")) {
				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다.");
					continue;
				}
				
				int id = Integer.parseInt(cmd.substring(14).trim());
				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				System.out.println("== 게시물 상세보기 ==");
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				
			} else {
				System.out.printf("%s는 존재하지 않는 명령어 입니다.\n", cmd);
			}
		}
		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}
	private void makeTestData() {
		System.out.println("게시글 테스트 데이터5개를 생성합니다.");
		for (int i = 1; i <= 5; i++) {
			int id = ++lastArticleId;
			String title = "제목" + i ;
			String body = "내용" + i ;
			articles.add(new Article(id, new Util().getNowDateStr(), title, body));
		}
	}
	
	private Article getArticleById(int id) {
		
		int index = this.getIndexById(id);
		if (index == -1) {
			return null;
		}
		return articles.get(index);
	}
	
	private int getIndexById(int id) {
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.id == id) {
				return i;
			}
		}
		return -1;
	}
	
	private boolean CheckNumber(String str) {
		for(int i = 0; i<str.length(); i++){
			char check = str.charAt(i);
			if( check <= 48 || check >= 58) {
				return false;
			}
		}		
		return true;
	}
}