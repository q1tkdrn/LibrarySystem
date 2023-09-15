package io.github.sanggu39.main;

import io.github.sanggu39.main.sql.BookDAO;
import io.github.sanggu39.main.ui.Ui;

public class Main {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAO();
        Ui ui = new Ui();
    }
}
