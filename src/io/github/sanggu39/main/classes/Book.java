package io.github.sanggu39.main.classes;

public class Book {
    int bookNum;
    String bookName;
    String author;
    boolean isLoaned;
    public int whoLoaned;

    public Book(int bookNum, String bookName, String author, Boolean isLoaned, int whoLoaned) {
        this.bookNum =bookNum;
        this.bookName = bookName;
        this.author = author;
        this.isLoaned = isLoaned;
        this.whoLoaned = whoLoaned;
    }

    public Book(){}

    public int getBookNum() {
        return bookNum;
    }
    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public boolean isLoaned() {
        return isLoaned;
    }
    public void setLoaned(boolean isLoaned) {
        this.isLoaned = isLoaned;
    }
    public int whoLoaned() {
        return whoLoaned;
    }
    public void setWhoLoaned(int whoLoaned) {
        this.whoLoaned = whoLoaned;
    }

    @Override
    public String toString() {
        return "Book{" + "bookNum=" + bookNum + ", bookName='" + bookName + ", author='" + author + ", isLoaned=" + isLoaned + '}';
    }
}
