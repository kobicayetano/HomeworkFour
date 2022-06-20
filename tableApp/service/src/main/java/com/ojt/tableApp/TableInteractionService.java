package com.ojt.tableApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

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
	
 	public void printTableContents(List<List<String>> myList){
 		String output = "";
 		for (List<String> rowInTable : myList) {
            for (String data : rowInTable) {
            	output += StringUtils.replace(data,"=", ",") + "\t";    
            }
            output +="\n";
        }
        System.out.print(output);
 	}

 	public String editTableContent(int row, int col, String keyOrValue, String newValue){
        List<List<String>> myList = table.getTableContents();
        String[] indexData;
        indexData = StringUtils.split(myList.get(row).get(col), "=");
        String returnValue;
        if(StringUtils.equals("KEY", keyOrValue)){
            indexData[0] = newValue;
            myList.get(row).set(col, indexData[0] + "=" + indexData[1]);
            table.setTableContents(myList);
            returnValue = table.getTableContents().get(row).get(col);
        }else if(StringUtils.equals("VALUE", keyOrValue)){
            indexData[1] = newValue;
            myList.get(row).set(col, indexData[0] + "=" + indexData[1]);
            table.setTableContents(myList);
            returnValue = table.getTableContents().get(row).get(col);
        }else{
            return "No changes made.";
        }

        return returnValue;
    }

}