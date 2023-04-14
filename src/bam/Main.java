package bam;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import bam.util.Util;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		
		List<Article> articles = new ArrayList<>(); 
		
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
				Article article = new Article(id, new Util().getNowDateStr(), body, title);
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다.\n", lastArticleId);
				
			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다.");
					continue;
				}
				
				System.out.println("== 게시물 목록보기 ==");
				System.out.println("번호	|	제목	|	작성날짜");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	|	%s	|	%s\n", article.id, article.title, article.regDate);
				}
				
			} else if (cmd.startsWith("article modify ")) {
				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다.");
					continue;
				}
				
				int id = Integer.parseInt(cmd.substring(14).trim());
				Article foundArticle = null;
				
				for (Article article : articles) {
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
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
				Article foundArticle = null;
				
				for (Article article : articles) {
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
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
				Article foundArticle = null;
				
				for (Article article : articles) {
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
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
}

class Article {
	int id;
	String regDate;
	String title;
	String body;
	
	Article (int id, String regDate, String body, String title) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
	}
}
