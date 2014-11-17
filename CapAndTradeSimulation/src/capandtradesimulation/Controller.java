/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capandtradesimulation;

import model.Model;
import capAndTradeUserInterface.CapAndTradeConsole;

/**
 *
 * @author Benjamin
 */
public class Controller extends Model {

    // Singleton pattern for the Controller
    private static final Controller instance = new Controller();

    /**
     * Default constructor just declares the new Simulation
     */
    private Controller() {
        //simulation = new Simulation();
    }

    /**
     * Singleton patter to call the one instance of the controller
     * @return returns the Controller object
     */
    public static Controller getInstance() {
        return instance;
    }

    /**
     * This will run the console application for the program, it will call the
     * necessary functions from the CapAndTradeConsole class and will set the
     * necessary information.
     */
    public void runConsoleApplication() {
        CapAndTradeConsole consoleApp = new CapAndTradeConsole();

        boolean isDefaultName = consoleApp.useDefaultNames();
        if (isDefaultName) {
            setPowerStationNamesDefault(consoleApp.getNumTeams());
        } else {
            updatePowerStationNames(consoleApp.getPowerStationNamesConsole(consoleApp.getNumTeams()));
        }
        consoleApp.displayPowerStationCleanRate(getPowerStations());
        for (int i = 1;; i++) {
            System.out.println("Round " + i);
            updateTradeInfo(consoleApp.getPowerStationsTradeInformationConsole(getPowerStations()));
            consoleApp.displayPowerStationsInfo(getPowerStations());
            if (consoleApp.promptToQuit()) {
                break;
            }
        }
        //show the winner
        consoleApp.displayWinner(getTotalMarginalProfit(), getPowerStations());
    }
}
