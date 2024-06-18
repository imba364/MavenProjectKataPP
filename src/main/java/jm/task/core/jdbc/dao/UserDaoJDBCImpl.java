package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS users(" +
                    "ID BIGINT NOT NULL AUTO_INCREMENT, NAME VARCHAR (45), " +
                    "LASTNAME VARCHAR (45), AGE TINYINT, PRIMARY KEY (ID))";

            statement.executeUpdate(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                    statement.close();
                }
            if (connection != null) {
                    connection.close();
                }
            }
        }


    @Override
    public void dropUsersTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS users";

            statement.executeUpdate(sql);
            System.out.println("Table dropped");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                    statement.close();
            }
            if (connection != null) {
                    connection.close();
                }
            }
        }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES(?, ?, ?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User saved");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                    preparedStatement.close();
                }
            if (connection != null) {
                    connection.close();
                }
            }
        }

    public void removeUserById(long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM users WHERE ID=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                    preparedStatement.close();
            }
            if (connection != null) {
                    connection.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            String sql = "SELECT ID, NAME, LASTNAME, AGE FROM users";
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
            System.out.println("Users loaded");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            String sql = "DELETE FROM users";
            connection = getConnection();
            statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Table cleaned");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
