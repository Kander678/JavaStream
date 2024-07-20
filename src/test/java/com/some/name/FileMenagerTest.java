package com.some.name;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileMenagerTest {
    @Test
    void saveTextTestToFile() throws IOException {
        File file = new File("test.txt");
        FileMenager fm = new FileMenager();

        try {
            fm.saveTextToFile(file);
            assertTrue(file.exists());

            List<String> line = Files.readAllLines(file.toPath());

            assertEquals(1, line.size());
            assertEquals("Something special", line.get(0));
        }
        catch (IOException e) {
            e.printStackTrace();
        }finally {
             file.delete();
        }
    }
    @Test
    void readTextTestFromFile(){
        FileMenager fm = new FileMenager();
        File file = new File("test.txt");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write("Something special");
        }catch (IOException e){
            System.out.println("Something went wrong"+e.getMessage());
        }

        try {
            fm.readTextFromFile(file);
            String content=Files.readString(file.toPath());
            assertEquals("Something special", content);
        }catch (IOException e){
            System.out.println("Something went wrong"+e.getMessage());
        }finally {
            if(file.exists()){
                file.delete();
            }
        }
    }
    @Test
    void saveObjectToFileTest(){
        File file = new File("test.txt");
        FileMenager fm = new FileMenager();

        fm.saveObjectToFile(file);
        assertTrue(file.exists());

        file.delete();
    }
    @Test
    void readObjectTestFromFile(){
        File file = new File("test.txt");
        FileMenager fm = new FileMenager();

        fm.saveObjectToFile(file);

        SingletonEmployee emp = fm.readObjectFromFile(file);
        assertNotNull(emp);

        assertEquals(6,SingletonEmployee.getInstance().getEmployeeList().size());
        assertEquals("Kowalski",SingletonEmployee.getInstance().getEmployeeList().stream()
                                                                                    .map(Employee::getLastName)
                                                                                    .findFirst().orElse(null));
        file.delete();
    }

}