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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.PowerStation;
import model.Trade;

/**
 *
 * @author Benjamin
 */
public class EndGameMultiplayerGUI extends Application {

    private VBox root;
    private VBox displayInfoVBox;
    private Scene endGameScene;

    @Override
    public void start(Stage primaryStage) {
        Label welcome = new Label();
        welcome.setText("The Simulation Results");
        
        //container for quit and return buttons
        HBox formatButtons = new HBox(5);
        formatButtons.getChildren().add(returnToMenu());
        formatButtons.getChildren().add(quitSimulation());

        //base format box for this scene
        root = new VBox();
        root.setPadding(new Insets(10, 10 , 10, 10));
        root.getChildren().add(welcome);
        displayResults();
        root.getChildren().add(displayWinner());
        root.getChildren().add(displayInfoVBox);
        root.getChildren().add(formatButtons);

        endGameScene = new Scene(root, 700, 500);
        primaryStage.setScene(endGameScene);

        endGameScene.getStylesheets().add("Style.css");
        primaryStage.setFullScreen(true);
    }

    /**
     * This formats the results into a VBox so that everything is displayed
     * correctly.
     */
    public void displayResults() {
        displayInfoVBox = new VBox();

        // right now this hBox is not being used!!!!!!!!!!
        HBox titleInfo = new HBox(40);

        //titleInfo.setPadding(new Insets(15, 12, 15, 12));
        titleInfo.setSpacing(20);

        //name Label
        Label nameTitle = new Label();
        nameTitle.setMinWidth(40);
        nameTitle.setText("Name");
        titleInfo.getChildren().add(nameTitle);
        
        //checks if the round is more than two rounds
        if (Controller.getInstance().getNumRounds() < 2) {
            
            //formats how many rounds of trade information the game should display, it sets the 
            //titles for all the rounds
            for (int i = 0; i < Controller.getInstance().getNumRounds(); i++) {
                Label permitsTradedTitle = new Label();
                permitsTradedTitle.setMinWidth(40);
                permitsTradedTitle.setTextAlignment(TextAlignment.CENTER);
                permitsTradedTitle.setText("Traded");

                Label permitsTradedPrice = new Label();
                permitsTradedPrice.setMinWidth(40);
                permitsTradedPrice.setTextAlignment(TextAlignment.CENTER);
                permitsTradedPrice.setText("Earned");

                titleInfo.getChildren().add(permitsTradedTitle);
                titleInfo.getChildren().add(permitsTradedPrice);
            }

            Label marginalProfitTitle = new Label();
            marginalProfitTitle.setMinWidth(120);
            marginalProfitTitle.setText("Total Marginal Profit");

            titleInfo.getChildren().add(marginalProfitTitle);

            displayInfoVBox.getChildren().add(titleInfo);

            //build HBox that will be used to display power station information;
            int i = 0;
            for (PowerStation powerStation : Controller.getInstance().getPowerStations()) {
                HBox powerStationInfo = new HBox(40);
                powerStationInfo.setSpacing(20);

                Label name = new Label();
                name.setText(powerStation.getPowerStationName());
                name.setMinWidth(40);
                name.setTextAlignment(TextAlignment.LEFT);
                powerStationInfo.getChildren().add(name);

                //format the trade history so that it can be seen for all rounds
                for (List<Trade> round : Controller.getInstance().getTradeHistory()) {
                    Label permitsTradedLbl = new Label();
                    permitsTradedLbl.setMinWidth(40);
                    permitsTradedLbl.setTextAlignment(TextAlignment.CENTER);
                    permitsTradedLbl.setText(Integer.toString(round.get(i).getPermitsTraded()));
                    round.get(i).getPermitsTraded();

                    Label priceTradeLbl = new Label();
                    priceTradeLbl.setMinWidth(40);
                    priceTradeLbl.setTextAlignment(TextAlignment.CENTER);
                    priceTradeLbl.setText(Integer.toString(round.get(i).getPriceOfTrade()));
                    round.get(i).getPermitsTraded();

                    powerStationInfo.getChildren().add(priceTradeLbl);
                    powerStationInfo.getChildren().add(permitsTradedLbl);
                }

                //formats and displays the marginal profit for the game
                Label marginalProfit = new Label();
                marginalProfit.setMinWidth(120);
                marginalProfit.setTextAlignment(TextAlignment.RIGHT);
                marginalProfit.setText(Integer.toString(
                        Controller.getInstance().getTotalMarginalProfit().get(i)));

                powerStationInfo.getChildren().add(marginalProfit);

                displayInfoVBox.getChildren().add(powerStationInfo);
                i++;
            }
        } else {                                        //if the round is more than two rounds
            /*This will display the chart of all the marginal profit earned over the rounds*/
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Teams");
            final LineChart<String, Number> lineChart
                    = new LineChart<String, Number>(xAxis, yAxis);

            //sets the end of game results
            lineChart.setTitle("End of game results");
            Integer j = 0;
            for (PowerStation powerStation : Controller.getInstance().getPowerStations()) {
                lineChart.getData().add(buildSeries(powerStation.getPowerStationName(), j));
                j++;
            }

            Scene scene = new Scene(displayInfoVBox, 800, 600);
            displayInfoVBox.getChildren().add(lineChart);
            
        }
    }

