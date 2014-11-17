/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
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
public class SimulationNGTest {
    
    public SimulationNGTest() {
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
     * Test of getPowerStations method, of class Simulation.
     */
    @Test
    public void testGetPowerStations() {

    }

    /**
     * Test of setPowerStationNamesDefault method, of class Simulation.
     */
    @Test
    public void testSetPowerStationNamesDefault() {
        System.out.println("setPowerStationNamesDefault");
        int numberOfTeams = 10;
        Simulation instance = new Simulation();
        instance.setPowerStationNamesDefault(numberOfTeams);
        List<String> resultExp = new ArrayList<>();
        char temp = 'A';
        for (int i = 0; i < numberOfTeams; i++) {
            resultExp.add(String.valueOf(temp));
            temp++;
        }
        List<String> actual = new ArrayList<>();
        for (PowerStation item : instance.getPowerStations()) {
            actual.add(item.getPowerStationName());
        }
        assertEquals(actual, resultExp);
        
    }

    /**
     * Test of setPowerStationNames method, of class Simulation.
     */
    @Test
    public void testSetPowerStationNames() {

    }

    /**
     * Test of getTotalMarginalProfit method, of class Simulation.
     */
    @Test
    public void testGetTotalMarginalProfit() {
        
        System.out.println("getTotalMarginalProfit Once through");
        Simulation instance = new Simulation();
        instance.setPowerStationNamesDefault(3);
        
        for (PowerStation ps : instance.getPowerStations()) {
            ps.setCleanRate(10);
        }
        List<Trade> theTrades = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            theTrades.add(new Trade(-100,2000));
        }
        instance.setPowerStationsTradeInformation(theTrades);
        
        List<Integer> expected = new ArrayList<>();
        for (PowerStation ps : instance.getPowerStations()) {
            expected.add(ps.calcMarginalProfit());
        }
        List<Integer> actual = instance.getTotalMarginalProfit();
        System.out.println("Size of Arrays: " + actual.size() + " " + expected.size());
        assertEquals(actual, expected);
        
        
        //Test for multiple times through the list
        System.out.println("getTotalMarginalProfit Once through");
        instance = new Simulation();
        instance.setPowerStationNamesDefault(3);
        
        for (PowerStation ps : instance.getPowerStations()) {
            ps.setCleanRate(10);
        }
        for (int i = 0; i < 10; i++) {
            theTrades = new ArrayList<>();
       
            for (int h = 0; h < 3; h++) {
                theTrades.add(new Trade(-100,2000));
            }
            instance.setPowerStationsTradeInformation(theTrades);
        
        }
        expected = new ArrayList<>();
        for (PowerStation ps : instance.getPowerStations()) {
            expected.add(10 * ps.calcMarginalProfit());
        }
        actual = instance.getTotalMarginalProfit();
        System.out.println("Size of Arrays: " + actual.size() + " " + expected.size());
        assertEquals(actual, expected);        
    }

    /**
     * Test of setPowerStationsTradeInformation method, of class Simulation.
     */
    @Test
    public void testSetPowerStationsTradeInformation() {
        System.out.println("setPowerStationsTradeInformation");

    }
    
}
