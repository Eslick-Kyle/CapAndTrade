/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capandtradesimulation;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Benjamin
 */
public class TradeNGTest {
    
    public TradeNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }


    /**
     * Test of testDefaultConstructor method, of class Trade.
     */
    @Test
    public void testDefaultConstructor() {
        Trade instance = new Trade();
        // Test permitsTraded
        int expResult = 0;
        int result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 0;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
    }
    
    /**
     * Test of StringConstructor method, of class Trade
     */
    @Test
    public void testStringConstructor() {
        //Test Trade if empty strings are passed in
        Trade instance = new Trade("","");
        int expResult = 0;
        int result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 0;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
        //test if one number is empty and other is not
        instance = new Trade("1","");
        expResult = 1;
        result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 0;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
        //test if getpermits is empty and price of Trade is a number
        instance = new Trade("","1");
        expResult = 0;
        result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 1;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
        // test if both number are a number
        instance = new Trade("1","1");
        expResult = 1;
        result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 1;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
        // test if one number has a character
        instance = new Trade("a","1");
        expResult = 0;
        result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 1;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
        // test for if other number has a character
        instance = new Trade("1","a");
        expResult = 1;
        result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 0;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
        // test for if both number are characters
        instance = new Trade("a","a");
        expResult = 0;
        result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 0;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
        // test for if one number has a word
        instance = new Trade("Ben","1");
        expResult = 0;
        result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 1;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
        // test for if other number has a word
        instance = new Trade("1","Ben");
        expResult = 1;
        result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 0;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
        // test for if both have a word
        instance = new Trade("Ben","Ben");
        expResult = 0;
        result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 0;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
        // test for if characters are both letters and words
        instance = new Trade("1a3w","a1s3");
        expResult = 0;
        result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 0;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
    }        
    
    
    /**
     * Test of integer Constructor method, of class Trade
     */
    @Test 
    public void testIntConstructor() {
        Trade instance = new Trade(1,1);
        int expResult = 1;
        int result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 1;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
        instance = new Trade(0,1);
        expResult = 0;
        result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 1;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
        instance = new Trade(1,0);
        expResult = 1;
        result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 0;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
        
        instance = new Trade(0,0);
        expResult = 0;
        result = instance.getPermitsTraded();
        assertEquals(result, expResult);
        // test priceOfTrade
        expResult = 0;
        result = instance.getPriceOfTrade();
        assertEquals(result, expResult);
    }
          
}
