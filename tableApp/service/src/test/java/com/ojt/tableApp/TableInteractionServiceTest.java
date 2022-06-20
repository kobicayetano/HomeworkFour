package com.ojt.tableApp;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class TableInteractionServiceTest{
	
	Table table = new Table();
	TableInteractionService tis = new TableInteractionService();

	@Test
	public void populateTableContents_ShouldHaveSameValue(){

		List<List<String>> actual = tis.populateTableContents(4,5);
		assertEquals(table.getTableContents(), actual);

	}	

	@Test
	public void populateTableContents_ShouldHaveSize4And5(){
		tis.populateTableContents(4,5);
		assertEquals(table.getTableContents().size(), 4);
		assertEquals(table.getTableContents().get(0).size(), 5);
	}

	@Test
    public void printTableContents_ShouldHaveSameOutput() throws FileNotFoundException{
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      	System.setOut(new PrintStream(outContent));

        List<List<String>> myList =  Arrays.asList(Arrays.asList("aaa=aaa","aaa=aaa"),
                                                  Arrays.asList("bbb=bbb","bbb=bbb"));

        String expected = "aaa,aaa\taaa,aaa\t\nbbb,bbb\tbbb,bbb\t\n";
        table.setTableContents(myList);
        tis.printTableContents(myList);

        assertEquals(expected, outContent.toString());
        
    }



	
}