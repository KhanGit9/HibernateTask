package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:mysql://localhost:3306/pal";
    private static final String username = "root";
    private static final String password = "a27736254321";
    private static Connection connection = null;
    private static Util instance;
    private Util () {}
    public static Util getInstance() {
        if (instance==null) {
            instance = new Util();
        }
        return instance;
    }
    SessionFactory factory;











    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}


