/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
public class SinglePlayerGUI extends Application {

    private Scene singleiPlayerScene;
    private Stage primaryStage;
    private VBox root;
    private BorderPane border;
    private ObservableList<String> displayList;
    private int numTrades;
    private boolean allTradeButtonsDisabled = false;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        numTrades = 0;

        Controller.getInstance().setSinglePlayerNames();
        displaySingleplayerWindow();
    }

    /**
     * This function displays the basic window, this will be used anytime that
     * the GUI needs to be updated
     */
    public void displaySingleplayerWindow() {
        ListView displayTeamsArea = new ListView();
        displayList = displayPowerStationsInfo();

        border = new BorderPane(); //main display grid
        border.setPadding(new Insets(10, 10, 10, 10));

        displayTeamsArea.setItems(displayList);

        /* This is very basic information, the VBox will need to be replaced, 
         this was mostly to show what needed to be done */
        Label welcome = new Label();
        welcome.setText("Welcome to the Multiplayer");

        //this is assigned a new value and will be used throughout the rest 
        //of the game
        root = new VBox();

        //format buttons to display at the bottom of the screen
        HBox endGameButtonsHBox = new HBox(5);
        endGameButtonsHBox.getChildren().add(returnToMenu());
        endGameButtonsHBox.getChildren().add(endGameButton());

        border.setTop(welcome);
        border.setCenter(displayTeamsArea);
        border.setBottom(endGameButtonsHBox);
        displayPowerStationsAndGetTradesBoxes();

        singleiPlayerScene = new Scene(border, 700, 500);
        singleiPlayerScene.getStylesheets().add("multiplayerStyle.css");
        primaryStage.setScene(singleiPlayerScene);
        primaryStage.setFullScreen(true);
    }

    /**
     * this updates the list view for the single player
     * 
     * @param trades - this is the trades that have been made both by the user and by 
     * the computers
     */
    public void updateListView(List<Trade> trades) {
        displayList.clear();
        updatePowerStationsInfo(trades);
    }

    /**
     * This adds a section that will display the trade information
     */
    public void displayPowerStationsAndGetTradesBoxes() {

        HBox titleInfo = new HBox();

        Label headerLabel = new Label();
        headerLabel.setText("Trade Offers (can only accept up to 4)");

        titleInfo.getChildren().add(headerLabel);
        titleInfo.setAlignment(Pos.CENTER);
        border.setLeft(titleInfo);

        /* Sets the information with the text fields to account for all the 
         information.
         */
        ArrayList<Integer> prices = new ArrayList<>();
        ArrayList<Button> acceptedTradeBtns = new ArrayList<>();

        acceptedTradeBtns.add(new Button());

        // gets the computer trade price offers
        prices.add(0, 0);
        for (int i = 1; i < 11; i++) {
            prices.add(Controller.getInstance().computerAskPrice(0, i));
        }

        //basic vbox that will store all the basic information
        VBox psInputBoxes = new VBox(5);
        psInputBoxes.getChildren().add(titleInfo);
        
        //formats the power station offers to the user and places the acceptance button 
        //into the Hbox
        List<PowerStation> powerStations = Controller.getInstance().getPowerStations();
        for (int i = 1; i < powerStations.size(); i++) {
            //creats the HBox that will store the information
            HBox powerStationInfo = new HBox();
            powerStationInfo.setSpacing(10);
            powerStationInfo.setMinWidth(165);

            //displays the offers from the computer and formats it correctly
            Label permitsToTradeLbl = new Label();
            permitsToTradeLbl.setMinWidth(446);
            permitsToTradeLbl.setMaxWidth(500);
            
            //gets the price and performs some checks on it
            int tradePrice = prices.get(i);
            if (tradePrice != 0) {                          //ensures the price is not zero
                String tradeOfferString = powerStations.get(i).getPowerStationName();
                if (tradePrice < 0) {               //if the price is negative it is an offer to buy
                    tradeOfferString += " offers to buy 25 permits for $"
                            + Integer.toString((-1) * tradePrice);
                } else if (tradePrice > 0) {      // if the price is positive it is an offer to sell
                    tradeOfferString += " offers to sell 25 permits for $"
                            + tradePrice;
                }

                permitsToTradeLbl.setText(tradeOfferString);  //adds the string to the label

                //gets the button and adds it to the list of buttons
                Button acceptTradeBtn = acceptTradeButton(prices, acceptedTradeBtns);
                acceptedTradeBtns.add(acceptTradeBtn);

                //adds the information to the HBox
                powerStationInfo.getChildren().add(permitsToTradeLbl);
                powerStationInfo.getChildren().add(acceptTradeBtn);
            } else {                                // adds an empty button to the list
                acceptedTradeBtns.add(new Button());
            }
            psInputBoxes.getChildren().add(powerStationInfo);  //add HBoxInfo to VBoxAllTeamsInfo
        }

        //format button acceptedTradeBtns and submitTradeInfo button
        HBox buttons = new HBox(5);
        Button submitTradeInfo = submitTradeInfo(acceptedTradeBtns);
        buttons.getChildren().add(submitTradeInfo);
        buttons.getChildren().add(updateTradeInfoButton(acceptedTradeBtns));
        
        //puts the buttons in that will get the input for trade info, 
        //and submitTradeInfo        
        psInputBoxes.getChildren().add(buttons);
        border.setRight(psInputBoxes);
    }

    /**
     * This will create a button that checks if a trade is accepted or not, this only allows the
     * user to make up to four trades
     *
     * @param prices - the prices of the trade
     * @param acceptedTradeBtns - buttons that have memory if they were disabled
     * @return - returns a button
     */
    public Button acceptTradeButton(List<Integer> prices, List<Button> acceptedTradeBtns) {

        Button acceptTradeBtn = new Button();
        acceptTradeBtn.setText("Accept");
        acceptTradeBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                numTrades++;
                acceptTradeBtn.setText("Accepted");
                acceptTradeBtn.setDisable(true);
                updatePowerStationsPlayer(prices, acceptedTradeBtns);
                
                // check if four trades have been made and disable buttons
                if (numTrades >= 4) {
                    disableAcceptTradeButtons(acceptedTradeBtns);
                }
            }
        });

        return acceptTradeBtn;
    }

    /**
     * This will disable all the trade info buttons
     *
     * @param acceptedTradeBtns
     */
    public void disableAcceptTradeButtons(List<Button> acceptedTradeBtns) {
        //loops through all buttons and disables them
        for (Button acceptedTradeBtn : acceptedTradeBtns) {
            acceptedTradeBtn.setDisable(true);
        }
        //only allows the function in this if statement to be called once
        if (!allTradeButtonsDisabled) {
            doComputerTrades();
            allTradeButtonsDisabled = true;
        }
    }

    /**
     * This will update the players information that they have selected from the
     * GUI
     *
     * @param prices - the prices that are available
     * @param acceptedTradeBtns - list of buttons, some which are disabled
     */
    public void updatePowerStationsPlayer(List<Integer> prices, List<Button> acceptedTradeBtns) {
        int playerPrice = 0;
        int playerPermits = 0;
        List<PowerStation> powerStations = Controller.getInstance().getPowerStations();
        //loops through all the prices to find the trades and prices to be updated
        for (int i = 0; i < prices.size(); i++) {
            //check if the button is disabled
            if (acceptedTradeBtns.get(i).isDisabled()) {
                if (prices.get(i) < 0) {  //if negative, player sells permits
                    playerPermits -= 25;
                    // add the prices to the individuals permits
                    playerPrice -= prices.get(i);
                } else {                  //else player buys the permits
                    playerPermits += 25;
                    // add the prices to the individuals permits
                    playerPrice -= prices.get(i);
                }

                // factor in price to the team that made the trade
                int teamPrice = prices.get(i);

                int teamPermits = 0;
                if (prices.get(i) > 0) { //if positive the company sells me permits
                    teamPermits -= 25;
                    // sets the power stations trade income
                    powerStations.get(i).setTradeIncome(teamPrice);
                } else {                 //else the company buys my permits
                    teamPermits += 25;
                    // sets the power stations trade income
                    powerStations.get(i).setTradeIncome(teamPrice);
                }
                // sets the power stations permits traded
                powerStations.get(i).setPermitsTraded(teamPermits);
            }
        }
        powerStations.get(0).setPermitsTraded(playerPermits); // sets my permits
        powerStations.get(0).setTradeIncome(playerPrice); // sets my total income
    }

    /**
     * This sets up and controls the button that sill submit tradeInformation
     *
     * @return returns a button
     */
    public Button submitTradeInfo(List<Button> acceptedTradeBtns) {
        Button submitTradeInfoBtn = new Button();
        submitTradeInfoBtn.setText("Submit Trade Info");
        submitTradeInfoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //updates the trades and disables all buttons
                disableAcceptTradeButtons(acceptedTradeBtns);
                Controller.getInstance().updateTradeInfo(makeTradeList());
                
                //resets the basic information so another round can be played
                allTradeButtonsDisabled = false;
                numTrades = 0;
                
                displaySingleplayerWindow();  //will display the view again with updated information
            }
        });
        return submitTradeInfoBtn;
    }

    /**
     * This updates the trade info to the GUI
     *
     * @param acceptedTradeBtns list of buttons
     * @return returns a button
     */
    public Button updateTradeInfoButton(List<Button> acceptedTradeBtns) {
        Button updateTradeInfoBtn = new Button();
        updateTradeInfoBtn.setText("Update Trade Info");
        
        updateTradeInfoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                disableAcceptTradeButtons(acceptedTradeBtns);
                updateListView(makeTradeList());
            }
        });
        return updateTradeInfoBtn;
    }

    /**
     * This updates the power station information
     *
     * @param trades
     */
    public void updatePowerStationsInfo(List<Trade> trades) {
        displayList.clear();

        //formats that string that will display the header inforamtion
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

        //formats the heading for all the teams
        String formatDisplay = "Name\t\t\tCleanRate\t\tMarginal Profit";
        displayList.add(formatDisplay);
  
        //formats they player team correctly
        PowerStation player = Controller.getInstance().getPowerStations().get(0);
        formatDisplay = player.getPowerStationName() + "       \t\t\t" + player.getCleanRate()
                + "\t\t\t\t" + player.calcMarginalProfit();
        displayList.add(formatDisplay);

        //formats the computer teams to make them display correctly
        List<PowerStation> ps = Controller.getInstance().getPowerStations();
        for (int i = 1; i < ps.size(); i++) {
            formatDisplay = ps.get(i).getPowerStationName() + " \t\t\t"
                    + Integer.toString(ps.get(i).getCleanRate())
                    + " \t\t\t\t" + Integer.toString(ps.get(i).calcMarginalProfit());
            displayList.add(formatDisplay);
        }
    }

    public void getTradeInfo() {

    }

    /**
     * This formats the information to be displayed in the observable list
     *
     * @return a list to be displayed
     */
    public ObservableList displayPowerStationsInfo() {
        ObservableList<String> displayList = FXCollections.observableArrayList();

        //formats the string to display the header information
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

        //formats the heading for all the teams
        String formatDisplay = "Name\t\t\tCleanRate\t\tMarginal Profit";
        displayList.add(formatDisplay);
        
        List<Integer> totalMarginalProfit = Controller.getInstance().getTotalMarginalProfit();
        //formats the player to conform to all the other teams when displayed
        PowerStation player = Controller.getInstance().getPowerStations().get(0);
        formatDisplay = player.getPowerStationName() + "       \t\t\t" + player.getCleanRate()
                + "\t\t\t\t" + totalMarginalProfit.get(0);
        displayList.add(formatDisplay);
        
        //formats the computer teams to make them display correctly
        List<PowerStation> ps = Controller.getInstance().getPowerStations();
        for (int i = 1; i < ps.size(); i++) {
            formatDisplay = ps.get(i).getPowerStationName() + " \t\t\t"
                    + Integer.toString(ps.get(i).getCleanRate())
                    + " \t\t\t\t" + Integer.toString(totalMarginalProfit.get(i));
            displayList.add(formatDisplay);
        }

        return displayList;
    }

    /**
     * This creates a button that ends the game
     *
     * @return the button to send info to end of game screen.
     */
    public Button endGameButton() {
        Button endGameBtn = new Button();
        endGameBtn.setText("End Simulation");
        endGameBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Controller.getInstance().selectGameScene("results multiplayer");
            }

        });
        return endGameBtn;
    }

    /**
     * This creates a button that returns to the main menu
     *
     * @return returns the button created
     */
    public Button returnToMenu() {
        Button menuBtn = new Button();
        menuBtn.setText("Main Menu");
        menuBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Controller.getInstance().restartSimulation();
                Controller.getInstance().selectGameScene("main menu");
            }

        });
        return menuBtn;
    }

    /**
     * the computers trade amongst themselves and store the information in each
     * of the power stations.
     */
    public void doComputerTrades() {
        int count = 1;
        for (PowerStation ps : Controller.getInstance().getPowerStations()) {
            if (!ps.getPowerStationName().equals("Player")) {
                for (int i = 1; i < 11; i++) {
                    if ((ps.getPermitsTraded() == 100 || ps.getPermitsTraded() == -100)) {
                        break;
                    }
                    if (count != i) {
                        if ((Controller.getInstance().getPowerStations().get(count).getPermitsTraded() == 100
                                || Controller.getInstance().getPowerStations().get(count).getPermitsTraded() == -100)) {
                            break;
                        }
                        int trade = Controller.getInstance().computerAskPrice(count, i);
                        if (trade > 0) {        //positive means the second station wants to sell
                            ps.setPermitsTraded(ps.getPermitsTraded() + 25);
                            ps.setTradeIncome(ps.getTradeIncome() - trade);
                        } else if (trade < 0) { //negitive means the second station wants to buy
                            ps.setPermitsTraded(ps.getPermitsTraded() - 25);
                            ps.setTradeIncome(ps.getTradeIncome() - trade);
                        }
                    }
                }
                count++;
            }
        }
        for (PowerStation ps : Controller.getInstance().getPowerStations()) {
            if ((ps.getPermitsTraded() > 100 || ps.getPermitsTraded() < -100)) {
                System.out.println("THERE IS A PROBLEM HERE!!!!!!!!!!!!!!");
            }
        }
    }

    /**
     * Makes a List of trades from the information stored in the power stations
     *
     * @return
     */
    public List<Trade> makeTradeList() {
        ArrayList<Trade> trades = new ArrayList<>();
        for (PowerStation ps : Controller.getInstance().getPowerStations()) {
            Trade tempTrade = new Trade();
            tempTrade.setPermitsTraded(ps.getPermitsTraded());
            tempTrade.setPriceOfTrade(ps.getTradeIncome());
            trades.add(tempTrade);
        }

        return trades;
    }
}
