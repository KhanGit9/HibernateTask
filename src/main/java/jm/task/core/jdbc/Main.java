package jm.task.core.jdbc;

import jm.task.core.jdbc.util.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("FGbsd", "124124f", (byte) 11);
        userService.saveUser("FGbsd", "aegasdfg", (byte) 21);
        userService.saveUser("FGbsd", "adsfg", (byte) 8);
        userService.saveUser("FGbsd", "aadfgsf", (byte) 16);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
