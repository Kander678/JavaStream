package com.some.name;

import java.io.*;

public class FileMenager {
    public void saveTextToFile(File file){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write("Something special");
        }catch (IOException e){
            System.out.println("Something went wrong"+e.getMessage());
        }
    }
    public void readTextFromFile(File file){
        try {
            File myfile = new File(file.getPath());
            FileReader read = new FileReader(myfile);
            BufferedReader br = new BufferedReader(read);

            String wiersz;
            while((wiersz=br.readLine())!=null){
                System.out.println(wiersz);
            }
        }catch (Exception ex){
            System.out.println("Something went wrong"+ex.getMessage());
        }
    }
    public void saveObjectToFile(File file){
        SingletonEmployee singletonEmployee=SingletonEmployee.getInstance();
        try(FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream out=new ObjectOutputStream(fos)){

            out.writeObject(singletonEmployee);
        }catch (IOException e){
            System.out.println("Something went wrong"+e.getMessage());
        }
    }
    public SingletonEmployee readObjectFromFile(File file){
        SingletonEmployee employee=null;
        try(FileInputStream fos = new FileInputStream(file);
            ObjectInputStream out=new ObjectInputStream(fos)){
            employee=(SingletonEmployee) out.readObject();
        }catch (IOException e){
            System.out.println("Something went wrong"+e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

}
