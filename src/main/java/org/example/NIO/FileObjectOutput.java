package org.example.NIO;

import org.example.stream.part2.practice.Trader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileObjectOutput {

    public static void main(String[] args) {
       try(FileOutputStream fileOutputStream = new FileOutputStream("save.txt")){
           ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
           objectOutputStream.writeObject(new Trader("Raoul", "Cambridge"));
           objectOutputStream.writeObject(new Trader("Mario","Milan"));
           objectOutputStream.writeObject(new Trader("Alan","Cambridge"));
           objectOutputStream.writeObject(new Trader("Brian","Cambridge"));
       }catch (Exception e){
           e.printStackTrace();
       }

       try(FileInputStream fileInputStream = new FileInputStream("save.txt")){
           ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
           Trader v = (Trader) objectInputStream.readObject();
           System.out.println(v);
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
