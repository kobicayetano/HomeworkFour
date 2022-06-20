package com.ojt.tableApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

class TableInteractionService{
	
	Table table = new Table();


	public List<List<String>> populateTableContents(int row, int col) throws IOException{
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
        table.saveTableToFile(table.getTableFileName(), myList);
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

 	public String editTableContent(int row, int col, String keyOrValue, String newValue) throws IOException{
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

        table.saveTableToFile(table.getTableFileName(), myList);
        return returnValue;
    }

    public List<String> searchTableContents(String searchToken){
        List<List<String>> myList = table.getTableContents();
        List<String> output = new ArrayList<>();
        int tableRow = myList.size();
        int tableColumn;
        int occuranceInKey;
        int fromIndexOfKey;
        int occuranceInValue;
        int fromIndexOfValue;

        for (int i = 0; i < tableRow; i++) {
            tableColumn = myList.get(i).size();
            for (int j = 0; j < tableColumn; j++) {

                String currentData = myList.get(i).get(j);
                String[] stringToSearch = StringUtils.split(currentData,"=");//result.split("=");
                occuranceInKey = 0;
                fromIndexOfKey = 0;
                occuranceInValue = 0;
                fromIndexOfValue = 0;
                //return -1 if value cannot  be found (Loop stop mechanism)
                //search in KEY
                while ((fromIndexOfKey = StringUtils.indexOf(stringToSearch[0],searchToken, fromIndexOfKey)) != -1) {
                    occuranceInKey++;
                    fromIndexOfKey++;
                }
                if (occuranceInKey > 0) {
                   output.add(String.format("Output: [%d,%d] - %d Occurance in Key field\n", i, j, occuranceInKey));
                }
                //return -1 if value cannot  be found (Loop stop mechanism)
                //search in VALUE
                while ((fromIndexOfValue = StringUtils.indexOf(stringToSearch[1],searchToken, fromIndexOfValue)) != -1) {
                    occuranceInValue++;
                    fromIndexOfValue++;
                }
                if (occuranceInValue > 0) {
                    output.add(String.format("Output: [%d,%d] - %d Occurance in Value field\n", i, j, occuranceInValue));
                }
            }
        }
        for(String s : output){
            System.out.println(s);
        }
        return output;
    }

    public String addTableContent(int row, String keyAndValue) throws IOException{
    	
    	List<List<String>> myList = table.getTableContents();
    	myList.get(row).add(keyAndValue);
    	int lastIndex = myList.get(row).size()-1;
    	table.setTableContents(myList);
    	table.saveTableToFile(table.getTableFileName(), myList);

    	return myList.get(row).get(lastIndex);
    }	


}