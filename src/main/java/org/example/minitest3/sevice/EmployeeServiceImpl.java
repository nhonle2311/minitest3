package org.example.minitest3.sevice;

import org.example.minitest3.model.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{
    private static final List<Employee> employees;

    static {
        employees = new ArrayList<>();
        initializeDefaultEmployees();
    }

    private static void initializeDefaultEmployees() {
        LocalDate today = LocalDate.now();
        employees.add(new Employee("1", "John Doe", "123 Main St", today.minusYears(30), 50000));
        employees.add(new Employee("2", "Jane Smith", "456 Oak Ave", today.minusYears(28), 60000));
        employees.add(new Employee("3", "Michael Johnson", "789 Elm St", today.minusYears(35), 55000));
        employees.add(new Employee("4", "Emily Davis", "101 Pine St", today.minusYears(32), 52000));
        employees.add(new Employee("5", "Chris Wilson", "202 Maple Ave", today.minusYears(29), 58000));
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployeeById(String id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }


    @Override
    public void updateEmployee(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(employee.getId())) {
                employees.set(i, employee);
                break;
            }
        }
    }


    @Override
    public void deleteEmployee(String id) {
        employees.removeIf(employee -> employee.getId().equals(id));
    }

}
