package io.github.sanggu39.main.sql;

import io.github.sanggu39.main.classes.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    Connection conn;
    String dburl = "jdbc:mysql://127.0.0.1:3306/book_db";
    String dbUser = "root";
    String dbpasswd = "1111";

    public BookDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertBook(Book book) {
        String sql = "insert into book values(?,?,?,?,?);";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, book.getBookNum());
            pstmt.setString(2, book.getBookName());
            pstmt.setString(3, book.getAuthor());
            pstmt.setBoolean(4, book.isLoaned());
            pstmt.setInt(5, book.whoLoaned());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void updateBook(Book book) {
        String sql = "update book set bookName=?, author=?, isLoaned=?, whoLoaned=? where bookNum = ?;";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getBookName());
            pstmt.setString(2, book.getAuthor());
            pstmt.setBoolean(3, book.isLoaned());
            pstmt.setInt(4, book.whoLoaned());
            pstmt.setInt(5,book.getBookNum());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void deleteBook(int bookNum) {
        String sql = "delete from book where bookNum = ?;";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookNum);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public Book selectOne(int bookNum) {
        String sql = "select * from book where bookNum = ?;";
        PreparedStatement pstmt = null;
        Book re = new Book();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookNum);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                re.setBookNum(rs.getInt("bookNum"));
                re.setBookName(rs.getString("bookName"));
                re.setAuthor(rs.getString("author"));
                re.setLoaned(rs.getBoolean("isLoaned"));
                re.setWhoLoaned(rs.getInt("whoLoaned"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return re;
    }

    public List<Book> slectAll() {
        String sql = "select * from book;";
        PreparedStatement pstmt = null;

        List<Book> list = new ArrayList<Book>();

        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet re = pstmt.executeQuery();

            while (re.next()) {
                Book s = new Book();
                s.setBookNum(re.getInt("bookNum"));
                s.setBookName(re.getString("bookName"));
                s.setAuthor(re.getString("author"));
                s.setLoaned(re.getBoolean("isLoaned"));
                s.setWhoLoaned(re.getInt("whoLoaned"));
                list.add(s);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }
}
