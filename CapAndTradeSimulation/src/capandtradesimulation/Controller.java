/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capandtradesimulation;

import model.Model;
import capAndTradeUserInterface.CapAndTradeConsole;
import capAndTradeUserInterface.CapAndTradeGUI;
import capAndTradeUserInterface.MultiPlayerGUI;
import capAndTradeUserInterface.SinglePlayerGUI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class Controller extends Model {
    private Stage primaryStage;


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
    
    public void runGUIApplication() {

    }
    
    public void selectGameScene(String view) {
        if (view == "single player") {
            SinglePlayerGUI singlePlayerView = new SinglePlayerGUI();
            singlePlayerView.start(primaryStage);

        } else if (view == "multi player") {
            MultiPlayerGUI multiPlayerView = new MultiPlayerGUI();
            multiPlayerView.start(primaryStage);
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
}
