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
import javafx.event.EventType;
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

        root = new VBox();

        //format buttons at the bottom
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

    public void updateListView(List<Trade> trades) {
        displayList.clear();
        updatePowerStationsInfo(trades);
    }

    /**
     * This adds a section that will display the trade information
     */
    public void displayPowerStationsAndGetTradesBoxes() {

        // right now this hBox is not being used!!!!!!!!!!
        HBox titleInfo = new HBox();

        //titleInfo.setPadding(new Insets(15, 12, 15, 12));
        //titleInfo.setSpacing(50);
        //Label nameTitle = new Label();
        //nameTitle.setText("Name");
        //Label cleanRateTitle = new Label();
        //cleanRateTitle.setText("Clean Rate");
        //Label permitsTradedTitle = new Label();
        //permitsTradedTitle.setText("Permits Traded");
        Label salePriceTitle = new Label();
        salePriceTitle.setText("Sale Price");

        Label marginalProfitTitle = new Label();
        marginalProfitTitle.setText("Marginal Profit");

        //titleInfo.getChildren().add(nameTitle);
        //titleInfo.getChildren().add(cleanRateTitle);
        //titleInfo.getChildren().add(permitsTradedTitle);
        //titleInfo.getChildren().add(salePriceTitle);
        //titleInfo.getChildren().add(marginalProfitTitle);
        //border.setLeft(titleInfo);

        /* Sets the information with the text fields to account for all the 
         information. This also formats the area where the information will 
         be displayed and input can be gotten from the user.
         */
        ArrayList<Integer> prices = new ArrayList<>();
        ArrayList<Button> acceptedTradeBtns = new ArrayList<>();

        acceptedTradeBtns.add(new Button());

        // gets the computer trade price offers
        prices.add(0, 0);
        for (int i = 1; i < 11; i++) {
            prices.add(Controller.getInstance().computerAskPrice(0, i));
        }

        VBox psInputBoxes = new VBox(5);
        psInputBoxes.getChildren().add(titleInfo);
        List<PowerStation> powerStations = Controller.getInstance().getPowerStations();
        for (int i = 1; i < powerStations.size(); i++) {
            HBox powerStationInfo = new HBox();
            powerStationInfo.setSpacing(10);
            powerStationInfo.setMinWidth(165);

            Label name = new Label();
            name.setText(powerStations.get(i).getPowerStationName());
            name.setMinWidth(100);

            Label permitsToTradeLbl = new Label();
            int tradePrice = prices.get(i);

            if (tradePrice != 0) {
                String tradeOfferString = powerStations.get(i).getPowerStationName();
                if (tradePrice < 0) {
                    tradeOfferString += " offers to buy 25 permits for $"
                            + Integer.toString((-1) * tradePrice);
                } else if (tradePrice > 0) {
                    tradeOfferString += " offers to sell 25 permits for $"
                            + tradePrice;
                }

                permitsToTradeLbl.setText(tradeOfferString);

                Button acceptTradeBtn = acceptTradeButton(prices, acceptedTradeBtns);
                acceptedTradeBtns.add(acceptTradeBtn);

                powerStationInfo.getChildren().add(permitsToTradeLbl);
                powerStationInfo.getChildren().add(acceptTradeBtn);
            } else {
                acceptedTradeBtns.add(new Button());
            }
            //powerStationInfo.getChildren().add(name);
            //powerStationInfo.getChildren().add(cleanRate);
            //powerStationInfo.getChildren().add(marginalProfit);
            psInputBoxes.getChildren().add(powerStationInfo);
        }

        // puts the button in that will get the input for trade info
        Button submitTradeInfo = submitTradeInfo();
        psInputBoxes.getChildren().add(submitTradeInfo);
        psInputBoxes.getChildren().add(updateTradeInfoButton(acceptedTradeBtns));
        border.setRight(psInputBoxes);
    }

    /**
     * This will create a button that checks if a trade is excepted or not
     *
     * @param prices
     * @param acceptedTradeBtns
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
        for (Button acceptedTradeBtn : acceptedTradeBtns) {
            acceptedTradeBtn.setDisable(true);
        }
        doComputerTrades();
        //only allows the function in this if to be called once
        if (!allTradeButtonsDisabled) {
            allTradeButtonsDisabled = true;
        }
    }
    
    /**
     * This will update the players information that they have selected from the
     * GUI
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
    public Button submitTradeInfo() {
        Button submitTradeInfoBtn = new Button();
        submitTradeInfoBtn.setText("Submit Trade Info");
        submitTradeInfoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Trade> makeTradeList = makeTradeList();
                Controller.getInstance().updateTradeInfo(makeTradeList);
                makeTradeList = null;
                numTrades = 0;
                displaySingleplayerWindow();
            }
        });
        return submitTradeInfoBtn;
    }

    /**
     * This updates the trade info to the GUI
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

        PowerStation safePowerStation;
        int i = 0;
        for (PowerStation ps : Controller.getInstance().getPowerStations()) {
            safePowerStation = new PowerStation();
            safePowerStation.setCleanRate(ps.getCleanRate());
            safePowerStation.setTradeIncome(trades.get(i).getPriceOfTrade());
            safePowerStation.setPermits(trades.get(i).getPermitsTraded());
            formatDisplay = ps.getPowerStationName() + " \t\t\t"
                    + Integer.toString(ps.getCleanRate())
                    + " \t\t\t\t" + Integer.toString(safePowerStation.calcMarginalProfit());
            displayList.add(formatDisplay);
            i++;
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

        List<Integer> totalMargeProfit = Controller.getInstance().getTotalMarginalProfit();
        int i = 0;
        for (PowerStation ps : Controller.getInstance().getPowerStations()) {
            formatDisplay = ps.getPowerStationName() + " \t\t\t"
                    + Integer.toString(ps.getCleanRate())
                    + " \t\t\t\t" + Integer.toString(totalMargeProfit.get(i));
            displayList.add(formatDisplay);
            i++;
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
     * the computers trade amungst themselves and store the information in each
     * of the power stations.
     */
    public void doComputerTrades() {
        int count = 1;
        for (PowerStation ps : Controller.getInstance().getPowerStations()) {
            if (!ps.getPowerStationName().equals("Player")) {
                for (int i = 1; i < 10; i++) {
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
                            //Controller.getInstance().getPowerStations().get(count).setPermitsTraded(Controller.getInstance().getPowerStations().get(count).getPermitsTraded() - 25);
                            //Controller.getInstance().getPowerStations().get(count).setTradeIncome(Controller.getInstance().getPowerStations().get(count).getTradeIncome() + trade);
                        } else if (trade < 0) { //negitive means the second station wants to buy
                            ps.setPermitsTraded(ps.getPermitsTraded() - 25);
                            ps.setTradeIncome(ps.getTradeIncome() - trade);
                            //Controller.getInstance().getPowerStations().get(count).setPermitsTraded(Controller.getInstance().getPowerStations().get(count).getPermitsTraded() + 25);
                            //Controller.getInstance().getPowerStations().get(count).setTradeIncome(Controller.getInstance().getPowerStations().get(count).getTradeIncome() + trade);
                        }
                    }
                }
                count++;
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
