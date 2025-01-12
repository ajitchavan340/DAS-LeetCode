package org.example.stream;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

public class Lambdas {
    public static void main(String[] args) {
       File[] file =  new File(".").listFiles(File::isHidden);
        System.out.println(Arrays.toString(file));
    }
}
