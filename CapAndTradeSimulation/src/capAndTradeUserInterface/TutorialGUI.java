/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capAndTradeUserInterface;

import capandtradesimulation.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class TutorialGUI extends Application{
    Scene tutorialScene;
    VBox root;
    
    @Override
    public void start(Stage primaryStage) {
        Label welcome = new Label();
        welcome.setText("Welcome to the Tutorial\n");
        
        //How to play title
        Label howToPlayLbl = new Label();
        howToPlayLbl.setText("How to Play: ");
        
        root = new VBox(10);
        root.getChildren().add(welcome);
        
        // sets the purpose info for the game
        root.getChildren().add(purposeOfGameExplanationLabel());
        
        // sets the how to play information
        root.getChildren().add(displayExplanationOfGame());
        
        //call to menuButton returns will automatically place in root
        returnToMenu();
        
        tutorialScene = new Scene(root, 700, 550);
        primaryStage.setScene(tutorialScene);
        
        tutorialScene.getStylesheets().add("Style.css");
    }
    
    /**
     * Displays the game explanation using Labels and then putting them in a VBox
     * @return a VBox with the correctly formatted labels
     */
    public VBox displayExplanationOfGame() {
        VBox formatedExplanation = new VBox(10);
        
        Label basicPowerStationTitleLbl = new Label();
        basicPowerStationTitleLbl.setText("A Basic Power Station Contains");
        
        Label energyProdLbl = new Label();
        energyProdLbl.setText("1. Energy Production: 100 units");
        
        Label salesLbl = new Label();
        salesLbl.setText("2. Sales: $100.00 per unit of energy for a total of $10,000.00");
        
        Label emissionsLbl = new Label();
        emissionsLbl.setText("3. Emissions: 200 units\n    "
                + "Emissions are the waste produced by the company");
        
        Label cleanRate = new Label();
        cleanRate.setText("4. Clean Rate: Based on way energy is produced\n     "
                + "Clean rate is the money required to clean up waste from one unit of energy");
        
        Label cleanCost = new Label();
        cleanCost.setText("5. Clean Cost: Total cost based on the Clean Rate\n     "
                + "Clean Cost is found by, Clean Rate * Emissions");
        
        Label profit = new Label();
        profit.setText("6. Profit: Money earned after deduction of the clean cost amount");
        
        Label permits = new Label();
        permits.setText("7. Permits: 100 permits\n     "
                + "Permits are a waiver for cleaning up units of energy. One permit = One Unit of Waste");
        
        Label gameVariables = new Label();
        gameVariables.setText("Variables:\n     1. Permits: The permits "
                + "can be traded among each other to maximize the usefulness "
                + "of the clean rates.\n     2. Permits Price: The trades comes "
                + "at a price, because one has to use the permits to avoid clean up "
                + "the permits are traded at a\n       price that is agreed upon by both "
                + "companies.");
        
        Label marginalProfitExplanation = new Label();
        marginalProfitExplanation.setText("Objective:\n     Obtain the maximum"
                + " marginal profit\n     Marginal Profit: this "
                + "is the profit earned based on how well the permits were aquired"
                + "and used it is the amount earned after sales and clean costs are "
                + "deducted.");
        
        
        formatedExplanation.getChildren().add(basicPowerStationTitleLbl);
        formatedExplanation.getChildren().add(energyProdLbl);
        formatedExplanation.getChildren().add(salesLbl);
        formatedExplanation.getChildren().add(emissionsLbl);
        formatedExplanation.getChildren().add(cleanRate);
        formatedExplanation.getChildren().add(cleanCost);
        formatedExplanation.getChildren().add(profit);
        formatedExplanation.getChildren().add(permits);
        formatedExplanation.getChildren().add(gameVariables);
        formatedExplanation.getChildren().add(marginalProfitExplanation);
        
        return formatedExplanation;
    }
    
    /**
     * This is the explanation for the purpose of the game
     * @return Label with an explanation of the game
     */
    public Label purposeOfGameExplanationLabel() {
        Label purposeOfGameLbl = new Label();
        
        String gamePurpose = "Purpose:\n     To learn how energy consumption, permits, and clean up costs\n"
                + "affect a power station company, and to learn the benefits of trading\n"
                + "cleaning permits among power companies";
        purposeOfGameLbl.setText(gamePurpose);
        return purposeOfGameLbl;
    } 
    
    /**
     * This will control the button that will return to the main menu function
     */
    public void returnToMenu () {
        Button menuBtn = new Button();
        menuBtn.setText("Main Menu");
        menuBtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
               Controller.getInstance().selectGameScene("main menu");
            }
            
        });
      
        root.getChildren().add(menuBtn);
    }
}
