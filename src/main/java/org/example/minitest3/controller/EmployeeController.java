package org.example.minitest3.controller;

import org.example.minitest3.model.Employee;
import org.example.minitest3.sevice.EmployeeService;
import org.example.minitest3.sevice.EmployeeServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import java.util.UUID;


@WebServlet(name = "EmployeeController", urlPatterns = "/employee/*")
public class EmployeeController extends HttpServlet {
    private EmployeeService employeeService;

    public void init() {
        employeeService = new EmployeeServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action) {
            case "list":
                listEmployees(request, response);
                break;
            case "show":
                showEmployee(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            default:
                listEmployees(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action) {
            case "add":
                addEmployee(request, response);
                break;
            case "edit":
                editEmployee(request, response);
                break;
            default:
                response.sendRedirect("/employee?action=list");
                break;
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Employee> employees = employeeService.getAllEmployees();
        request.setAttribute("employees", employees);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/employee_list.jsp");
        dispatcher.forward(request, response);
    }

    private void showEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Employee employee = employeeService.getEmployeeById(id);
        request.setAttribute("employee", employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/employee_details.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/employee_form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Employee existingEmployee = employeeService.getEmployeeById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/employee_form.jsp");
        request.setAttribute("employee", existingEmployee);
        dispatcher.forward(request, response);
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        employeeService.deleteEmployee(id);
        response.sendRedirect("/employee?action=list");
    }


    private void addEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = UUID.randomUUID();
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        double salary = Double.parseDouble(request.getParameter("salary"));
        Employee newEmployee = new Employee(id.toString(), fullName, address, dob, salary);
        employeeService.addEmployee(newEmployee);
        response.sendRedirect("/employee?action=list");
    }



    private void editEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        double salary = Double.parseDouble(request.getParameter("salary"));
        Employee updatedEmployee = new Employee(id, fullName, address, dob, salary);
        employeeService.updateEmployee(updatedEmployee);
        response.sendRedirect("/employee?action=list");
    }

}

