/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capandtradesimulation;

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
}
