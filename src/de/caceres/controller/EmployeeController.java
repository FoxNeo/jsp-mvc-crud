package de.caceres.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.caceres.dao.EmployeeDAO;
import de.caceres.dao.EmployeeDAOImpl;
import de.caceres.entity.Employee;

/**
 * Servlet implementation class EmployeeController
 */
public class EmployeeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    EmployeeDAO employeeDAO;
    RequestDispatcher dispatcher = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
        employeeDAO = new EmployeeDAOImpl();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Inside Servlet HTTP GET");

        if (action == null) {
            action = "LIST";
        }
        switch (action) {
        case "LIST":
            listEmployees(request, response);
            break;
        case "EDIT":
            getSingleEmployee(request, response);
            break;
        case "DELETE":
            deleteEmployee(request, response);
            break;
        default:
            listEmployees(request, response);
            break;
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("firstname");
        String dob = request.getParameter("dob");
        String department = request.getParameter("department");
        Employee employee = new Employee();

        employee.setName(name);
        employee.setDob(dob);
        employee.setDepartment(department);
        if (id.isEmpty() || id == null) {
            if (employeeDAO.save(employee)) {
                request.setAttribute("message", "Saved successfully!");
            }
        } else {
            employee.setId(Integer.parseInt(id));
            if (employeeDAO.update(employee)) {
                request.setAttribute("message", "Update successfully!");
            }
        }

        listEmployees(request, response);
    }

    public void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Employee> list = employeeDAO.get();
        request.setAttribute("list", list);
        dispatcher = request.getRequestDispatcher("/views/employee-list.jsp");
        dispatcher.forward(request, response);
    }

    public void getSingleEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Employee employee = employeeDAO.get(Integer.valueOf(id));
        request.setAttribute("employee", employee);

        dispatcher = request.getRequestDispatcher("/views/employee-add.jsp");
        dispatcher.forward(request, response);
    }
    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(employeeDAO.delete(Integer.parseInt(id))) {
            request.setAttribute("message", "Record has been deleted!");
        }
        listEmployees(request, response);
    }

}
