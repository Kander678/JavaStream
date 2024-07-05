package com.some.name;

import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Employee> employees=new ArrayList<>(List.of(
                new Employee("Jan", "Kowalski", 25, "IT", 4500.00),
                new Employee("Anna", "Nowak", 40, "HR", 5500.00),
                new Employee("Paweł", "Zieliński", 35, "IT", 6000.00),
                new Employee("Ewa", "Wiśniewska", 28, "Marketing", 4000.00),
                new Employee("Marek", "Wójcik", 50, "HR", 7000.00)
        ));
        List<Employee> highestSalary=employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .stream().collect(Collectors.toList());
        System.out.println("Highest Salary"+highestSalary);

        OptionalDouble averageSalary=employees.stream().mapToDouble(Employee::getSalary).average();
        System.out.println("Average Salary"+averageSalary);

        List<Employee> olderThen30=employees.stream().filter(employee -> employee.getAge()>30).collect(Collectors.toList());
        System.out.println("Employee older then 30 = "+olderThen30);

        Map<String, Long> departmentCount = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("Number of people working in department: " + departmentCount);

        List<Employee> sortingListByName=employees.stream()
                .sorted((el1,el2)->el1.getFirstName().compareTo(el2.getFirstName()))
                .collect(Collectors.toList());
        System.out.println("Sorted List by first Name="+sortingListByName);

        List<Employee> sortingListByNameAndLastName=employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName).thenComparing(Employee::getFirstName))
                .collect(Collectors.toList());
        System.out.println("Sorted List by First and Last Name= =" +sortingListByNameAndLastName);

        String department="IT";
        List<Employee> ListOfSpecificDepartment=employees.stream()
                .filter(employee -> employee.getDepartment()==department)
                .collect(Collectors.toList());
        System.out.println("People from "+department+" "+ ListOfSpecificDepartment);

        Map<String,Double> listOfAverageSalaryByDepartment=employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Average salary depending on Department= "+listOfAverageSalaryByDepartment);

        Map<String, Double> highestSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                                optionalEmp -> optionalEmp.map(Employee::getSalary).orElse(0.0)
                        )));
        System.out.println("Highest pay per Department = "+highestSalaryByDepartment);


    }
}