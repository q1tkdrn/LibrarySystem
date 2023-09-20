package io.github.sanggu39.main.utill;

import io.github.sanggu39.main.classes.Book;
import io.github.sanggu39.main.classes.User;
import io.github.sanggu39.main.sql.UserDAO;
import io.github.sanggu39.main.ui.Ui;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AccountManager {
    UserDAO userDAO = new UserDAO();
    public void login() {
        User user = null;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("id를 입력하세요");
            System.out.print(">");
            String id =  sc.next();
            System.out.println("비밀번호를 입력하세요");
            System.out.print(">");
            String pswd = sc.next();
            List<User> users = userDAO.slectAll();
            boolean isExist = false;
            String uPswd = null;
            for (int i=0; i + 1 <= users.size();i++) {
                user = users.get(i);
                String userId = user.getId();
                if(Objects.equals(id, userId)) {
                    isExist = true;
                    uPswd = user.getPswd();
                    break;
                }
            }
            if(!isExist || !pswd.equals(uPswd)) {
                System.out.println("id나 비밀번호가 유효하지 않습니다.");
            } else break;
        }
        Ui ui = new Ui();
        ui.showMain(user);
    }

    public void regist() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("id를 입력해 주세요");
            System.out.print(">");
            String id = sc.next();
            List<User> users = userDAO.slectAll();
            boolean isExist = false;
            for (int i=0; i + 1 <= users.size();i++) {
                User user = users.get(i);
                String userId = user.getId();
                if(Objects.equals(id, userId)) {
                    isExist = true;
                    break;
                }
            }
            if(isExist) {
                System.out.println("이미 존재하는 id입니다.");
                continue;
            }
            System.out.println("비밀번호를 입력해주세요.");
            System.out.print(">");
            String pswd = sc.next();
            User user = new User(users.get(users.size() - 1).getUserNum() + 1, id, pswd);
            userDAO.registUser(user);
            break;
        }
    }

}
