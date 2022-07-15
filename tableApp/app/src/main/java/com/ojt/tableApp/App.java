package com.ojt.tableApp;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.ojt.tableApp.InputUtil;
import java.io.IOException;
import java.io.FileNotFoundException;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.ArrayList;


public class App 
{
    
    Table table = new Table();
    TableInteractionService tic = new TableInteractionService();
    
    public void initialPrompt(){
        Scanner scanner = new Scanner(System.in);
        boolean tableLoaded = false;
        try{
           System.out.print("Enter file path to load: ");
           String path = scanner.next(); 
           tableLoaded = table.loadTableFromFile(path);

       }catch(FileNotFoundException FNFE){
            System.out.println("Error: " + FNFE);
            tableLoaded = false;
       } 
       if(!tableLoaded){
        initializeTable();
       }
    }

    public void callAddTableContent(){
        Scanner scanner = new Scanner(System.in);
        try{
        System.out.print("Row index: " );
        int row = scanner.nextInt();
        InputUtil.checkForLessThanZeroInput(row);
        System.out.print("New key: " );
        String key = scanner.next();
        System.out.print("New Value: " );
        String value = scanner.next();
        String keyAndValue = key+"="+value;
        tic.addTableContent(row, keyAndValue);
        }catch(InputMismatchException IME){
            System.out.println("Check input.");
        }catch(IOException IOE){
            System.out.println("Error: "+ IOE);
        }catch(IllegalArgumentException IAE){
            System.out.println("Error: "+ IAE);
        }
    }

    public void callSortTableContent(){
        Scanner scanner = new Scanner(System.in);
        try{
        System.out.print("A - Ascending / D - Descending: ");
        String orientation = scanner.next();
        tic.sortTableContent(orientation);
        }catch(IOException IOE){
            System.out.println("Error: "+ IOE);
        }
    }

    public void callSearchTableContent(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search for: ");
        String searchToken = scanner.next();
        List<String> output = tic.searchTableContents(searchToken);
        for(String s : output){
            System.out.println(s);
        }
    }

    public void callEditTableContent(){
        Scanner scanner = new Scanner(System.in);
        try{
            int row;
            int col;
            String keyOrValue;
            String newValue;
            System.out.print("Row index: " );
            row = scanner.nextInt();
            System.out.print("Column index: " );
            col = scanner.nextInt();          
            InputUtil.checkForLessThanZeroInput(row);
            InputUtil.checkForLessThanZeroInput(col);
            System.out.print("Key or Value: " );
            keyOrValue = StringUtils.upperCase(scanner.next());
            System.out.print("New value: " );
            newValue = scanner.next();
            tic.editTableContent(row, col, keyOrValue, newValue);
        }catch(InputMismatchException IME){
            System.out.println("Invalid input.");
        }catch(IOException IOE){
            System.out.println("Error:" + IOE);
        }catch(IndexOutOfBoundsException IOBE){
            System.out.println("Error:" + IOBE);
        }
        catch(IllegalArgumentException IAE){
            System.out.println("Error:" + IAE);
        }
    }

    public void initializeTable(){
        Scanner scanner = new Scanner(System.in);
        int row;
        int col;
        try{
        System.out.print("Row size:");
        row = scanner.nextInt();
        System.out.print("Column size:");
        col = scanner.nextInt();
        InputUtil.checkForZeroInput(row);
        InputUtil.checkForLessThanZeroInput(row);
        InputUtil.checkForZeroInput(col);
        InputUtil.checkForLessThanZeroInput(col);
        tic.printTableContents(tic.populateTableContents(row,col));
        }catch(InputMismatchException IME){
            System.out.println("Error: "+ IME);
            initializeTable();
        }catch(IOException IOE){
            initializeTable();
        }catch(IllegalArgumentException IAE){
            System.out.println("Error: "+ IAE);
            initializeTable();
        }
    }

    public void callPrintTableContent(){
        tic.printTableContents(table.getTableContents());
    }

    public static void main(String[] args){   
        Scanner inputScanner = new Scanner(System.in);
        App app = new App();
        app.initialPrompt();
        String action;
        while(true){
            System.out.println("\n");
            System.out.println("===================\n");
            System.out.println("a.) New Table");
            System.out.println("b.) Edit Table");
            System.out.println("c.) Search Table");
            System.out.println("d.) Print Table");
            System.out.println("e.) Sort Table");
            System.out.println("f.) Add Column");
            System.out.println("g.) Exit\n");
            System.out.print("Input: ");

            action = StringUtils.upperCase(inputScanner.next());
            switch(action){
                case"A":
                    app.initializeTable(); 
                    break;
                case"B":
                    app.callEditTableContent();
                    break;
                case"C":
                    app.callSearchTableContent();
                    break;
                case"D":
                    app.callPrintTableContent();
                    break;
                case"E":
                    app.callSortTableContent();
                    break;
                case"F":
                    app.callAddTableContent();
                    break;
                case"G":
                    System.out.println("Process terminated.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");    
            }
        } 
    }

}
