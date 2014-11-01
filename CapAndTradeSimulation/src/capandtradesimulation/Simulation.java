/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capandtradesimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Benjamin
 */
public class Simulation {
    private List<PowerStation> powerStations;

    /**
     * Description getter for array list
     * @return this is a list of power stations
     */
    public List<PowerStation> getPowerStations() {
        return powerStations;
    }
    
    /**
     * Description: default constructor, automatically gives teams names
     */
    public Simulation () {
        powerStations = new ArrayList<>();
    }
    
    /**
     * Description: constructor takes number of Teams, sets names
     * to default values
     * @param numTeams 
     */
    public Simulation (int numTeams) {
        powerStations = new ArrayList<>();
        setPowerStationNamesDefault(numTeams);
    }
    
    /**
     * Description: names power stations with letters starting at 'a' until the
     * number of teams is reached
     * @param numberOfTeams 
     */
    public void setPowerStationNamesDefault(int numberOfTeams) {
        char name = 'a';
        for (int i = 0; i < numberOfTeams; i++) {
            PowerStation team = new PowerStation();
            team.setPowerStationName(String.valueOf(name));
            powerStations.add(team);
            name++;
        }
    }
    
    /**
     * Description: takes a list of names and sets the powerStations names to 
     * those values
     * @param powerStationNames - This is the names of the teams
     */
    public void setPowerStationNames (List<String> powerStationNames) {
        for (String name : powerStationNames) {
            PowerStation team = new PowerStation();
            team.setPowerStationName(name);
            powerStations.add(team);
        }
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
     * Description: This function will take a list of trades made by the users
     * and will assign that information to the PowerStations. The size of the 
     * array has to be the same size for this function to work.
     * 
     * @param trades - the trades each station made and the price they received
     */
    public void setPowerStationsTradeInformation(List<Trade> trades) {
        if (trades.size() != powerStations.size()) {
            System.out.println("ERROR: (setPowerStationsInformation) not enough information provided");
            System.exit(0);
        }
        
        // set the trade values in the power stations
        int i = 0;
        for (Trade theTrade : trades) {
            powerStations.get(i).setPermitsTraded(theTrade.getPermitsTraded());
            powerStations.get(i).setTradeIncome(theTrade.getPriceOfTrade());
            i++;
        }
    }
    
    /**
     * Description: This function will get the trade information for the number 
     * of teams from the console. The information can then be used to assign 
     * these values to the power stations.
     * 
     * @return - this returns a array list of the information
     */
    public List<Trade> getPowerStationsTradeInformationConsole() {
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
     */
    public void displayPowerStationsInfo() {
        for (PowerStation thePowerStation : powerStations) {
            System.out.println(thePowerStation.getPowerStationName() + "'s statistics:");
            System.out.println("\tPermits Traded: " + thePowerStation.getPermitsTraded() + " ");
            System.out.println("\tTrade Income: " + thePowerStation.getTradeIncome() + "\n");
        }
    }
}
