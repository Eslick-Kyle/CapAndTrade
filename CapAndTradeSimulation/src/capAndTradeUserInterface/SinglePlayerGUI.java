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

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
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
     * This is an optional way to display the information, this might be easier
     * to format the way that we want as opposed to the display list. This could
     * allow us to get the trades information.
     */
    public void displayPowerStationsAndGetTradesBoxes() {
        
        
        // right now this hBox is not being used!!!!!!!!!!
        HBox titleInfo = new HBox();

        //titleInfo.setPadding(new Insets(15, 12, 15, 12));
        titleInfo.setSpacing(50);
        Label nameTitle = new Label();
        nameTitle.setText("Name");

        Label cleanRateTitle = new Label();
        cleanRateTitle.setText("Clean Rate");

        Label permitsTradedTitle = new Label();
        permitsTradedTitle.setText("Permits Traded");

        Label salePriceTitle = new Label();
        salePriceTitle.setText("Sale Price");

        Label marginalProfitTitle = new Label();
        marginalProfitTitle.setText("Marginal Profit");

        titleInfo.getChildren().add(nameTitle);
        //titleInfo.getChildren().add(cleanRateTitle);
        titleInfo.getChildren().add(permitsTradedTitle);
        titleInfo.getChildren().add(salePriceTitle);
        //titleInfo.getChildren().add(marginalProfitTitle);
        //border.setLeft(titleInfo);
        
        
        /* Sets the information with the text fields to account for all the 
            information. This also formats the area where the information will 
            be displayed and input can be gotten from the user.
        */
        ArrayList<Integer> prices = new ArrayList<>();
        ArrayList<TextField> permitsTraded = new ArrayList<>();
        prices.add(0, 0);
        for (int i = 1; i < 10; i++) {
            prices.add(Controller.getInstance().computerAskPrice(i));
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
                prices.add(tradePrice);
                if (tradePrice < 0) {
                    tradeOfferString += " offers to sell 25 permits for $"
                            + Integer.toString((-1)*tradePrice); 
                } else if (tradePrice > 0) {
                    tradeOfferString += " offers to buy 25 permits for $" + tradePrice;
                } 
                permitsToTradeLbl.setText(tradeOfferString);
                powerStationInfo.getChildren().add(permitsToTradeLbl);
                powerStationInfo.getChildren().add(acceptTradeButton());
            }
           
            //powerStationInfo.getChildren().add(name);
            
            //powerStationInfo.getChildren().add(cleanRate);
            //powerStationInfo.getChildren().add(marginalProfit);

            psInputBoxes.getChildren().add(powerStationInfo);
        }
        
        // puts the button in that will get the input for trade info
        Button submitTradeInfo = submitTradeInfo(prices, permitsTraded);
        psInputBoxes.getChildren().add(submitTradeInfo);
        psInputBoxes.getChildren().add(updateTradeInfoButton(prices, permitsTraded));
        border.setRight(psInputBoxes);
    }
    
    public Button acceptTradeButton() {
        Button acceptTradeBtn = new Button();
        acceptTradeBtn.setText("Accept");
        acceptTradeBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int maxTrades = Controller.getInstance().getPowerStations().get(0).getPermitsTraded();
                if (maxTrades > 100) {
                    System.out.println("You may not get more than 100 permits");
                } else {
                    acceptTradeBtn.setText("Accepted");
                    acceptTradeBtn.setDisable(true);
                     
                }
            }
        });
        
        return acceptTradeBtn;
    }

    /**
     * This sets up and controls the button that sill submit tradeInformation
     * @param prices - the text fields that get the price input
     * @param permitsTraded - the text fields that get the permitsTraded input
     * @return returns a button
     */
    public Button submitTradeInfo(List<Integer> prices, List<TextField> permitsTraded) {
        Button submitTradeInfoBtn = new Button();
        submitTradeInfoBtn.setText("Submit Trade Info");
        submitTradeInfoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Trade> trades = new ArrayList<>();
                for (int i = 0; i < prices.size(); i++) {
                    //trades.add(new Trade(permitsTraded.get(i).getText(), prices.get(i).getText()));
                    //prices.get(i).clear();
                    permitsTraded.get(i).clear();
                }
                Controller.getInstance().updateTradeInfo(trades);
                displaySingleplayerWindow();
            }
        });
        return submitTradeInfoBtn;
    }
    
        public Button updateTradeInfoButton(List<Integer> prices, List<TextField> permitsTraded) {
        Button updateTradeInfoBtn = new Button();
        updateTradeInfoBtn.setText("Update Trade Info");
        updateTradeInfoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Trade> trades = new ArrayList<>();
                for (int i = 0; i < prices.size(); i++) {
                    //trades.add(new Trade(permitsTraded.get(i).getText(), prices.get(i).));
                }
                //Controller.getInstance().updateTradeInfo(trades);
                updateListView(trades);
            }
        });
        return updateTradeInfoBtn;
    }
    
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
     * This creates an end of game button
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
     * This creates a return to menu button
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
