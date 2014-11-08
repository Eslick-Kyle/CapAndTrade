/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capAndTradeUserInterface;

import capandtradesimulation.Controller;
import capandtradesimulation.PowerStation;
import capandtradesimulation.Trade;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Benjamin
 */
public class CapAndTradeConsole {
    
    
    public CapAndTradeConsole() {
    }
    
     /**
     * Description: This is for the console version, it will prompt the user for
     * powerStation names. It will then initialize the power stations with those
     * names
     * @param numberOfTeams this is the number of teams to be created
     * @return  this will return the list of names
     */
    public List<String> getPowerStationNamesConsole(int numberOfTeams) {
        List<String> teamNames = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < numberOfTeams; i++) {
            System.out.print("Power Station Name " + Integer.toString(i + 1)+ ": ");
            String powerStationName = input.nextLine();
            teamNames.add(powerStationName);
        }
        return teamNames;
    }
    
        /**
     * Description: This function will get the trade information for the number 
     * of teams from the console. The information can then be used to assign 
     * these values to the power stations.
     * 
     * @param powerStations the power stations
     * @return - this returns a array list of the information
     */
    public List<Trade> getPowerStationsTradeInformationConsole(List<PowerStation> powerStations) {
        List<Trade> theTrades = new ArrayList<>();
        // get information from console
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < powerStations.size(); i++) {
            System.out.print(powerStations.get(i).getPowerStationName() + "'s Permits Traded: ");
            String numPermitsTraded = input.nextLine();
            System.out.print(powerStations.get(i).getPowerStationName() + "'s Permits Total Price: ");
            String permitsTotalPrice = input.nextLine();
            
            theTrades.add(new Trade(numPermitsTraded, permitsTotalPrice));
        }
        
        return theTrades;
    }
    
        
    /**
     * Description: This will display the information that is found in the power
     * Stations
     * @param powerStations the power stations info
     */
    public void displayPowerStationsInfo(List<PowerStation> powerStations) {
        
        for (PowerStation thePowerStation : powerStations) {
            System.out.println(thePowerStation.getPowerStationName() + "'s statistics:");
            System.out.println("\tPermits Traded: " + thePowerStation.getPermitsTraded() + " ");
            System.out.println("\tTrade Income: " + thePowerStation.getTradeIncome() + "\n");
        }
    }
}
