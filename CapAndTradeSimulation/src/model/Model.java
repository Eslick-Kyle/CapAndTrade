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
    
    public Model() {
        simulation = new Simulation();
    }
    void hello() {
        System.out.println("hello");
    }
 
    public List<PowerStation> getPowerStations() {
        return simulation.getPowerStations();  
    }
    
    public void setPowerStationNames(List<String> thePowerStations) {
        simulation.setPowerStationNames(thePowerStations);
    }
    
    public void setPowerStationNamesDefault(int numberOfTeams) {
        simulation.setPowerStationNamesDefault(numberOfTeams);
    }
        /**
     * This updates the power stations names
     * @param names - the names of the power stations
     */
    public void updatePowerStationNames(List<String> names) {
        simulation.setPowerStationNames(names);    
    }
    
    /**
     * Will update trade information in the Simulation data
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
    
    public List<Integer> getTotalMarginalProfit() {
        return simulation.getTotalMarginalProfit();
    }
}
