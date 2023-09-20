package io.github.sanggu39.main.sql;

import io.github.sanggu39.main.classes.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    Connection conn;
    String dburl = "jdbc:mysql://127.0.0.1:3306/book_db";
    String dbUser = "root";
    String dbpasswd = "1111";

    public UserDAO() {
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
    public void registUser(User user) {
        String sql = "insert into user values(?,?,?);";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getUserNum());
            pstmt.setString(2, user.getId());
            pstmt.setString(3, user.getPswd());
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

    public void updateUser(User user) {
        String sql = "update user set id=?, pswd=? where userNum = ?;";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPswd());
            pstmt.setInt(3, user.getUserNum());
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

    public void deleteBook(int userNum) {
        String sql = "delete from user where userNum = ?;";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userNum);
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

    public User selectOne(int userNum) {
        String sql = "select * from user where userNum = ?;";
        PreparedStatement pstmt = null;
        User re = new User();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userNum);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                re.setUserNum(rs.getInt("userNum"));
                re.setId(rs.getString("id"));
                re.setPswd(rs.getString("pswd"));
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

    public List<User> slectAll() {
        String sql = "select * from user;";
        PreparedStatement pstmt = null;

        List<User> list = new ArrayList<User>();

        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet re = pstmt.executeQuery();

            while (re.next()) {
                User s = new User();
                s.setUserNum(re.getInt("userNum"));
                s.setId(re.getString("id"));
                s.setPswd(re.getString("pswd"));
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
