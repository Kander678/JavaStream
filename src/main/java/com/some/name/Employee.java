package com.some.name;

import java.util.Optional;

public class Employee {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;
    private String department;

    public Employee(String firstName, String lastName, int age, String department,double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + age + " lat) - " + department + ": " + salary;
    }

}
