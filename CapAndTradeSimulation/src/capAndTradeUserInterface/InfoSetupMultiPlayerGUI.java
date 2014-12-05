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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class InfoSetupMultiPlayerGUI extends Application {
    Scene singlePlayerScene;
    VBox root;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label welcome = new Label();
        welcome.setText("Welcome to the Single Player");
        root = new VBox();
        root.getChildren().add(welcome);
        returnToMenu();
        singlePlayerScene = new Scene(root, 700, 500);
        primaryStage.setScene(singlePlayerScene);
    }
    
    
    
    
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
