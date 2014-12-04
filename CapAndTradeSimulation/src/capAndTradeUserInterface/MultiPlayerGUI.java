/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capAndTradeUserInterface;

import capandtradesimulation.Controller;
import java.util.ArrayList;
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
    private Stage getTeamNamesStage;
    Stage primaryStage;
    VBox askInfo;
    VBox root;
    BorderPane border;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        ListView displayTeamsArea = new ListView();
        ObservableList<String> displayList = displayPowerStationsInfo();

        border = new BorderPane(); //main display grid

        displayTeamsArea.setItems(displayList);

        /* This is very basic information, the VBox will need to be replaced, 
         this was mostly to show what needed to be done */
        Label welcome = new Label();
        welcome.setText("Welcome to the Multiplayer");

        root = new VBox();

        border.setTop(welcome);
        border.setCenter(displayTeamsArea);
        displayPowerStationsAndGetTradesBoxes();

        multiPlayerScene = new Scene(border, 700, 500);
        primaryStage.setScene(multiPlayerScene);
    }

    /**
     * This is an optional way to display the information, this might be easier
     * to format the way that we want as opposed to the display list. This could
     * allow us to get the trades information.
     */
    public void displayPowerStationsAndGetTradesBoxes() {
        
        
        // right now this hBox is not being used!!!!!!!!!!
        HBox titleInfo = new HBox();

        titleInfo.setPadding(new Insets(15, 12, 15, 12));
        titleInfo.setSpacing(10);
        Label nameTitle = new Label();
        nameTitle.setText("Power Station Name");

        Label cleanRateTitle = new Label();
        cleanRateTitle.setText("Clean Rate");

        Label permitsTradedTitle = new Label();
        permitsTradedTitle.setText("Permits Traded");

        Label salePriceTitle = new Label();
        salePriceTitle.setText("Sale Price");

        Label marginalProfitTitle = new Label();
        marginalProfitTitle.setText("Marginal Profit");

        titleInfo.getChildren().add(nameTitle);
        titleInfo.getChildren().add(cleanRateTitle);
        titleInfo.getChildren().add(permitsTradedTitle);
        titleInfo.getChildren().add(salePriceTitle);
        titleInfo.getChildren().add(marginalProfitTitle);
        //border.setLeft(titleInfo);
        
        

        ArrayList<TextField> prices = new ArrayList<>();
        ArrayList<TextField> permitsTraded = new ArrayList<>();

        VBox psInputBoxes = new VBox();
        for (PowerStation powerStation : Controller.getInstance().getPowerStations()) {
            HBox powerStationInfo = new HBox();
            Label name = new Label();
            name.setText(powerStation.getPowerStationName());

            Label cleanRate = new Label();
            cleanRate.setText(Integer.toString(powerStation.getCleanRate()));

            TextField inputNumOfPermitsTraded = new TextField();
            permitsTraded.add(inputNumOfPermitsTraded);

            TextField inputPriceOfTrades = new TextField();
            prices.add(inputPriceOfTrades);

            Label marginalProfit = new Label();
            marginalProfit.setText(Integer.toString(powerStation.calcMarginalProfit()));

            powerStationInfo.getChildren().add(name);
            powerStationInfo.getChildren().add(cleanRate);
            powerStationInfo.getChildren().add(inputNumOfPermitsTraded);
            powerStationInfo.getChildren().add(inputPriceOfTrades);
            powerStationInfo.getChildren().add(marginalProfit);

            psInputBoxes.getChildren().add(powerStationInfo);
        }

        Button submitTradeInfoBtn = new Button();
        submitTradeInfoBtn.setText("Submit Trade Info");
        submitTradeInfoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Trade> trades = new ArrayList<>();
                for (int i = 0; i < prices.size(); i++) {
                    trades.add(new Trade(prices.get(i).getText(), permitsTraded.get(i).getText()));
                    prices.get(i).clear();
                    permitsTraded.get(i).clear();
                }
                Controller.getInstance().updateTradeInfo(trades);
            }
        });
        psInputBoxes.getChildren().add(submitTradeInfoBtn);
        border.setBottom(psInputBoxes);
    }

    /**
     * This formats the information to be displayed in the observable list
     *
     * @return a list to be displayed
     */
    public ObservableList displayPowerStationsInfo() {
        ObservableList<String> displayList = FXCollections.observableArrayList();

        String formatDisplay = "Name  CleanRate  Marginal Profit";
        displayList.add(formatDisplay);
        for (PowerStation ps : Controller.getInstance().getPowerStations()) {
            formatDisplay = ps.getPowerStationName() + "\t\t"
                    + Integer.toString(ps.getCleanRate())
                    + "\t\t" + Integer.toString(ps.calcMarginalProfit());
            displayList.add(formatDisplay);
        }

        return displayList;
    }

    /**
     * This function creates a new stage to get the number of teams and then
     * send the info to name those teams the default names.
     */
    public void getNumberOfTeams() {
        TextField numberInputField = new TextField();

        Button defaultNamesBtn = new Button();
        defaultNamesBtn.setText("Default Names");
        defaultNamesBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String numTeams = numberInputField.getText();
                if (numTeams.equals("") || numTeams.equals("0")) {
                    numTeams = "1";
                }
                Controller.getInstance().setPowerStationNamesDefault(Integer.parseInt(numTeams));
                start(Controller.getInstance().getPrimaryStage());
                getTeamNamesStage.close();
            }
        });

        Button customNamesBtn = new Button();
        customNamesBtn.setText("Non Default Names");
        customNamesBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String numTeams = numberInputField.getText();
                if (numTeams.equals("") || numTeams.equals("0")) {
                    numTeams = "1";
                }
                getCustomPowerStationNames(Integer.parseInt(numTeams));

            }
        });
        /* The container to contain the information, This will most likely need 
         to be changed to something else */
        askInfo = new VBox();
        askInfo.getChildren().add(numberInputField);
        askInfo.getChildren().add(defaultNamesBtn);
        askInfo.getChildren().add(customNamesBtn);

        Scene getTeamInfoScene = new Scene(askInfo, 300, 250);

        // Creates the secondary stage or the info window
        getTeamNamesStage = new Stage();
        getTeamNamesStage.setTitle("Power Station Information");
        getTeamNamesStage.setScene(getTeamInfoScene);
        getTeamNamesStage.show();
        getTeamNamesStage.toFront();
    }

    /**
     * This changes the stage to get the power stations information
     *
     * @param numTeams - this is the number of teams
     */
    public void getCustomPowerStationNames(int numTeams) {

        Label customNamesLabel = new Label();
        customNamesLabel.setText("Enter Power Station Names:");
        askInfo = new VBox();
        askInfo.getChildren().add(customNamesLabel);

        ArrayList<TextField> inputFields = new ArrayList<>();
        for (int i = 0; i < numTeams; i++) {
            TextField inputName = new TextField();
            inputFields.add(inputName);
            askInfo.getChildren().add(inputName);
        }

        Button submitCostomNamesBtn = new Button();
        submitCostomNamesBtn.setText("Submit Names");
        submitCostomNamesBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ArrayList<String> names = new ArrayList<>();
                String teamName = "";
                for (int i = 0; i < inputFields.size(); i++) {
                    teamName = inputFields.get(i).getText();
                    if (teamName.equals("")) {
                        teamName = "No Name";
                    }
                    names.add(teamName);
                }
                Controller.getInstance().setPowerStationNames(names);
                start(Controller.getInstance().getPrimaryStage());

                getTeamNamesStage.close();
            }
        });
        askInfo.getChildren().add(submitCostomNamesBtn);

        Scene customNamesScene = new Scene(askInfo, 300, 250);

        getTeamNamesStage.setScene(customNamesScene);
    }

    public void getTradeInfo() {

    }
}
