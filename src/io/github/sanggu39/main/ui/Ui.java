package io.github.sanggu39.main.ui;

import io.github.sanggu39.main.utill.AccountManager;
import io.github.sanggu39.main.utill.BookManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    private BookManager bmgr = new BookManager();
    private AccountManager amgr = new AccountManager();

    public Ui() {
        showMain();
    }

    private void printLoginMenu() {
        System.out.println(" [도서 관리 프로그램]");
        System.out.println("1. 로그인");
        System.out.println("2. 회원 가입");
        System.out.print(">");
    }

    private void printMainMenu() {
        System.out.println(" [도서 관리 시스템]");
        System.out.println("1. 도서 등록");
        System.out.println("2. 도서 목록");
        System.out.println("3. 도서 대여");
        System.out.println("4. 도서 반납");
        System.out.println("5. 도서 삭제");
        System.out.println("0. 나가기");
        System.out.print(">");
    }

    public void showLogin() {
        while (true){
            printLoginMenu();
            try{
                int menu = sc.nextInt();
                switch (menu) {
                    case 1 -> bmgr.bookInsert();
                    case 2 -> bmgr.showBookList();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("위에 값중 입력 해주세요.");
                }
            } catch (InputMismatchException err) {
                System.out.println("정수 값을 입력해 주세요.");
                sc.nextLine();
            }
        }
    }
    public void showMain() {
        while (true){
            printMainMenu();
            try{
                int menu = sc.nextInt();
                switch (menu) {
                    case 1 -> bmgr.bookInsert();
                    case 2 -> bmgr.showBookList();
                    case 3 -> bmgr.loanBook();
                    case 4 -> bmgr.returnBook();
                    case 5 -> bmgr.deleteBook();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("위에 값중 입력 해주세요.");
                }
            } catch (InputMismatchException err) {
                System.out.println("정수 값을 입력해 주세요.");
                sc.nextLine();
            }
        }
    }
}
