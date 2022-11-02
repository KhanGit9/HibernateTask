package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("apti", "ramzan", (byte) 21);
        userService.saveUser("Rasul", "Jakson", (byte) 19);
        userService.saveUser("Khan", "Bubuev", (byte) 33);
        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
