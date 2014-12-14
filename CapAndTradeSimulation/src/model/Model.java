/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Kyle
 */
public class Model {
    Simulation simulation;
    
    /**
     * Default constructor, creates a new simulation
     */
    public Model() {
        simulation = new Simulation();
    }
 
    /**
     * Gets the power stations from the simulation
     * 
     * @return list of PowerStation
     */
    public List<PowerStation> getPowerStations() {
        return simulation.getPowerStations();  
    }
    
    /**
     * calls the simulation function for setting the non default power station 
     * names
     * 
     * @param thePowerStations list of names, also bases the number of teams
     * based on the number of names
     */
    public void setPowerStationNames(List<String> thePowerStations) {
        simulation.setPowerStationNames(thePowerStations);
    }
    
    /**
     * calls the simulation function to set the power stations to default names
     * 
     * @param numberOfTeams the number of teams to be created
     */
    public void setPowerStationNamesDefault(int numberOfTeams) {
        simulation.setPowerStationNamesDefault(numberOfTeams);
    }
    
    /**
     * This updates the power stations names
     * 
     * @param names - the names of the power stations
     */
    public void updatePowerStationNames(List<String> names) {
        simulation.setPowerStationNames(names);    
    }
    
    /**
     * Will update trade information in the Simulation data
     * 
     * @param trades - the information that will update the data 
     */
    public void updateTradeInfo(List<Trade> trades) {
        simulation.setPowerStationsTradeInformation(trades);
    } 
    
    /**
     * A getter for the list of power stations from the Simulation class
     * 
     * @return a list of the power stations
     */
    public List<PowerStation> getPowerStationsInfo() {
        return (getPowerStations());
    }
    
    /**
     * This gets the total marginal profit from the simulation
     * 
     * @return list of integers containing total marginal profit
     */
    public List<Integer> getTotalMarginalProfit() {
        return simulation.getTotalMarginalProfit();
    }
    
    /**
     * Gets the current rounds marginal profit
     * 
     * @return returns list with all teams marginal profit
     */
    public List<Integer> getCurrentRoundMarginalProfit() {
        return simulation.getCurrentRoundMarginalProfit();
    }
    
    /**
     * This restarts the simulation, or sets everything to its default state
     */
    public void restartSimulation() {
        simulation.restartSimulation();
    }
    
    /**
     * This is the number of rounds that the game has been played
     * 
     * @return 
     */
    public int getNumRounds() {
        return simulation.getNumberOfRounds();
    }
    
    /**
     * The history of the trades for all rounds
     * 
     * @return
     */
    public List<List<Trade>> getTradeHistory() {
        return simulation.getTradeHistory();
    }
    
    /**
     * The history of the marginal profit for all rounds
     * 
     * @return
     */
    public List<List<Integer>> marginalProfitHistory() {
        return simulation.getMarginalProfitHistory();
    }
        
    /**
     * Sets single player names
     */
    public void setSinglePlayerNames() {
        simulation.setSinglePlayerPowerStationNames();
    }
    
    /**
     * The price the computers will ask
     * 
     * @param psOne the number of the power station being offered
     * @param psTwo the number of the power station offering
     * @return the price being offered
     */
    public int getComputerAskPrice(int psOne, int psTwo) {
        return simulation.getComputerAskPrice(psOne, psTwo);
    }
}
