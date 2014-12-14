/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capAndTradeUserInterface;

import capandtradesimulation.Controller;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PowerStation;
import model.Trade;

/**
 *
 * @author Benjamin
 */
public class MultiPlayerGUI extends Application {

    private Scene multiPlayerScene;    
    private Stage primaryStage;  
    private VBox root;
    private BorderPane border;
    private ObservableList<String> displayList;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        displayMultiplayerWindow();       
    }
    
    
    /**
     * This function displays the basic window, this will be used anytime that 
     * the GUI needs to be updated
     */
    public void displayMultiplayerWindow() {
        ListView displayTeamsArea = new ListView();
        displayList = displayPowerStationsInfo();

        border = new BorderPane(); //main display grid
        border.setPadding(new Insets(10, 10, 10, 10));

        displayTeamsArea.setItems(displayList);

        /* This is very basic information, the VBox will need to be replaced, 
         this was mostly to show what needed to be done */
        Label welcome = new Label();
        welcome.setText("Welcome to the Multiplayer");

        root = new VBox();
        
        //formats buttons into a horizontal position
        HBox endGameButtonsHBox = new HBox(5);
        endGameButtonsHBox.getChildren().add(returnToMenu());
        endGameButtonsHBox.getChildren().add(endGameButton());
        
        //sets the format for the multi player game
        border.setTop(welcome);
        border.setCenter(displayTeamsArea);
        border.setBottom(endGameButtonsHBox);
        displayPowerStationsAndGetTradesBoxes();

        //declares a new scene and puts it into the primary stage
        multiPlayerScene = new Scene(border, 700, 500);
        multiPlayerScene.getStylesheets().add("multiplayerStyle.css");
        primaryStage.setScene(multiPlayerScene);
        primaryStage.setFullScreen(true);
    }
    
    /**
     * This will update the list view to make it so that only marginal profit for the round is 
     * shown
     * 
     * @param trades
     */
    public void updateListView(List<Trade> trades) {
        displayList.clear();
        updatePowerStationsInfo(trades);
        
    }
    
    /**
     * This will display the section that has trade input boxes and will have basic team 
     * information.
     */
    public void displayPowerStationsAndGetTradesBoxes() {
        /*
        The header information, it will have the tiles of all the information to be used
        */
        HBox titleInfo = new HBox();

        titleInfo.setSpacing(50);
        Label nameTitle = new Label();
        nameTitle.setText("Name");

        Label permitsTradedTitle = new Label();
        permitsTradedTitle.setText("Permits Traded");

        Label salePriceTitle = new Label();
        salePriceTitle.setText("Sale Price");

        titleInfo.getChildren().add(nameTitle);
        titleInfo.getChildren().add(permitsTradedTitle);
        titleInfo.getChildren().add(salePriceTitle);
        
        
        /* Sets the information with the text fields to account for all the 
            information. This also formats the area where the information will 
            be displayed and input can be gotten from the user.
        */
        ArrayList<TextField> prices = new ArrayList<>();
        ArrayList<TextField> permitsTraded = new ArrayList<>();

        VBox psInputBoxes = new VBox(5);
        psInputBoxes.getChildren().add(titleInfo);
        for (PowerStation powerStation : Controller.getInstance().getPowerStations()) {
            //create and format the HBox
            HBox powerStationInfo = new HBox();
            powerStationInfo.setSpacing(10);
            powerStationInfo.setMinWidth(165);
            
            //name of the power station
            Label name = new Label();
            name.setText(powerStation.getPowerStationName());
            name.setMinWidth(100);

            //create first input field, number of trades
            TextField inputNumOfPermitsTraded = new TextField();
            permitsTraded.add(inputNumOfPermitsTraded);
            inputNumOfPermitsTraded.setMinWidth(70);
            inputNumOfPermitsTraded.setMaxWidth(165);

            //create second input field price of trades
            TextField inputPriceOfTrades = new TextField();
            prices.add(inputPriceOfTrades);
            inputPriceOfTrades.setMinWidth(70);
            inputPriceOfTrades.setMaxWidth(165);

            //adds the information to the HBox
            powerStationInfo.getChildren().add(name);
            powerStationInfo.getChildren().add(inputNumOfPermitsTraded);
            powerStationInfo.getChildren().add(inputPriceOfTrades);

            //puts the HBox into the VBox that contains all the information
            psInputBoxes.getChildren().add(powerStationInfo);
        }
        
        // puts the button in that will get the input for trade info
        Button submitTradeInfo = submitTradeInfo(prices, permitsTraded);
        psInputBoxes.getChildren().add(updateTradeInfoButton(prices, permitsTraded));
        psInputBoxes.getChildren().add(submitTradeInfo); 
        
        border.setRight(psInputBoxes);
    }

    /**
     * This sets up and controls the button that sill submit tradeInformation
     * 
     * @param prices - the text fields that get the price input
     * @param permitsTraded - the text fields that get the permitsTraded input
     * @return returns a button
     */
    public Button submitTradeInfo(List<TextField> prices, List<TextField> permitsTraded) {
        Button submitTradeInfoBtn = new Button();
        submitTradeInfoBtn.setText("Submit Trade Info");
        submitTradeInfoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Trade> trades = new ArrayList<>();
                for (int i = 0; i < prices.size(); i++) {
                    trades.add(new Trade(permitsTraded.get(i).getText(), prices.get(i).getText()));
                    prices.get(i).clear();
                    permitsTraded.get(i).clear();
                }
                Controller.getInstance().updateTradeInfo(trades);
                displayMultiplayerWindow();
            }
        });
        return submitTradeInfoBtn;
    }
    
    /**
     * Button that will update the trade information that has been entered in the input boxes
     * 
     * @param prices - list of prices of the trades
     * @param permitsTraded - list of the permits that are traded
     * @return returns the button
     */
    public Button updateTradeInfoButton(List<TextField> prices, List<TextField> permitsTraded) {
        Button updateTradeInfoBtn = new Button();
        updateTradeInfoBtn.setText("Update Trade Info");
        
        updateTradeInfoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                //creates a trades array that will be used to show the current round
                ArrayList<Trade> trades = new ArrayList<>();
                for (int i = 0; i < prices.size(); i++) {
                    trades.add(new Trade(permitsTraded.get(i).getText(), prices.get(i).getText()));
                }
          
                updateListView(trades);    /*calls the function which will update the list view
                                           and will update the view to show the results of the 
                                           //trades*/
            }
        });
        return updateTradeInfoBtn;
    }
    
    /**
     * updates the power stations information in an observable list so that it will display 
     * the list view that takes an observable list.
     * 
     * @param trades - the trades that have been made
     */
    public void updatePowerStationsInfo(List<Trade> trades) {
        displayList.clear();

        //Formats all the header information into a string
        String displayPStationInfo;
        displayPStationInfo = "Clean Rate\t\tEmissions\t\tEnergy Production\t\tPermits\t\tSales\n";
        displayList.add(displayPStationInfo);
        PowerStation basicInfo = Controller.getInstance().getPowerStations().get(0);
        displayPStationInfo = "      " + basicInfo.getCleanRate() + "\t  \t\t";
        displayPStationInfo += "     " + basicInfo.getEmissions() + "     \t\t";
        displayPStationInfo += "\t    " + basicInfo.getEnergyProd() + "\t     \t\t";
        displayPStationInfo += "   " + basicInfo.getPermits() + "   \t       ";
        displayPStationInfo += "" + basicInfo.calcSales() + "\n";
        displayList.add(displayPStationInfo);

        
        //Formats the changed information for all the power stations        
        String formatDisplay = "Name        CleanRate           Marginal Profit";
        displayList.add(formatDisplay);
       
        PowerStation safePowerStation;      
        int i = 0;
        for (PowerStation ps : Controller.getInstance().getPowerStations()) {
            //sets the power stations information
            safePowerStation = new PowerStation();
            safePowerStation.setCleanRate(ps.getCleanRate());
            safePowerStation.setTradeIncome(trades.get(i).getPriceOfTrade());
            safePowerStation.setPermitsTraded(trades.get(i).getPermitsTraded());
            
            //formats the string to output the information in the correct format
            formatDisplay = ps.getPowerStationName() + " \t\t\t"
                    + Integer.toString(ps.getCleanRate())
                    + " \t\t\t\t" + Integer.toString(safePowerStation.calcMarginalProfit());
           
            displayList.add(formatDisplay);     //add information to the displayList
            i++;
        }

    }  
        
    /**
     * This formats the information to be displayed in the observable list for when the informatin
     * is submitted
     *
     * @return a list to be displayed
     */
    public ObservableList displayPowerStationsInfo() {
        ObservableList<String> displayList = FXCollections.observableArrayList();

        //formats the string for all the head information
        String displayPStationInfo;
        displayPStationInfo = "Clean Rate\t\tEmissions\t\tEnergy Production\t\tPermits\t\tSales\n";
        displayList.add(displayPStationInfo);
        PowerStation basicInfo = Controller.getInstance().getPowerStations().get(0);
        displayPStationInfo = "      " + basicInfo.getCleanRate() + "\t  \t\t";
        displayPStationInfo += "     " + basicInfo.getEmissions() + "     \t\t";
        displayPStationInfo += "\t    " + basicInfo.getEnergyProd() + "\t     \t\t";
        displayPStationInfo += "   " + basicInfo.getPermits() + "   \t       ";
        displayPStationInfo += "" + basicInfo.calcSales() + "\n";
        displayList.add(displayPStationInfo);
        
        String formatDisplay = "Name        CleanRate           Marginal Profit";
        displayList.add(formatDisplay);
 
        //formats the string to correctly display the power stations changed information
        List<Integer> currentMargeProfit = Controller.getInstance().getCurrentRoundMarginalProfit();
        int i = 0;
        for (PowerStation ps : Controller.getInstance().getPowerStations()) {
            formatDisplay = ps.getPowerStationName() + " \t\t\t"
                    + Integer.toString(ps.getCleanRate())
                    + " \t\t\t\t" + Integer.toString(currentMargeProfit.get(i));
            displayList.add(formatDisplay);
            i++;
        }
        
        return displayList;
    }
    
    /**
     * This creates a button that ends the game and displays the results
     * 
     * @return the button to send info to end of game screen.
     */
    public Button endGameButton() {
        Button endGameBtn = new Button();
        endGameBtn.setText("End Simulation");
        endGameBtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Controller.getInstance().selectGameScene("results multiplayer");
            }
            
        });
        return endGameBtn;
    }
    
    /**
     * This creates a button that will return the user to the main menu
     * 
     * @return returns the button created
     */
    public Button returnToMenu () {
        Button menuBtn = new Button();
        menuBtn.setText("Main Menu");
        menuBtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Controller.getInstance().restartSimulation();
                Controller.getInstance().selectGameScene("main menu");
            }
            
        });
        return menuBtn;
    }
}
