package com.flycode.graphnetworktools;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by anhaytananun on 19.04.16.
 */
public class SimilarityCounter {
    private static final BipartiteConfiguration[] bipartiteConfigurations = {
            new BipartiteConfiguration(0, 1, 5, "bipartite-23"),
            new BipartiteConfiguration(0, 2, 6, "bipartite-24"),
            new BipartiteConfiguration(0, 3, 7, "bipartite-25"),
            new BipartiteConfiguration(1, 2, 9, "bipartite-34"),
            new BipartiteConfiguration(1, 3, 10, "bipartite-35"),
            new BipartiteConfiguration(2, 3, 12, "bipartite-45")
    };

    private static final SingleConfiguration[] singleConfigurations = {
            new SingleConfiguration(1, 8, "single-33"),
            new SingleConfiguration(2, 11, "single-44"),
            new SingleConfiguration(3, 13, "single-55")
    };

    public static void main(String[] args) throws IOException {
        String location = SimilarityCounter.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        String line;

        int rawCount = 0;
        int realRawCount = 0;

        for (BipartiteConfiguration bipartiteConfiguration : bipartiteConfigurations) {
            FileWriter  outputFileWriter;
            FileReader inputFileReader;
            BufferedWriter outputBufferedWriter;
            BufferedReader inputBufferedReader;

            inputFileReader = new FileReader(location + fileName + ".txt");
            outputFileWriter = new FileWriter(location + fileName + "_" + bipartiteConfiguration.tag + ".txt");
            inputBufferedReader = new BufferedReader(inputFileReader);
            outputBufferedWriter = new BufferedWriter(outputFileWriter);

            ArrayList<Bipartite> bipartites = new ArrayList<>();

            try {
                while ((line = inputBufferedReader.readLine()) != null) {
                    line = line.replaceAll(" |\t", " ").trim();
                    String[] numbers = line.split(" ");

                    int vFromNumber = new Integer(numbers[bipartiteConfiguration.vFromIndex]);
                    int vToNumber = new Integer(numbers[bipartiteConfiguration.vToIndex]);
                    int eNumber = new Integer(numbers[bipartiteConfiguration.eIndex]);

                    Bipartite bipartite = new Bipartite(vFromNumber, vToNumber, eNumber);
                    boolean bipartiteExists = false;

                    for (Bipartite existingBipartite : bipartites) {
                        if (existingBipartite.equals(bipartite)) {
                            bipartiteExists = true;
                            break;
                        }
                    }

                    if (!bipartiteExists) {
                        rawCount++;

                        if (eNumber != 0) {
                            realRawCount++;
                        }

                        bipartites.add(bipartite);
                        outputBufferedWriter.write(bipartite.vFromNumber + "\t" + bipartite.vToNumber + "\t" + bipartite.eNumber + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            inputFileReader.close();
            inputBufferedReader.close();
            outputBufferedWriter.close();
            outputFileWriter.close();
        }

        System.out.println(rawCount + "\t" + realRawCount);

        for (SingleConfiguration singleConfiguration : singleConfigurations) {
            FileWriter  outputFileWriter;
            FileReader inputFileReader;
            BufferedWriter outputBufferedWriter;
            BufferedReader inputBufferedReader;

            inputFileReader = new FileReader(location + fileName + ".txt");
            outputFileWriter = new FileWriter(location + fileName + "_" + singleConfiguration.tag + ".txt");
            inputBufferedReader = new BufferedReader(inputFileReader);
            outputBufferedWriter = new BufferedWriter(outputFileWriter);

            ArrayList<Single> singles = new ArrayList<>();

            try {
                while ((line = inputBufferedReader.readLine()) != null) {
                    line = line.replaceAll(" |\t", " ").trim();
                    String[] numbers = line.split(" ");

                    int vIndex = new Integer(numbers[singleConfiguration.vIndex]);
                    int eNumber = new Integer(numbers[singleConfiguration.eIndex]);

                    Single single = new Single(vIndex, eNumber);
                    boolean singleExists = false;

                    for (Single existingSingle : singles) {
                        if (existingSingle.equals(single)) {
                            singleExists = true;
                            break;
                        }
                    }

                    if (!singleExists) {
                        rawCount++;

                        if (eNumber != 0) {
                            realRawCount++;
                        }

                        singles.add(single);
                        outputBufferedWriter.write(single.vNumber + "\t" + single.eNumber + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            inputFileReader.close();
            inputBufferedReader.close();
            outputBufferedWriter.close();
            outputFileWriter.close();
        }

        System.out.println(rawCount + "\t" + realRawCount);
    }

    private static class BipartiteConfiguration {
        private int vFromIndex;
        private int vToIndex;
        private int eIndex;
        private String tag;

        public BipartiteConfiguration(int vFromIndex, int vToIndex, int eIndex, String tag) {
            this.vFromIndex = vFromIndex;
            this.vToIndex = vToIndex;
            this.eIndex = eIndex;
            this.tag = tag;
        }

        public int getvFromIndex() {
            return vFromIndex;
        }

        public int getvToIndex() {
            return vToIndex;
        }

        public int geteIndex() {
            return eIndex;
        }

        public String getTag() {
            return tag;
        }
    }

    private static class SingleConfiguration {
        private int vIndex;
        private int eIndex;
        private String tag;

        public SingleConfiguration(int vIndex, int eIndex, String tag) {
            this.vIndex = vIndex;
            this.eIndex = eIndex;
            this.tag = tag;
        }

        public int getvIndex() {
            return vIndex;
        }

        public int geteIndex() {
            return eIndex;
        }

        public String getTag() {
            return tag;
        }
    }

    private static class Bipartite {
        private int vFromNumber;
        private int vToNumber;
        private int eNumber;

        public Bipartite(int vFromNumber, int vToNumber, int eNumber) {
            this.vFromNumber = vFromNumber;
            this.vToNumber = vToNumber;
            this.eNumber = eNumber;
        }

        public int getVFromNumber() {
            return vFromNumber;
        }

        public int getVToNumber() {
            return vToNumber;
        }

        public int getENumber() {
            return eNumber;
        }

        @Override
        public boolean equals(Object object) {
            if (!(object instanceof Bipartite)) {
                return false;
            }

            Bipartite bipartite = (Bipartite) object;

            return bipartite.vFromNumber == this.vFromNumber
                    && bipartite.vToNumber == this.vToNumber
                    && bipartite.eNumber == this.eNumber;
        }
    }

    private static class Single {
        private int vNumber;
        private int eNumber;

        public Single(int vNumber, int eNumber) {
            this.vNumber = vNumber;
            this.eNumber = eNumber;
        }

        public int getVNumber() {
            return vNumber;
        }

        public int getENumber() {
            return eNumber;
        }

        @Override
        public boolean equals(Object object) {
            if (!(object instanceof Single)) {
                return false;
            }

            Single single = (Single) object;

            return single.vNumber == this.vNumber
                    && single.eNumber == this.eNumber;
        }
    }
}
