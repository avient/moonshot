package utils;

import infrastructure.Configuration;
import infrastructure.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CsvParser {
    private static CsvParser instance = null;
    private static final String DownloadsDirectory = Configuration.getInstance().getProperty("tempDataDirectory");

    private CsvParser() {
    }

    public static synchronized CsvParser getInstance() {
        if (instance == null) {
            instance = new CsvParser();
        }
        return instance;
    }

    public List<List<String>> parseToList(String fileName) {
        Logger.getInstance().info(String.format("Parsing csv file %s to list", fileName));
        File file = new File(DownloadsDirectory + File.separator + fileName);

        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try {
            inputStream = new Scanner(file);

            while (inputStream.hasNext()) {
                String line = inputStream.next();
                String[] values = line.split(",");
                lines.add(Arrays.asList(values));
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void writeToFile(List<List<String>> list, String fileName) {
        Logger.getInstance().info("Writing to csv file");
        File file = new File(DownloadsDirectory + File.separator + fileName);
        FileWriter csvWriter;
        try {
            csvWriter = new FileWriter(file);
            for (List<String> rowData : list) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
