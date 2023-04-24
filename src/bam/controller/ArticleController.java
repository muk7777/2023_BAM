package bam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bam.dto.Article;
import bam.util.Util;

public class ArticleController extends Controller {
	
	private int lastArticleId;
	private List<Article> articles;
	private Scanner sc;
	private String cmd;
	
	
	public ArticleController(List<Article> articles, Scanner sc) {
		this.articles = articles;
		this.sc = sc;
		this.lastArticleId = 0;
	}
	@Override
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;
		switch(methodName) {
		case "write": 
			doWrite();
			break;
		case "list": 
			showList();
			break;
		case "detail": 
			showDetail();
			break;
		case "modify": 
			doModify();
			break;
		case "delete": 
			doDelete();
			break;
		default:
			System.out.println("명령어를 확인해주세요.");
			break;
		}
	}
	
	private void doWrite() {
		
		System.out.println("== 게시물 작성하기 ==");
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		
		int id = ++lastArticleId;
		Article article = new Article(id, new Util().getNowDateStr(), loginedMember.id, title, body);
		articles.add(article);
		
		System.out.printf("%d번 글이 생성되었습니다.\n", lastArticleId);
	}
	
	private void showList() {
		
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
		System.out.println("번호	|	제목	|	작성일	|	작성자");
		for (int i = printArticle.size() - 1; i >= 0; i--) {
			Article article = printArticle.get(i);
			System.out.printf("%d	|	%s	|	%s	|	%d\n", article.id, article.title, article.regDate, article.memberId);
		}
	}
	
	private void doModify() {
		
		if (articles.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다.");
			return;
		}
		
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		
		if (CheckNumber(cmdBits[2]) == false) {
			System.out.println("숫자만 입력해주세요.");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);
		Article foundArticle = getArticleById(id);
	
		
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		if (foundArticle.id != loginedMember.id) {
			System.out.println("해당 게시물에 대한 권한이 없습니다.");
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
	private void doDelete() {
		if (articles.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다.");
			return;
		}
		
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		int id = Integer.parseInt(cmdBits[2]);
		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		if (foundArticle.id != loginedMember.id) {
			System.out.println("해당 게시물에 대한 권한이 없습니다.");
			return;
		}
		
		articles.remove(foundArticle);
		System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
	}
	
	private void showDetail() {
		if (articles.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다.");
			return;
		}
		
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		int id = Integer.parseInt(cmdBits[2]);
		
		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		System.out.println("== 게시물 상세보기 ==");
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
		System.out.printf("작성자 : %d\n", foundArticle.memberId);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		
	}
	
	public void makeTestData() {
		System.out.println("게시글 테스트 데이터5개를 생성합니다.");
		for (int i = 1; i <= 5; i++) {
			int id = ++lastArticleId;
			String title = "제목" + i ;
			String body = "내용" + i ;
			articles.add(new Article(id, new Util().getNowDateStr(), 2, title, body));
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
