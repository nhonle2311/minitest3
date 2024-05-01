package org.example.minitest3.sevice;

import org.example.minitest3.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(String id);

    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(String id);
}
