package com.ojt.tableApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class Table 
{   

    private static List<List<String>> tableContents;
    private static String tableFileName = "myFile.txt";

    public void setTableContents(List<List<String>> tableContents){
        this.tableContents = tableContents;
    }

    public List<List<String>> getTableContents(){
        return tableContents;
    }

    public String getTableFileName(){
        return tableFileName;
    }

    public void setTableFileName(String tableFileName){
        this.tableFileName = tableFileName;
    }

    public boolean loadTableFromFile(String path) throws FileNotFoundException{
        Scanner reader = new Scanner(new File(path));
        List<List<String>> contents = new ArrayList<>();
        List<String> rowInTable;

        while (reader.hasNextLine()) {
                rowInTable = new ArrayList<>();
                String currentLine = StringUtils.trim(reader.nextLine());
                String[] lineData = StringUtils.split(currentLine,",");
                for (int j = 0; j < lineData.length; j++) {
                    rowInTable.add(lineData[j]);
                }
                contents.add(rowInTable);
        }

        setTableFileName(path);
        setTableContents(contents);
        return true;
    }

    public boolean saveTableToFile(String path, List<List<String>> table) throws IOException{
        List<String> rowInTable;
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(path));
            for (int i = 0; i < table.size(); i++) {
                rowInTable = new ArrayList<>();
                rowInTable = table.get(i);
                for (int j = 0; j < rowInTable.size(); j++) {
                    if (j == rowInTable.size() - 1) {
                        outputWriter.write(rowInTable.get(j));
                    } else {
                        outputWriter.write(rowInTable.get(j) + ",");
                    }
                }
                outputWriter.newLine();
            }
            outputWriter.flush();
            outputWriter.close();
        return true;
    }


}
