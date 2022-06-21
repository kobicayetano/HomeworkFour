package com.ojt.tableApp;

import static org.junit.Assert.*;
import java.lang.IllegalArgumentException;
import org.junit.Test;
import com.ojt.tableApp.InputUtil;
/**
 * Unit test for simple App.
 */
public class InputUtilTest 
{
    
    @Test (expected = IllegalArgumentException.class)
    public void zeroInputThrowsException() throws IllegalArgumentException
    {
        InputUtil.checkForZeroInput(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void lessThanZeroInputThrowsException() throws IllegalArgumentException
    {
        InputUtil.checkForLessThanZeroInput(0);
    }
}
