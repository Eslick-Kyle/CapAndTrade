/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capandtradesimulation;

import capAndTradeUserInterface.CapAndTradeConsole;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class Controller extends Model{
    private Simulation simulation;
    
    private static final Controller instance = new Controller();
    /**
     * Default constructor just declares the new Simulation
     */
    private Controller() {
        simulation = new Simulation();
    }
    
    public static Controller getInstance() {
        return instance;
    }
    
    /**
     * A getter for the list of power stations from the Simulation class
     * @return a list of the power stations
     */
    public List<PowerStation> getPowerStationsInfo() {
        return (simulation.getPowerStations());
    }
    
    /**
     * This will run the console application for the program, it will call the 
     * necessary functions from the CapAndTradeConsole class and will set the
     * necessary information.
     */
    public void runConsoleApplication() {
        CapAndTradeConsole consoleApp = new CapAndTradeConsole();
        //much conflict much break need merge
        updatePowerStationNames(consoleApp.getPowerStationNamesConsole(3));
        updateTradeInfo(consoleApp.getPowerStationsTradeInformationConsole());
        consoleApp.displayPowerStationsInfo(simulation.getPowerStations());
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
}
