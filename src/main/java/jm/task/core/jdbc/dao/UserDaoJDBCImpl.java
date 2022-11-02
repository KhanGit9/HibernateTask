package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }
    Connection connection = Util.getInstance().getConnection();
    public void createUsersTable() throws SQLException {
        try (Connection connection = Util.getInstance().getConnection()) {
            connection.setAutoCommit(false);
            connection.createStatement().execute("CREATE TABLE  IF NOT EXISTS pall (id BIGINT PRIMARY KEY " +
                    "AUTO_INCREMENT NOT NULL, name VARCHAR (45), " +
                    "lastname VARCHAR (45), age TINYINT(2))");
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();

        }
    }
    public void dropUsersTable() throws SQLException {
    String sql = "DROP TABLE IF EXISTS pall";
        try (Connection connection = Util.getInstance().getConnection()) {
            connection.setAutoCommit(false);
            connection.createStatement().executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO pall (name, lastname, age) VALUES (?, ?, ?)";
        try (PreparedStatement pst = Util.getInstance().getConnection().prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setByte(3, age);
            pst.execute();
            System.out.println("Польлзователь с именим " + name + " добавлен");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM pall where id - ?";
        try (PreparedStatement preparedStatement = Util.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            System.out.println("Пользователь с id " + id + "удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * from pall";
        try (Connection connection = Util.getInstance().getConnection()) {
            connection.setAutoCommit(false);
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                connection.commit();
            }
            System.out.println(users);
        } catch (SQLException e) {
            connection.rollback();
        e.printStackTrace();
        }
        return users;
    }
    public void cleanUsersTable() throws SQLException {
        String sql = "TRUNCATE pall";
       try (Connection connection = Util.getInstance().getConnection()) {
           connection.setAutoCommit(false);
           connection.createStatement().executeUpdate(sql);
           connection.commit();
       } catch (SQLException e) {
           connection.rollback();
       }
    }
}
