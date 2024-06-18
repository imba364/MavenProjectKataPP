package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl extends UserDaoJDBCImpl implements UserService  {

    public void createUsersTable() throws SQLException {
        super.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        super.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        super.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        super.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
       return super.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        super.cleanUsersTable();
    }
}
