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
 * Simulation class holds all the main methods to interact with the data in the
 * model. It also contains the data to run multiple rounds of the simulation
 *
 * @author Benjamin
 */
public class Simulation {

    private List<List<Trade>> tradeHistory = new LinkedList<>();
    private List<PowerStation> powerStations;
    private List<List<Integer>> marginalProfitHistory = new LinkedList<>();
    private int numberOfRounds;

    /**
     * Description: default constructor, automatically gives teams names
     */
    public Simulation() {
        powerStations = new ArrayList<>();
        numberOfRounds = 0;
    }

    /**
     * Description: constructor takes number of Teams, sets names to default
     * values
     *
     * @param numTeams number of teams
     */
    public Simulation(int numTeams) {
        powerStations = new ArrayList<>();
        setPowerStationNamesDefault(numTeams);
    }

    /**
     * Description getter for array list
     *
     * @return this is a list of power stations
     */
    public List<PowerStation> getPowerStations() {
        return powerStations;
    }

    /**
     * The number of rounds played in the simulation
     *
     * @return number of rounds
     */
    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public List<List<Trade>> getTradeHistory() {
        return tradeHistory;
    }

    /**
     * Description: names power stations with letters starting at 'a' until the
     * number of teams is reached
     *
     * @param numberOfTeams
     */
    public void setPowerStationNamesDefault(int numberOfTeams) {
        List<Integer> margeProfit = new ArrayList<>();
        char name = 'A';
        for (int i = 0; i < numberOfTeams; i++) {
            PowerStation team = new PowerStation();
            team.setPowerStationName(String.valueOf(name));
            powerStations.add(team);
            name++;
            margeProfit.add(0);
        }
        marginalProfitHistory.add(margeProfit);
    }
/**
     * Description: Sets user's name to player and adds names for computers
     * names computer power stations with letters starting at 'a' 
     *
     * 
     */
    public void setSinglePlayerPowerStationNames() {
        PowerStation team = new PowerStation();
        team.setPowerStationName("Player");
        powerStations.add(team);
        List<Integer> margeProfit = new ArrayList<>();
        char name = 'A';
        margeProfit.add(0);
        for (int i = 0; i < 10; i++) {
            team = new PowerStation();
            team.setPowerStationName("Computer " + String.valueOf(name));
            powerStations.add(team);
            name++;
            margeProfit.add(0);
        }
        marginalProfitHistory.add(margeProfit);
    }

    /**
     * Description: takes a list of names and sets the powerStations names to
     * those values
     *
     * @param powerStationNames - This is the names of the teams
     */
    public void setPowerStationNames(List<String> powerStationNames) {
        List<Integer> margeProfit = new ArrayList<>();
        for (String name : powerStationNames) {
            PowerStation team = new PowerStation();
            team.setPowerStationName(name);
            powerStations.add(team);
            margeProfit.add(0);
        }
        marginalProfitHistory.add(margeProfit);
    }

    /**
     * This returns a list of the total marginal profit that was found over the
     * course of multiple rounds.
     *
     * @return list of Integers with the total marginal profit
     */
    public List<Integer> getTotalMarginalProfit() {
        /* This section is to format the total marginal profit to be the same
         size as the number of teams playing */
        List<Integer> totalMarginalProfit = new LinkedList<>();
        for (List<Integer> mp : marginalProfitHistory) {
            for (int i = 0; i < mp.size(); i++) {
                totalMarginalProfit.add(0);
            }
            break;
        }

        // Here we will find the total marginal profit for all rounds
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
     * Returns the last rounds marginal profit
     *
     * @return
     */
    public List<Integer> getCurrentRoundMarginalProfit() {
        return marginalProfitHistory.get(marginalProfitHistory.size() - 1);
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
            powerStations.get(i).calcCleanRate();
            i++;
        }
        numberOfRounds++;
        marginalProfitHistory.add(marginalProfit);
    }

    public void restartSimulation() {
        tradeHistory = new LinkedList<>();
        powerStations = new ArrayList<>();
        marginalProfitHistory = new LinkedList<>();
        numberOfRounds = 0;
    }

    public List<List<Integer>> getMarginalProfitHistory() {
        return marginalProfitHistory;
    }

    public void setMarginalProfitHistory(List<List<Integer>> marginalProfitHistory) {
        this.marginalProfitHistory = marginalProfitHistory;
    }
}
