package com.some.name;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    @Test
    void checkName(){
        Employee employee=new Employee("Kuba","Dori",25,"Something",25000);
        assertEquals("Kuba",employee.getFirstName());
    }
}