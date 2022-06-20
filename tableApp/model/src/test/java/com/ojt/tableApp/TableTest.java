package com.ojt.tableApp;

import static org.junit.Assert.*;


import java.io.FileNotFoundException;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;


public class TableTest 
{
    
    Table table = new Table();

    @Test (expected = FileNotFoundException.class)
    public void loadATableFromAnExistingFile_FileNotFoundException() throws FileNotFoundException{ 
       String path = "Sample/Dummy/Path";
       table.loadTableFromFile(path);
    }

    @Test
    public void loadATableFromAnExistingFile_Successful() {
        try{
        String path = "/Users/MSI/Downloads/file.txt";
        List<List<String>> mlist =  Arrays.asList(Arrays.asList("MEk=e^z","m!?=W^*"),
                                                  Arrays.asList("(<d=81S","3xs=^Cb"));
        table.loadTableFromFile(path);
        assertEquals(mlist, table.getTableContents());
        }catch(FileNotFoundException FNF){
            System.out.print(FNF);
        }
    }

    @Test
    public void loadATableFromAnExistingFile_Unsuccessful() {
        try{
        String path = "/Users/MSI/Downloads/file.txt";
        List<List<String>> mlist =  Arrays.asList(Arrays.asList("Ek=e^z","m!?=W^*"),
                                                  Arrays.asList("(<d=81S","3xs=^Cb"));
        table.loadTableFromFile(path);
        assertNotEquals(mlist, table.getTableContents());
        }catch(FileNotFoundException FNF){
            System.out.print(FNF);
        }
    }


    @Test (expected = IOException.class) 
    public void saveTableToFile_IOException() throws IOException{
        String path = "";
        table.saveTableToFile("", table.getTableContents());
    } 

    @Test
    public void saveTableToFile_Successful() {
        try{
        String path = "/Users/MSI/Downloads/file1.txt";
        List<List<String>> mlist =  Arrays.asList(Arrays.asList("MEk=e^z","m!?=W^*"),
                                                  Arrays.asList("(<d=81S","3xs=^Cb"));
        assertTrue(table.saveTableToFile(path, mlist));
        }catch(IOException IOE){
            System.out.print(IOE);
        }
    }

}
