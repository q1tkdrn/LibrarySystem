package io.github.sanggu39.main.utill;

import io.github.sanggu39.main.classes.Book;
import io.github.sanggu39.main.sql.BookDAO;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BookManager {

    BookDAO bookDAO = new BookDAO();

    public void bookInsert() {
        Scanner sc = new Scanner(System.in);
        int bookNum;
        while (true) {
            boolean isUnique = true;
            System.out.println("등록번호를 입력하세요");
            System.out.print(">");
            bookNum = sc.nextInt();
            List<Book> bookList = bookDAO.slectAll();
            for (int i=0; i + 1 <= bookList.size();i++) {
                Book book = bookList.get(i);
                int bN = book.getBookNum();
                if(bN == bookNum) {
                    System.out.println("이미 있는 번호입니다.");
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                break;
            }
        }
        System.out.println("도서명을 입력하세요");
        System.out.print(">");
        String bookName = sc.next();
        System.out.println("저자를 입력하세요");
        System.out.print(">");
        String author = sc.next();
        Boolean isLoaned = false;

        Book book = new Book(bookNum, bookName, author, isLoaned);

        bookDAO.insertBook(book);
    }

    public void showBookList() {
        StringBuilder sb = new StringBuilder();
        sb.append("     [도서 목록]" + "\n");
        List<Book> bookList = bookDAO.slectAll();
        for(int i = 0; i + 1<= bookList.size(); i++) {
            Book book = bookList.get(i);
            String isLaoned;
            if (book.isLoaned()) {
                isLaoned = "O";
            } else  isLaoned = "X";
            sb.append(book.getBookNum())
                    .append(".")
                    .append(book.getBookName())
                    .append("(")
                    .append(book.getAuthor())
                    .append(")")
                    .append(" 대출여부:")
                    .append(isLaoned)
                    .append("\n");
        }
        System.out.println(sb);
    }

    public void loanBook() {
        Scanner sc = new Scanner(System.in);
        boolean isExist = false;
        Book book = null;
        while (true) {
            System.out.println("대여할 책의 제목 또는 등록된 번호를 입력하세요");
            System.out.print(">");
            if (sc.hasNextInt()) {
                int bookNum = sc.nextInt();
                List<Book> bookList = bookDAO.slectAll();
                for (int i=0; i + 1 <= bookList.size();i++) {
                    book = bookList.get(i);
                    int bN = book.getBookNum();
                    if(bN == bookNum) {
                        isExist = true;
                        break;
                    }
                }
            } else if (sc.hasNext()) {
                List<Book> bookList = bookDAO.slectAll();
                String bookName = sc.next();
                for (int i=0; i + 1 <= bookList.size();i++) {
                    book = bookList.get(i);
                    String bN = book.getBookName();
                    if(Objects.equals(bN, bookName)) {
                        isExist = true;
                        break;
                    }
                }

            } else {
                System.out.println("유효한 입력이 아닙니다.");
            }
            if (isExist) {
                if(!book.isLoaned()) {
                    break;
                } else System.out.println("이미 대여된 책입니다.");
            } else System.out.println("해당 이름과 같은 책이 없습니다.");
        }

        book.setLoaned(true);
        bookDAO.updateBook(book);
        System.out.println("대여 완료");
    }

    public void returnBook() {
        Scanner sc = new Scanner(System.in);
        Book book = null;
        boolean isExist = false;
        while (true) {
            System.out.println("반납할 책의 제목 또는 등록된 번호를 입력하세요");
            System.out.print(">");
            if (sc.hasNextInt()) {
                int bookNum = sc.nextInt();
                List<Book> bookList = bookDAO.slectAll();
                for (int i=0; i + 1 <= bookList.size();i++) {
                    book = bookList.get(i);
                    int bN = book.getBookNum();
                    if(bN == bookNum) {
                        isExist = true;
                        break;
                    }
                }
            } else if (sc.hasNext()) {
                List<Book> bookList = bookDAO.slectAll();
                String bookName = sc.next();
                for (int i=0; i + 1 <= bookList.size();i++) {
                    book = bookList.get(i);
                    String bN = book.getBookName();
                    if(Objects.equals(bN, bookName)) {
                        isExist = true;
                        break;
                    }
                }
            } else {
                System.out.println("유효한 입력이 아닙니다.");
            }
            if (isExist) {
                if(book.isLoaned()) {
                    break;
                } else System.out.println("대여 되지 않은 책입니다.");
            } else System.out.println("해당 이름과 같은 책이 없습니다.");
        }
        book.setLoaned(false);
        bookDAO.updateBook(book);
        System.out.println("반납 완료");
    }

    public void deleteBook() {
        Scanner sc = new Scanner(System.in);
        boolean isExist = false;
        Book book = null;
        while (true) {
            System.out.println("삭제할 책의 제목 또는 등록된 번호를 입력하세요");
            System.out.print(">");
            if (sc.hasNextInt()) {
                int bookNum = sc.nextInt();
                List<Book> bookList = bookDAO.slectAll();
                for (int i=0; i + 1 <= bookList.size();i++) {
                    book = bookList.get(i);
                    int bN = book.getBookNum();
                    if(bN == bookNum) {
                        isExist = true;
                        break;
                    }
                }
            } else if (sc.hasNext()) {
                List<Book> bookList = bookDAO.slectAll();
                String bookName = sc.next();
                for (int i=0; i + 1 <= bookList.size();i++) {
                    book = bookList.get(i);
                    String bN = book.getBookName();
                    if(Objects.equals(bN, bookName)) {
                        isExist = true;
                        break;
                    }
                }

            } else {
                System.out.println("유효한 입력이 아닙니다.");
            }
            if (isExist) {
                break;
            } else System.out.println("해당 이름과 같은 책이 없습니다.");
        }

        book.setLoaned(true);
        bookDAO.deleteBook(book.getBookNum());
        System.out.println("삭제 완료");
    }
}
