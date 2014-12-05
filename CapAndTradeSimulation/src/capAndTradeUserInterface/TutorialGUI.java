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
public class TutorialGUI extends Application{
    Scene tutorialScene;
    VBox root;
    
    @Override
    public void start(Stage primaryStage) {
        Label welcome = new Label();
        welcome.setText("Welcome to the Tutorial");
        root = new VBox();
        root.getChildren().add(welcome);
        
        //call to menuButton both to set up and to take care of action
        returnToMenu();
        
        tutorialScene = new Scene(root, 700, 500);
        primaryStage.setScene(tutorialScene);
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
