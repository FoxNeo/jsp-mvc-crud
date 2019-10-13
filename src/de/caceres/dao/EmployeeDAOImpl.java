package de.caceres.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.caceres.entity.Employee;
import de.caceres.util.DBConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

    PreparedStatement prepareStatement = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    @Override
    public List<Employee> get() {
        List<Employee> list = null;
        Employee employee = null;
        try {
            list = new ArrayList<Employee>();

            String sql = "SELECT * FROM tbl_employee";

            connection = DBConnectionUtil.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setDepartment(resultSet.getString("department"));
                employee.setDob(resultSet.getString("dob"));
                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Employee e) {
        boolean flag = false;
        try {
            String sql = "INSERT INTO tbl_employee(name, dob, department)VALUES('" + e.getName() + "', '" + e.getDob()
                    + "', '" + e.getDepartment() + "')";
            connection = DBConnectionUtil.openConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public Employee get(int id) {
        Employee employee = null;
        try {
            employee = new Employee();
            String sql = "SELECT * FROM tbl_employee WHERE id=" + id;
            connection = DBConnectionUtil.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setDepartment(resultSet.getString("department"));
                employee.setDob(resultSet.getString("dob"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(employee.getName());
        return employee;
    }

    @Override
    public boolean update(Employee e) {
        boolean flag = false;
        try {
            String sql = "UPDATE tbl_employee SET name='" + e.getName() + "', dob='" + e.getDob() + "', department='"
                    + e.getDepartment() + "' where id=" + e.getId();
            connection = DBConnectionUtil.openConnection();
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean delete(int id) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM tbl_employee WHERE id=" + id;
            connection = DBConnectionUtil.openConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
}
}
