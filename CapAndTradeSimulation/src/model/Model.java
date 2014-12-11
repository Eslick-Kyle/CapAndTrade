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
    
    void hello() {
        System.out.println("hello");
    }
 
    /**
     * Gets the power stations from the simulation
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
     * @return a list of the power stations
     */
    public List<PowerStation> getPowerStationsInfo() {
        return (getPowerStations());
    }
    
    /**
     * This gets the total marginal profit from the simulation
     * @return list of integers containing total marginal profit
     */
    public List<Integer> getTotalMarginalProfit() {
        return simulation.getTotalMarginalProfit();
    }
    
    public List<Integer> getCurrentRoundMarginalProfit() {
        return simulation.getCurrentRoundMarginalProfit();
    }
    
    public void restartSimulation() {
        simulation.restartSimulation();
    }
    
    public int getNumRounds() {
        return simulation.getNumberOfRounds();
    }
    
    public List<List<Trade>> getTradeHistory() {
        return simulation.getTradeHistory();
    }
    
    public List<List<Integer>> marginalProfitHistory() {
        return simulation.getMarginalProfitHistory();
    }
        
    public void setSinglePlayerNames() {
        simulation.setSinglePlayerPowerStationNames();
    }
}
