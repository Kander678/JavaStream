package com.some.name;

import java.util.ArrayList;
import java.util.List;

public class SingletonEmployee {
    private static final SingletonEmployee instance = new SingletonEmployee();

    private SingletonEmployee() {
    }

    public static SingletonEmployee getInstance() {
        return instance;
    }

    public List<Employee> getEmployeeList() {
        return new ArrayList<>(List.of(
                new Employee("Jan", "Kowalski", 30, "IT", 5500.00),
                new Employee("Jan", "Kowalski", 42, "IT", 14000.00),
                new Employee("Anna", null, 25, "HR", 4500.00),
                new Employee("Piotr", "Wiśniewski", 40, "Finanse", 7000.00),
                new Employee("Marta", "Lewandowska", 35, "Marketing", 6000.00),
                new Employee("Tomasz", "Zieliński", 28, "Sprzedaż", 4800.00)));
    }

    public Employee firstLastName(String lastName) {
        Employee employee = getEmployeeList().stream()
                .filter(emp->emp.getLastName().orElse("").equals(lastName))
                .findFirst().orElse(null);
        return employee;
    }
}
