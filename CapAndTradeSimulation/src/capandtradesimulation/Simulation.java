/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capandtradesimulation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class Simulation {
    private List<PowerStation> powerStations;
    
    /**
     * Description: default constructor, automatically gives teams names
     */
    public Simulation () {
        powerStations = new ArrayList<>();
        namePowerStationsDefault(10);
    }
    
    /**
     * Description: constructor takes number of Teams, sets names
     * to default values
     * @param numTeams 
     */
    public Simulation (int numTeams) {
        powerStations = new ArrayList<>();
        namePowerStationsDefault(numTeams);
    }
    
    /**
     * Description: names power stations with letters starting at 'a' until the
     * number of teams is reached
     * @param numberOfTeams 
     */
    public void namePowerStationsDefault(int numberOfTeams) {
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
     * @param powerStationNames 
     */
    public void namePowerStations (List<String> powerStationNames) {
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
     * @param numberOfTeams 
     */
    public void getPowerStationNamesConsole(int numberOfTeams) {
        List<String> teamNames = new ArrayList<>();
        for (int i = 0; i < numberOfTeams; i++) {
            System.out.print("Team " + Integer.toString(i + 1)+ ": ");
            String powerStationName = System.console().readLine();
            teamNames.add(powerStationName);
        }
        namePowerStations(teamNames);
    }
    
    /**
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
    
    
    public List<Trade> getPowerStationsTradeInformationConsole() {
        List<Trade> theTrades = new ArrayList<>();
            
        return theTrades;
    }
}
