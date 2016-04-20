package com.flycode.graphnetworktools;

import java.io.*;
import java.util.Scanner;

/**
 * Created by anhaytananun on 19.04.16.
 */
public class NodeCounter {
    public static void main(String[] args) {
        String location = NodeCounter.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        FileReader inputFileReader;
        BufferedReader inputBufferedReader;
        FileWriter  outputFileWriter;
        BufferedWriter outputBufferedWriter;

        try {
            inputFileReader = new FileReader(location + fileName + ".txt");
            outputFileWriter = new FileWriter(location + fileName + "-out.txt");
            inputBufferedReader = new BufferedReader(inputFileReader);
            outputBufferedWriter = new BufferedWriter(outputFileWriter);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String line;
        int v2, v3, v4, v5, max_degree;
        int sum;

        try {
            while ((line = inputBufferedReader.readLine()) != null) {
                line = line.replaceAll(" |\t", " ").trim();
                String[] numbers = line.split(" ");

                try {
                    v2 = Integer.valueOf(numbers[0]);
                    v3 = Integer.valueOf(numbers[1]);
                    v4 = Integer.valueOf(numbers[2]);
                    v5 = Integer.valueOf(numbers[3]);
                    max_degree = Integer.valueOf(numbers[4]);
                } catch (NumberFormatException e) {
                    continue;
                }

                sum = v2 + v3 + v4 + v5;

                outputBufferedWriter.write(sum + "\t" + v5 + "\t" + max_degree + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
