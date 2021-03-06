/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capandtradesimulation;

import model.Model;
import capAndTradeUserInterface.CapAndTradeConsole;
import capAndTradeUserInterface.CapAndTradeGUI;
import capAndTradeUserInterface.EndGameMultiplayerGUI;
import capAndTradeUserInterface.InfoSetupMultiPlayerGUI;
import capAndTradeUserInterface.MultiPlayerGUI;
import capAndTradeUserInterface.SinglePlayerGUI;
import capAndTradeUserInterface.TutorialGUI;
import java.util.List;
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
     * Singleton pattern to call the one instance of the controller
     * 
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

        //assigns the names information to the simulation
        boolean isDefaultName = consoleApp.useDefaultNames();
        if (isDefaultName) {            // if user specifies to use default names
            setPowerStationNamesDefault(consoleApp.getNumTeams());
        } else {                        //else user specified they want to add their own names
            updatePowerStationNames(consoleApp.getPowerStationNamesConsole(
                    consoleApp.getNumTeams()));
        }
        
        //gets the trade information from the user and updates it
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
    
    /**
     * This function determines what next action needs to be take with the game
     * This selects the game type that the user wants to be played.
     * 
     * @param view - string with the name of the view to be changed
     */
    public void selectGameScene(String view) {
        if (view == "single player") {
            SinglePlayerGUI singlePlayerView = new SinglePlayerGUI();
            singlePlayerView.start(primaryStage);    
        } else if (view == "multi player") {
            MultiPlayerGUI multiPlayerView = new MultiPlayerGUI();
            multiPlayerView.start(primaryStage);
        } else if (view == "tutorial") {
            TutorialGUI tutorial = new TutorialGUI();
            tutorial.start(primaryStage);
        } else if (view == "main menu") {
            CapAndTradeGUI menu = new CapAndTradeGUI();
            menu.start(primaryStage);
        } else if (view == "setup multiplayer") {
            InfoSetupMultiPlayerGUI setup = new InfoSetupMultiPlayerGUI();
            setup.start(primaryStage);
        } else if (view == "results multiplayer") {
            EndGameMultiplayerGUI endScreen = new EndGameMultiplayerGUI();
            endScreen.start(primaryStage);
        } else if (view == "quit") {
            primaryStage.close();
        }
    }

    /**
     * this gets the primary stage that is being used for this program
     * 
     * @return primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * sets the primary stage that is being used for this program
     * 
     * @param primaryStage 
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * this will get the marginal profit history for the user.
     * 
     * @return this returns the marginal profit history
     */
    public List<List<Integer>> getMarginalProfitHistory() {
        return marginalProfitHistory();
    }
    
    /**
     * this is the computer ask price, it references the model, which reference simulation
     * 
     * @param psOne - the first team that the offer it to.
     * @param psTwo - the team making the offer
     * @return - the price of the offer for 25 permits
     */
    public int computerAskPrice(int psOne, int psTwo) {
        return getComputerAskPrice(psOne, psTwo);
    }
        
}
