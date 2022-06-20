package com.ojt.tableApp;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.ArrayList;


public class App 
{
    
    static Table table = new Table();
    static TableInteractionService tic = new TableInteractionService();
    static Scanner scanner = new Scanner(System.in);

    public static void initialPrompt(){
        boolean tableLoaded = false;
        try{
           System.out.print("Enter file path to load: ");
           String path = scanner.next(); 
           tableLoaded = table.loadTableFromFile(path);

       }catch(FileNotFoundException FNFE){
            tableLoaded = false;
       } 

       if(!tableLoaded){
        initializeTable();
       }
    }

    public static void menu(){
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

        String action = StringUtils.upperCase(scanner.next());
        switch(action){
            case"A":
                initializeTable();
                menu();
                break;
            case"B":
                callEditTableContent();
                menu();
            case"C":
                callSearchTableContent();
                menu();
            case"D":
                tic.printTableContents(table.getTableContents());
                menu();
            case"E":
                callSortTableContent();
                menu();
            case"F":
                callAddTableContent();
                menu();
            case"G":
                System.out.println("Process terminated.");
                System.exit(0);
            default:
                menu();    
        }
    }

    public static void callAddTableContent(){
        try{
        System.out.print("Enter which row to add column, key, value: ");
        int row = scanner.nextInt();
        String key = scanner.next();
        String value = scanner.next();
        String keyAndValue = key+"="+value;
        tic.addTableContent(row, keyAndValue);
        }catch(InputMismatchException IME){
            System.out.print("Check input.");
        }catch(IOException IOE){
            System.out.print("Error: "+ IOE);
        }
    }

    public static void callSortTableContent(){
        try{
        System.out.print("A - Ascending / D - Descending : ");
        String orientation = scanner.next();
        tic.sortTableContent(orientation);
        }catch(IOException IOE){
            System.out.print("Error: "+ IOE);
        }
    }

    public static void callSearchTableContent(){
        System.out.print("Search for: ");
        String searchToken = scanner.next();
        List<String> output = tic.searchTableContents(searchToken);
        for(String s : output){
            System.out.println(s);
        }
    }

    public static void callEditTableContent(){
        try{
            int row;
            int col;
            String keyOrValue;
            String newValue;
            System.out.print("Enter row, column, Key or Value, new value: ");
            //add checks for zeroInput and lessThanZeroInput
            row = scanner.nextInt();
            col = scanner.nextInt();
            keyOrValue = StringUtils.upperCase(scanner.next());
            newValue = StringUtils.upperCase(scanner.next());
            tic.editTableContent(row, col, keyOrValue, newValue);
        }catch(InputMismatchException IME){
            initializeTable();
        }catch(IOException IOE){
            initializeTable();
        }
    }


    public static void initializeTable(){
        try{
        int row;
        int col;
        System.out.print("Input row and column size: ");
        row = scanner.nextInt();
        col = scanner.nextInt();
        //add checks for zeroInput and lessThanZeroInput
        tic.printTableContents(tic.populateTableContents(row,col));
        }catch(InputMismatchException IME){
            initializeTable();
        }catch(IOException IOE){
            initializeTable();
        }

    }

    public static void main(String[] args)
    {   
        initialPrompt();
        menu();
        
    }
}
