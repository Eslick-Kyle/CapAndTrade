/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class Simulation {
    private List<List<Trade>> tradeHistory = new LinkedList<>();
    private List<PowerStation> powerStations;
    private List<List<Integer>> marginalProfitHistory = new LinkedList<>();
    
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
     * Description getter for array list
     * @return this is a list of power stations
     */
    public List<PowerStation> getPowerStations() {
        return powerStations;
    }
    
    /**
     * Description: names power stations with letters starting at 'a' until the
     * number of teams is reached
     * @param numberOfTeams 
     */
    public void setPowerStationNamesDefault(int numberOfTeams) {
        char name = 'A';
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
    
    public List<Integer> getTotalMarginalProfit() {
        List<Integer> totalMarginalProfit = new LinkedList<>();
        for (List<Integer> mp : marginalProfitHistory) {
            totalMarginalProfit.add(0);  
        }
        for (List<Integer> mp : marginalProfitHistory) {
            int i = 0;
            for (Integer profit : mp) {
                int temp = totalMarginalProfit.get(i) + profit;
                totalMarginalProfit.set(i, temp);
            i++;
            }
                       
        }
        return totalMarginalProfit;
    }
    
    /**
     * Description: This function will take a list of trades made by the users
     * and will assign that information to the PowerStations. The size of the 
     * array has to be the same size for this function to work.
     * 
     * @param trades - the trades each station made and the price they received
     */
    public void setPowerStationsTradeInformation(List<Trade> trades) {
        tradeHistory.add(trades);
        if (trades.size() != powerStations.size()) {
            System.out.println("ERROR: (setPowerStationsInformation) not enough information provided");
            System.exit(0);
        }
        ArrayList<Integer> marginalProfit = new ArrayList<>();
        // set the trade values in the power stations
        int i = 0;
        for (Trade theTrade : trades) {
            powerStations.get(i).setPermitsTraded(theTrade.getPermitsTraded());
            powerStations.get(i).setTradeIncome(theTrade.getPriceOfTrade());
            marginalProfit.add(powerStations.get(i).calcMarginalProfit());
            i++;
        }
        marginalProfitHistory.add(marginalProfit);
    }
}