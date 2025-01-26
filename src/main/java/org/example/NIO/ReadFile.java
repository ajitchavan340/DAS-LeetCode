package org.example.NIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class ReadFile {

    public void readFile(){
        long uniqueWords = 0;
        try (Stream<String> s = Files.lines(Paths.get("abc.txt"))){
           uniqueWords = s.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println(uniqueWords);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("abc.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line ="";
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ReadFile readFile = new ReadFile();
        readFile.readFile();

    }
}
