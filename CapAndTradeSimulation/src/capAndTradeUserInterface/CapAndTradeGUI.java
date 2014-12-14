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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class CapAndTradeGUI extends Application {
    private Scene scene; //the scene of the game
    private VBox root; //the base user view
    
    private Button tutorialBtn;
    private Button singlePlayerBtn;
    private Button multiPlayerBtn;
    
    @Override
    public void start(Stage primaryStage) {
        Controller.getInstance().setPrimaryStage(primaryStage);
        
        /* Create the Buttons by calling the method that creates them  */
        createTutorialButton();
        createSinglePlayerButton();
        createMultiPlayerButton();
        
        root = new VBox(10);
        
        //Format VBox appropriately
        root.setAlignment(Pos.CENTER);
        
        root.getChildren().add(tutorialBtn);
        root.getChildren().add(singlePlayerBtn);
        root.getChildren().add(multiPlayerBtn);
        root.getChildren().add(quitSimulation());
        
        scene = new Scene(root, 700, 500);
        
        primaryStage.setTitle("Cap and Trade Simulation");
        primaryStage.setScene(scene);
        scene.getStylesheets().add("Style.css");        
        primaryStage.show();
    }
    
    /**
     * creates the tutorial button which will take the user to a tutorial view
     */
    public void createTutorialButton () {
        tutorialBtn = new Button();
        tutorialBtn.setText("Tutorial");
        tutorialBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Controller.getInstance().selectGameScene("tutorial");
            }
        });      
    }
    
    /**
     * This is the set up and control of the singlePlayerButton
     */
    public void createSinglePlayerButton () {
        // create the single player button
        singlePlayerBtn = new Button();
        singlePlayerBtn.setText("Single Player");
        singlePlayerBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Controller.getInstance().selectGameScene("single player");
            }
        });
    }
    
    /**
     * this is the multi player button that will call the multi player GUI classes
     */
    public void createMultiPlayerButton() {
        // create the multiplayer button
        multiPlayerBtn = new Button();
        multiPlayerBtn.setText("Multi Player");
        multiPlayerBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Controller.getInstance().selectGameScene("setup multiplayer");
            }
        });
    }
    
    /**
     * Sends command to controller to quit the simulation
     * @return Button with quit game on it
     */
    public Button quitSimulation() {
        Button quitBtn = new Button();
        quitBtn.setText("Quit");
        
        quitBtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Controller.getInstance().selectGameScene("quit");
            }
            
        });
        return quitBtn;
    }
    
    /**
     * The main class that is used to launch the game
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
