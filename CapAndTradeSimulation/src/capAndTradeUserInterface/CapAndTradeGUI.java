/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capAndTradeUserInterface;

import capandtradesimulation.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class CapAndTradeGUI extends Application {
    Scene scene;
    VBox root;
    
    @Override
    public void start(Stage primaryStage) {
        Controller.getInstance().setPrimaryStage(primaryStage);

        // create the single player button
        Button singlePlayerBtn = new Button();
        singlePlayerBtn.setText("Single Player");
        singlePlayerBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Controller.getInstance().selectGameScene("single player");
            }
        });
        
        // create the multiplayer button
        Button multiPlayerBtn = new Button();
        multiPlayerBtn.setText("Multi Player");
        multiPlayerBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Controller.getInstance().selectGameScene("setup multiplayer");
            }
        });
        
        /* We will need to better improve this GUI look and probably the VBox 
        container */
        root = new VBox();
        root.getChildren().add(singlePlayerBtn);
        root.getChildren().add(multiPlayerBtn);
        tutorialButton();
        
        scene = new Scene(root, 700, 500);
        
        primaryStage.setTitle("Cap and Trade Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void tutorialButton () {
        Button tutorialBtn = new Button();
        tutorialBtn.setText("Tutorial");
        tutorialBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Controller.getInstance().selectGameScene("tutorial");
            }
        });
        
        root.getChildren().add(tutorialBtn);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
