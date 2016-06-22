package com.flycode.graphnetworktools;

import java.io.*;
import java.util.Scanner;

/**
 * Created by anhaytananun on 19.04.16.
 */
public class NodeCounter {
    public static void main(String[] args) {
//        nodeCountTotal();
        cleaner(24);
    }

    public static void cleaner(int nodesCount) {
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
        int v2, v3, v4, v5;
        int sum;
        int rawSum = 0;

        try {
            while ((line = inputBufferedReader.readLine()) != null) {
                String lineBackup = line;
                line = line.replaceAll(" |\t", " ").trim();
                String[] numbers = line.split(" ");

                try {
                    v2 = Integer.valueOf(numbers[0]);
                    v3 = Integer.valueOf(numbers[1]);
                    v4 = Integer.valueOf(numbers[2]);
                    v5 = Integer.valueOf(numbers[3]);
                } catch (NumberFormatException e) {
                    continue;
                }

                sum = v2 + v3 + v4 + v5;

                if (sum == nodesCount) {
                    rawSum++;
                    outputBufferedWriter.write(lineBackup);
                    outputBufferedWriter.write("\n");
                }
            }

            System.out.println(rawSum);

            inputBufferedReader.close();
            inputFileReader.close();
            outputBufferedWriter.close();
            outputFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nodeCountTotal() {
        String location = NodeCounter.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        FileReader inputFileReader;
        BufferedReader inputBufferedReader;

        try {
            inputFileReader = new FileReader(location + fileName + ".txt");
            inputBufferedReader = new BufferedReader(inputFileReader);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String line;
        int v2, v3, v4, v5;
        int rawSum = 0;

        try {
            while ((line = inputBufferedReader.readLine()) != null) {
                line = line.replaceAll(" |\t", " ").trim();
                String[] numbers = line.split(" ");

                try {
                    v2 = Integer.valueOf(numbers[0]);
                    v3 = Integer.valueOf(numbers[1]);
                    v4 = Integer.valueOf(numbers[2]);
                    v5 = Integer.valueOf(numbers[3]);
                } catch (NumberFormatException e) {
                    continue;
                }

                if (v2 + v3 + v4 + v5 == 24) {
                    rawSum++;
                }
            }

            System.out.println(rawSum);

            inputBufferedReader.close();
            inputFileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nodeCountPerConf() {
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

            inputBufferedReader.close();
            inputFileReader.close();
            outputBufferedWriter.close();
            outputFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
