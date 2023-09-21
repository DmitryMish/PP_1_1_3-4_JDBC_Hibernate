package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    /*public UserDaoJDBCImpl() {

    }*/
    private static final Connection connection = Util.getConnection();


    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS users ("
                + "id BIGINT NOT NULL AUTO_INCREMENT, "
                + "name VARCHAR(45) NOT NULL, "
                + "last_name VARCHAR(45) NOT NULL, "
                + "age TINYINT NOT NULL, "
                + "PRIMARY KEY (id))";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTable);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(drop);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String save = "INSERT INTO users (name, last_Name, age) VALUES ('" + name + "' , '" + lastName + "' , " + age + ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(save);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String delete = "DELETE FROM users WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.execute(delete);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getAll = "SELECT * FROM users";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAll);
            while (resultSet.next()) {
                users.add(new User(resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")));
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String clean = "TRUNCATE TABLE users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(clean);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
