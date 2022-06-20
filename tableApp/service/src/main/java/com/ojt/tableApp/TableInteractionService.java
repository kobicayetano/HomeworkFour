package com.ojt.tableApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class TableInteractionService{
	
	Table table = new Table();


	public List<List<String>> populateTableContents(int row, int col){
		List<List<String>> myList = new ArrayList<>();
		String generatedKey = "";
        String generatedValue = "";
        final String characterPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+?/.><[]";
        Random random = new Random();

        for (int i = 0; i < row; i++) {
            ArrayList<String> rowInTable = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < 3; k++) {
                    generatedKey += characterPool.charAt(random.nextInt(characterPool.length()));
                    generatedValue += characterPool.charAt(random.nextInt(characterPool.length()));
                }
                rowInTable.add(generatedKey + "=" + generatedValue);
                generatedKey = "";
                generatedValue = "";
            }
            myList.add(rowInTable);
        }
        
        table.setTableContents(myList);
        return myList;
	}
	
	
}