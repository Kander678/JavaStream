package com.some.name;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;
public class MainUT {
    private List<Employee> employees;
   @BeforeEach
    void setUp() {
       employees = new ArrayList<>(List.of(
               new Employee("Jan", "Kowalski", 25, "IT", 4500.00),
               new Employee("Anna", "Nowak", 40, "HR", 5500.00),
               new Employee("Paweł", "Zieliński", 35, "IT", 6000.00),
               new Employee("Ewa", "Wiśniewska", 28, "Marketing", 4000.00),
               new Employee("Marek", "Wójcik", 50, "HR", 7000.00)
       ));
   }

   @Test
    void highestSalaryTest() {
       Optional<Double> highestSalary=employees.stream()
               .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
               .map(Employee::getSalary);
       assertTrue(highestSalary.isPresent());
       assertEquals(7000.0,highestSalary.get());
   }
   @Test
   void averageSalary(){
       OptionalDouble averageSalary=employees.stream()
               .mapToDouble(Employee::getSalary)
               .average();
       assertTrue(averageSalary.isPresent());
       assertEquals(5400.0,averageSalary.getAsDouble());
   }
   @Test
   void olderThan30First(){
       Optional<String> olderThan30=employees.stream()
               .filter(emp->emp.getAge()>30)
               .map(emp->emp.getFirstName()+" "+emp.getLastName().orElse(""))
               .findFirst();

       assertTrue(olderThan30.isPresent());
       assertEquals("Anna Nowak", olderThan30.get());
   }

   @Test
    void departmentCount(){
       Map<String, Long> departmentCount = employees.stream()
               .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
       assertEquals(1,departmentCount.get("Marketing"));
       assertEquals(2,departmentCount.get("IT"));
       assertEquals(2,departmentCount.get("HR"));
   }
   @Test
   void sortingByFirstName(){
       Optional<String > sortingListByName=employees.stream()
               .sorted((el1,el2)->el1.getFirstName().compareTo(el2.getFirstName()))
               .map(Employee::getFirstName)
               .findFirst();
       assertTrue(sortingListByName.isPresent());
       assertEquals("Anna",sortingListByName.get());
   }
   @Test
   void sortingByFirstAndLastName(){
       Optional<String > sortingListByNameAndLastName=employees.stream()
               .sorted(Comparator.comparing(Employee::getFirstName).thenComparing(eli->eli.getLastName().orElse("")))
               .map(eli->eli.getFirstName()+" "+eli.getLastName().orElse(""))
               .findFirst();
       assertTrue(sortingListByNameAndLastName.isPresent());
       assertEquals("Anna Nowak",sortingListByNameAndLastName.get());
   }

   @Test
    void peopleInItDepartment(){
       List<Employee> ListOfSpecificDepartment=employees.stream()
               .filter(employee -> employee.getDepartment()=="IT")
               .collect(Collectors.toList());
       List<Employee> expectedList = List.of(
               new Employee("Jan", "Kowalski", 25, "IT", 4500.00),
               new Employee("Paweł", "Zieliński", 35, "IT", 6000.00)
       );

//       assertEquals(expectedList, ListOfSpecificDepartment);
   }
    @Test
    void averageSalaryByDepartment(){
        Map<String,Double> listOfAverageSalaryByDepartment=employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));

        assertEquals(6250.0,listOfAverageSalaryByDepartment.get("HR"));
        assertEquals(5250.0,listOfAverageSalaryByDepartment.get("IT"));
        assertEquals(4000.0,listOfAverageSalaryByDepartment.get("Marketing"));
    }
    @Test
    public void highestSalaryByDepartment() {
        Map<String, Double> listOfHighestSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                                emp -> emp.map(Employee::getSalary).orElse(0.0)
                        )));

        assertEquals(6000.00, listOfHighestSalaryByDepartment.get("IT"));
        assertEquals(7000.00, listOfHighestSalaryByDepartment.get("HR"));
        assertEquals(4000.00, listOfHighestSalaryByDepartment.get("Marketing"));
    }

    @Test
    public void findEmployeeWithNullLastName() {
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee("Jan", "Kowalski", 30, "IT", 5500.00),
                new Employee("Anna", null, 25, "HR", 4500.00),
                new Employee("Piotr", "Wiśniewski", 40, "Finanse", 7000.00),
                new Employee("Marta", "Lewandowska", 35, "Marketing", 6000.00),
                new Employee("Tomasz", "Zieliński", 28, "Sprzedaż", 4800.00)));

        Optional<Employee> foundEmployee=employees.stream()
                .filter(emp->emp.getLastName()==null)
                .findFirst();

        assertFalse(foundEmployee.isPresent());
        assertEquals(null,foundEmployee.get().getLastName());
   }


}