    /**
     * builds the chart to be displayed for the final results
     * 
     * @param powerStation - takes the power stations
     * @param j
     * @return
     */
    public XYChart.Series buildSeries(String powerStation, Integer j) {
        XYChart.Series series = new XYChart.Series();
                series.setName(powerStation);
                List<List<Integer>> marginalProfitHistory = Controller.getInstance().getMarginalProfitHistory();
                Integer i = 0;
                int mp = 0;
                for (List<Integer> mph : marginalProfitHistory) {
                    mp += mph.get(j);
                    series.getData().add(new XYChart.Data(i.toString(), mp));
                    i++;
                }
                return series;
    }

    /**
     * This will create a label with all the winner information in it and will
     * return the results
     *
     * @return a label with the winner information
     */
    public Label displayWinner() {
        Label winner = new Label();
        int maxMargProfit = 0;
        int index = 0;
        int indexTie = -1;
        
        //checks for the winner of the game's index
        int i = 0;
        for (int profit : Controller.getInstance().getTotalMarginalProfit()) {
            if (maxMargProfit < profit) {
                maxMargProfit = profit;
                index = i;
            }
            i++;
        }

        //check for multipleWinners and puts their index values into the array
        ArrayList<Integer> winners = new ArrayList<>();
        for (int profit : Controller.getInstance().getTotalMarginalProfit()) {
            if (maxMargProfit == profit) {
                winners.add(i);
            }
            i++;
        }

        //displays the winner information
        String winnerText = "";
        if (winners.size() > 1) {                               //if there are multiple winners

            winnerText = "There is a " + Integer.toString(winners.size()) + " way tie\n";
            winnerText += "The winning teams are ";
            
            //displays all the names of the teams that are the winners
            for (int h = 0; h < winners.size(); h++) {
                if (winners.size() - 1 == h) {
                    winnerText += "and " + Controller.getInstance()
                            .getPowerStations().get(h).getPowerStationName();
                } else {
                    winnerText += Controller.getInstance().getPowerStations().get(h)
                            .getPowerStationName() + ", ";
                }
            }
            winnerText += " with a marginal profit of " + maxMargProfit;
        } else {                                                  //if there is only one winner
            winnerText = "The Winner is team "
                    + Controller.getInstance().getPowerStations().get(index)
                    .getPowerStationName()
                    + " with a marginal profit of " + maxMargProfit;
        }

        winner.setText(winnerText);    //sets the winner text into the label
        return winner;
    }

    /**
     * Sends command to controller to quit the simulation
     *
     * @return Button with quit game on it
     */
    public Button quitSimulation() {
        Button quitBtn = new Button();
        quitBtn.setText("Quit Game");

        quitBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Controller.getInstance().selectGameScene("quit");
            }

        });
        return quitBtn;
    }

    /**
     * The button that will return the user to the main menu
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
}
