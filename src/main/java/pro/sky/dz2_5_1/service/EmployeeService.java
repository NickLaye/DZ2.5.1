package pro.sky.dz2_5_1.service;

import org.springframework.stereotype.Service;
import pro.sky.dz2_5_1.Employee;
import pro.sky.dz2_5_1.exceptions.EmloyeeNotFoundException;
import pro.sky.dz2_5_1.exceptions.EmployeeAlreadyAddedException;
import pro.sky.dz2_5_1.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    private static final int LIMIT = 10;

    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee remove(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (!employees.contains(employee)) {
            throw new EmloyeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }

    public Employee find(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (!employees.contains(employee)) {
            throw new EmloyeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
