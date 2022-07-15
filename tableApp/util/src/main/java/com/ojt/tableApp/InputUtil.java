package com.ojt.tableApp;
import java.lang.IllegalArgumentException;
/**
 * Hello world!
 *
 */
public class InputUtil 
{
    public static void checkForZeroInput(int number)
    {
        if(number==0){
            throw new IllegalArgumentException("Invalid input.");
        }
    }

    public static void checkForLessThanZeroInput( int number )
    {
        if(number<0){
            throw new IllegalArgumentException("Invalid input.");
        }
    }

}
