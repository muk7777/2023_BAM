package bam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bam.dto.Article;
import bam.util.Util;

public class ArticleController {
	
	private int lastArticleId;
	private List<Article> articles;
	private Scanner sc;
	
	public ArticleController(List<Article> articles, Scanner sc) {
		this.articles = articles;
		this.sc = sc;
		this.lastArticleId = 0;
	}
	
	public void doWrite() {
		
			System.out.println("== 게시물 작성하기 ==");
			System.out.printf("제목 : ");
			String title = sc.nextLine();
			System.out.printf("내용 : ");
			String body = sc.nextLine();
			
			
			int id = ++lastArticleId;
			Article article = new Article(id, new Util().getNowDateStr(), title, body);
			articles.add(article);
			
			System.out.printf("%d번 글이 생성되었습니다.\n", lastArticleId);
	}
	
	public void showList(String cmd) {
		
		if (articles.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다.");
			return;
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
			return;
		}
		
		
		System.out.println("== 게시물 목록보기 ==");
		System.out.println("번호	|	제목	|	작성날짜");
		for (int i = printArticle.size() - 1; i >= 0; i--) {
			Article article = printArticle.get(i);
			System.out.printf("%d	|	%s	|	%s\n", article.id, article.title, article.regDate);
		}
	}
	
	public void doModify(String cmd) {
		if (articles.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다.");
			return;
		}
		
		String[] cmdBite = cmd.split(" ");
		
		if (cmdBite[2].equals("")) {
			System.out.println("게시물 번호가 입력되지 않았습니다.");
			return;
		}
		
		if (CheckNumber(cmdBite[2]) == false) {
			System.out.println("숫자만 입력해주세요.");
			return;
		}
		
		int id = Integer.parseInt(cmdBite[2]);
		Article foundArticle = getArticleById(id);
	
		
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
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
		
	}
	public void doDelete(String cmd) {
		if (articles.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다.");
			return;
		}
		
		int id = Integer.parseInt(cmd.substring(14).trim());
		
		
		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		articles.remove(foundArticle);
		System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
	}
	
	public void showDetail(String cmd) {
		if (articles.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다.");
			return;
		}
		
		int id = Integer.parseInt(cmd.substring(14).trim());
		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		System.out.println("== 게시물 상세보기 ==");
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		
	}
	
	public void makeTestData() {
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
