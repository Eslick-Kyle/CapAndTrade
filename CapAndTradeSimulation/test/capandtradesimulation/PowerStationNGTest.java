/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Kyle
 */
public class PowerStationNGTest {
    
    public PowerStationNGTest() {
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
     * Test of calcExtraEmitions method, of class PowerStation.
     */
    @Test
    public void testCalcExtraEmitions() {
        System.out.println("calcExtraEmitions");
        PowerStation instance = new PowerStation();
        int expResult = 100;
        int result = instance.calcExtraEmitions();
        assertEquals(result, expResult);
    }

    /**
     * Test of calcCleanRate method, of class PowerStation.
     */
    @Test
    public void testCalcCleanRate() {
        System.out.println("calcCleanRate");
        PowerStation instance = new PowerStation();
        instance.calcCleanRate();
        assertNotEquals(instance.getCleanRate(), 0);
    }

    /**
     * Test of calcPermitUsed method, of class PowerStation.
     */
    @Test
    public void testCalcPermitUsed() {
        System.out.println("calcPermitUsed");
        PowerStation instance = new PowerStation();
        int expResult = 100;
        int result = instance.calcPermitUsed();
        assertEquals(result, expResult);
        
        instance.setPermitsTraded(-50);
        expResult = 50;
        result = instance.calcPermitUsed();
        assertEquals(result, expResult);
        
        instance.setPermitsTraded(-100);
        expResult = 0;
        result = instance.calcPermitUsed();
        assertEquals(result, expResult);
        
        instance.setPermitsTraded(100);
        expResult = 200;
        result = instance.calcPermitUsed();
        assertEquals(result, expResult);
        
        instance.setPermitsTraded(50);
        expResult = 150;
        result = instance.calcPermitUsed();
        assertEquals(result, expResult);
        
        instance.setPermitsTraded(300);
        expResult = 200;
        result = instance.calcPermitUsed();
        assertEquals(result, expResult);
    }

    /**
     * Test of calcCleanCost method, of class PowerStation.
     */
    @Test
    public void testCalcCleanCost() {
        System.out.println("calcCleanCost");
        PowerStation instance = new PowerStation();
        instance.setCleanRate(10);
        int expResult = 1000;
        int result = instance.calcCleanCost();
        assertEquals(result, expResult);
        
        instance.setCleanRate(20);
        expResult = 2000;
        result = instance.calcCleanCost();
        assertEquals(result, expResult);
        
        instance.setCleanRate(50);
        expResult = 5000;
        result = instance.calcCleanCost();
        assertEquals(result, expResult);
        
        instance.setCleanRate(60);
        expResult = 6000;
        result = instance.calcCleanCost();
        assertEquals(result, expResult);
    }

    /**
     * Test of calcSales method, of class PowerStation.
     */
    @Test
    public void testCalcSales() {
        System.out.println("calcSales");
        PowerStation instance = new PowerStation();
        int expResult = 10000;
        int result = instance.calcSales();
        assertEquals(result, expResult);
    }

    /**
     * Test of calcProfit method, of class PowerStation.
     */
    @Test
    public void testCalcProfit() {
        System.out.println("calcProfit");
        PowerStation instance = new PowerStation();
        instance.setCleanRate(10);
        int expResult = 9000;
        int result = instance.calcProfit();
        assertEquals(result, expResult);
        
        instance.setCleanRate(20);
        expResult = 8000;
        result = instance.calcProfit();
        assertEquals(result, expResult);
        
        instance.setCleanRate(50);
        expResult = 5000;
        result = instance.calcProfit();
        assertEquals(result, expResult);
        
        instance.setCleanRate(60);
        expResult = 4000;
        result = instance.calcProfit();
        assertEquals(result, expResult);
    }

    /**
     * Test of calcMarginalProfit method, of class PowerStation.
     */
    @Test
    public void testCalcMarginalProfit() {
        System.out.println("calcMarginalProfit");
        PowerStation instance = new PowerStation();
        int expResult = 0;
        int result = instance.calcMarginalProfit();
        assertEquals(result, expResult);
        instance.setCleanRate(10);
        instance.setPermitsTraded(-100);
        instance.setTradeIncome(2000);
        expResult = 1000;
        result = instance.calcMarginalProfit();
        assertEquals(result, expResult);
        
        instance.setCleanRate(20);
        instance.setPermitsTraded(-100);
        instance.setTradeIncome(2000);
        expResult = 0;
        result = instance.calcMarginalProfit();
        assertEquals(result, expResult);
        
        instance.setCleanRate(60);
        instance.setPermitsTraded(-100);
        instance.setTradeIncome(2000);
        expResult = -4000;
        result = instance.calcMarginalProfit();
        assertEquals(result, expResult);
        
        instance.setCleanRate(10);
        instance.setPermitsTraded(100);
        instance.setTradeIncome(-2000);
        expResult = -1000;
        result = instance.calcMarginalProfit();
        assertEquals(result, expResult);
        
        instance.setCleanRate(10);
        instance.setPermitsTraded(200);
        instance.setTradeIncome(-3000);
        expResult = -2000;
        result = instance.calcMarginalProfit();
        assertEquals(result, expResult);
        
    }

    /**
     * Test of getPowerStationName method, of class PowerStation.
     */
    @Test
    public void testGetPowerStationName() {
        System.out.println("getPowerStationName");
        PowerStation instance = new PowerStation();
        instance.setPowerStationName("kyle");
        String expResult = "kyle";
        String result = instance.getPowerStationName();
        assertEquals(result, expResult);
    }

}
