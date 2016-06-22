package com.flycode.graphnetworktools;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.io.*;

/**
 * Created by anhaytananun on 28.04.16.
 */
public class GraphGenerator implements ClipboardOwner {
    private static IOPair[] bipartiteInputs = {
//            new IOPair("output70_clean-out_bipartite-23.txt", "bp23", 3),
//            new IOPair("output70_clean-out_bipartite-24.txt", "bp24", 4),
            new IOPair("output70_clean-out_bipartite-25.txt", "bp25", 5),
//            new IOPair("output70_clean-out_bipartite-34.txt", "bp34", 4),
//            new IOPair("output70_clean-out_bipartite-35.txt", "bp35", 5),
//            new IOPair("output70_clean-out_bipartite-45.txt", "bp45", 5)
    };

    private static IOPair[] singleGraphs = {
//            new IOPair("output70_clean-out_single-33.txt", "s33", 3),
//            new IOPair("output70_clean-out_single-44.txt", "s44", 4),
//            new IOPair("output70_clean-out_single-55.txt", "s55", 5)
    };

    public static void main(String[] args) throws IOException, InterruptedException {
        String location = SimilarityCounter.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        String line;
        String allCommandsString = "";

        int totalProcessed = 0;

        for (IOPair ioPair : bipartiteInputs) {
            System.out.println("Processing " + ioPair.getOutputFolder());

            FileReader inputFileReader;
            BufferedReader inputBufferedReader;

            inputFileReader = new FileReader(location + ioPair.getInputFile());
            inputBufferedReader = new BufferedReader(inputFileReader);

            while ((line = inputBufferedReader.readLine()) != null) {
                line = line.replaceAll(" |\t", " ").trim();
                String[] numbers = line.split(" ");

                try {
                    int vFromNumber = new Integer(numbers[0]);
                    int vToNumber = new Integer(numbers[1]);
                    int eNumber = new Integer(numbers[2]);

                    String command = "./home/anhaytananun/Documents/nauty26r5/geng -b "
                            + " -D" + ioPair.getMaxDegree()
                            + " " + (vFromNumber + vToNumber) + " "
                            + eNumber + ":" + eNumber
                            + " /home/anhaytananun/Desktop/network/graphs/dirty/" + ioPair.getOutputFolder() + "/" + vFromNumber + "_" + vToNumber + "_" + eNumber + ".txt";

//                    Process process = Runtime
//                            .getRuntime()
//                            .exec(
//                                    new String[]{
//                                            "bash",
//                                            "-c",
//                                            command
//                                    }
//                            );

                    allCommandsString = allCommandsString + command + " & ";

                    totalProcessed++;
                    System.out.println("Processed: " + totalProcessed);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }

        for (IOPair ioPair : singleGraphs) {
            System.out.println("Processing " + ioPair.getOutputFolder());

            FileReader inputFileReader;
            BufferedReader inputBufferedReader;

            inputFileReader = new FileReader(location + ioPair.getInputFile());
            inputBufferedReader = new BufferedReader(inputFileReader);

            while ((line = inputBufferedReader.readLine()) != null) {
                line = line.replaceAll(" |\t", " ").trim();
                String[] numbers = line.split(" ");

                try {
                    int vNumber = new Integer(numbers[0]);
                    int eNumber = new Integer(numbers[1]);

                    String command = "./home/anhaytananun/Documents/nauty26r5/geng "
                            + " -D" + ioPair.getMaxDegree()
                            + " " + vNumber + " "
                            + eNumber + ":" + eNumber
                            + " /home/anhaytananun/Desktop/network/graphs/dirty/" + ioPair.getOutputFolder() + "/" + vNumber + "_" + eNumber + ".txt";

                    Runtime
                            .getRuntime()
                            .exec(
                                    new String[]{
                                            "bash",
                                            "-c",
                                            command
                                    }
                            );

                    Thread.sleep(100);

                    allCommandsString = allCommandsString + command + " & ";

                    totalProcessed++;
                    System.out.println("Processed: " + totalProcessed);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }

        System.out.println(allCommandsString);

//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//        StringSelection stringSelection = new StringSelection(allCommandsString);
//        clipboard.setContents(stringSelection, new GraphGenerator());
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable transferable) {

    }

    private static class IOPair {
        private String inputFile;
        private String outputFolder;
        private int maxDegree;

        public IOPair(String inputFile, String outputFolder, int maxDegree) {
            this.inputFile = inputFile;
            this.outputFolder = outputFolder;
            this.maxDegree = maxDegree;
        }

        public String getInputFile() {
            return inputFile;
        }

        public void setInputFile(String inputFile) {
            this.inputFile = inputFile;
        }

        public String getOutputFolder() {
            return outputFolder;
        }

        public void setOutputFolder(String outputFolder) {
            this.outputFolder = outputFolder;
        }

        public int getMaxDegree() {
            return maxDegree;
        }

        public void setMaxDegree(int maxDegree) {
            this.maxDegree = maxDegree;
        }
    }
}
