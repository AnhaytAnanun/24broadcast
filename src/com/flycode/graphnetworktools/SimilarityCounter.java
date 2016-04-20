package com.flycode.graphnetworktools;

import java.io.*;
import java.util.Scanner;

/**
 * Created by anhaytananun on 19.04.16.
 */
public class SimilarityCounter {
    public static void main(String[] args) {
        String location = SimilarityCounter.class.getProtectionDomain().getCodeSource().getLocation().getFile();

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
        int e23, e24, e25, e33, e34, e35, e44, e45, e55;

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
                    e23 = Integer.valueOf(numbers[5]);
                    e24 = Integer.valueOf(numbers[6]);
                    e25 = Integer.valueOf(numbers[7]);
                    e33 = Integer.valueOf(numbers[8]);
                    e34 = Integer.valueOf(numbers[9]);
                    e35 = Integer.valueOf(numbers[10]);
                    e44 = Integer.valueOf(numbers[11]);
                    e45 = Integer.valueOf(numbers[12]);
                    e55 = Integer.valueOf(numbers[13]);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Triplet {
        private int vFromNumber;
        private int vToNumber;
        private int vEdgeNumber;

        public Triplet(int vFromNumber, int vToNumber, int vEdgeNumber) {
            this.vFromNumber = vFromNumber;
            this.vToNumber = vToNumber;
            this.vEdgeNumber = vEdgeNumber;
        }

        public int getvFromNumber() {
            return vFromNumber;
        }

        public int getvToNumber() {
            return vToNumber;
        }

        public int getvEdgeNumber() {
            return vEdgeNumber;
        }
    }
}
