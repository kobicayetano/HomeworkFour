package com.ojt.tableApp;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class TableInteractionServiceTest{
	
	Table table = new Table();
	TableInteractionService tis = new TableInteractionService();

	@Test
	public void PopulateTableContents_ShouldHaveSameValue(){

		List<List<String>> actual = tis.populateTableContents(4,5);
		assertEquals(table.getTableContents(), actual);

	}	

	@Test
	public void PopulateTableContents_ShouldHaveSize4And5(){

		assertEquals(table.getTableContents().size(), 4);
		assertEquals(table.getTableContents().get(0).size(), 5);
	}

}