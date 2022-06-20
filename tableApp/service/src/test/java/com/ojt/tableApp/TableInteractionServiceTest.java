package com.ojt.tableApp;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.io.ByteArrayOutputStream;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class TableInteractionServiceTest{
	
	Table table = new Table();
	TableInteractionService tis = new TableInteractionService();

	@Test
	public void populateTableContents_ShouldHaveSameValue(){
		try{
		List<List<String>> actual = tis.populateTableContents(4,5);
		assertEquals(table.getTableContents(), actual);
		}catch(IOException IOE){
			System.out.print(IOE);
		}

	}	

	@Test
	public void populateTableContents_ShouldHaveSize4And5(){
		try{
		tis.populateTableContents(4,5);
		assertEquals(table.getTableContents().size(), 4);
		assertEquals(table.getTableContents().get(0).size(), 5);
		}catch(IOException IOE){
			System.out.print(IOE);
		}
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

    @Test 
    public void editTableContent_EditKey(){
    	try{
    	List<List<String>> myList =  Arrays.asList(Arrays.asList("aaa=aaa","aaa=aaa"),
                                                  Arrays.asList("bbb=bbb","bbb=bbb"));
    	table.setTableContents(myList);
    	String output = tis.editTableContent(0,0,"KEY","111");
    	assertEquals(table.getTableContents().get(0).get(0), output);
    	}catch(IOException IOE){
			System.out.print(IOE);
		}
    }

    @Test 
    public void editTableContent_EditValue(){
    	try{
    	List<List<String>> myList =  Arrays.asList(Arrays.asList("aaa=aaa","aaa=aaa"),
                                                  Arrays.asList("bbb=bbb","bbb=bbb"));
    	table.setTableContents(myList);
    	String output = tis.editTableContent(0,0,"KEY","111");
    	assertEquals(table.getTableContents().get(0).get(0), output);
    	}catch(IOException IOE){
			System.out.print(IOE);
		}
    }

    @Test 
    public void editTableContent_NotKeyOrValue(){
    	try{
    	List<List<String>> myList =  Arrays.asList(Arrays.asList("aaa=aaa","aaa=aaa"),
                                                  Arrays.asList("bbb=bbb","bbb=bbb"));
    	table.setTableContents(myList);
    	String output = tis.editTableContent(0,0,"aaa","111");
    	assertEquals("No changes made.", output);
    	}catch(IOException IOE){
			System.out.print(IOE);
		}
    }

    @Test 
    public void searchTableContents_1OccuranceOnKeyAndValue(){
    	List<List<String>> myList =  Arrays.asList(Arrays.asList("1aa=aaa","aaa=aaa"),
                                                  Arrays.asList("bbb=bbb","bbb=1bb"));
    	table.setTableContents(myList);
    	List<String> expected = Arrays.asList("Output: [0,0] - 1 Occurance in Key field\n","Output: [1,1] - 1 Occurance in Value field\n");

    	assertEquals(expected, tis.searchTableContents("1"));
    }

    @Test
    public void addTableContent_Successful(){
    	try{
    	tis.populateTableContents(2,2);

    	String output = tis.addTableContent(0, "AAA=BBB");
    	assertEquals("AAA=BBB", output);
    	}catch(IOException IOE){
			System.out.print(IOE);
		}

    }

   @Test
   public void sortTableContent_Ascending(){
   	try{
   	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      	System.setOut(new PrintStream(outContent));

        List<List<String>> myList =  Arrays.asList(Arrays.asList("aaa=bbb","aaa=aaa"),
                                                  Arrays.asList("bbb=bbb","bbb=ccc"));

        String expected = "aaa,aaa\taaa,bbb\t\nbbb,bbb\tbbb,ccc\t\n";
        table.setTableContents(myList);

        tis.sortTableContent("a");
        myList = table.getTableContents();
        tis.printTableContents(myList);

        assertEquals(expected, outContent.toString());
        }catch(IOException IOE){
			System.out.print(IOE);
		}
   }

   @Test
   public void sortTableContent_Descending(){
   	try{
   	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      	System.setOut(new PrintStream(outContent));

        List<List<String>> myList =  Arrays.asList(Arrays.asList("aaa=bbb","aaa=aaa"),
                                                  Arrays.asList("bbb=bbb","bbb=ccc"));

        String expected = "aaa,bbb\taaa,aaa\t\nbbb,ccc\tbbb,bbb\t\n";
        table.setTableContents(myList);

        tis.sortTableContent("d");
        myList = table.getTableContents();
        tis.printTableContents(myList);

        assertEquals(expected, outContent.toString());
        }catch(IOException IOE){
			System.out.print(IOE);
		}
   }

   @Test
   public void sortTableContent_Default(){
   	try{
   	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      	System.setOut(new PrintStream(outContent));

        List<List<String>> myList =  Arrays.asList(Arrays.asList("aaa=bbb","aaa=aaa"),
                                                  Arrays.asList("bbb=bbb","bbb=ccc"));

        String expected = "No changes made";
        table.setTableContents(myList);

        tis.sortTableContent("e");
        myList = table.getTableContents();
        

        assertEquals(expected, outContent.toString());
        }catch(IOException IOE){
			System.out.print(IOE);
		}
   }
       
	
}