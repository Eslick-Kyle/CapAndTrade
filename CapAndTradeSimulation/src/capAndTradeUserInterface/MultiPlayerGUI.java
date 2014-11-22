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
public class MultiPlayerGUI extends Application{
    Scene multiPlayerScene;
    @Override
    public void start(Stage primaryStage) {
        
        
        Button enterBtn = new Button();
        enterBtn.setText("Multi Player");
        enterBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        
        VBox askInfo = new VBox();
        askInfo.getChildren().add(enterBtn);
        Scene getTeamInfoScene = new Scene(askInfo, 300, 250);
        
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Power Station Information");
        secondaryStage.setScene(getTeamInfoScene);
        secondaryStage.show();
        
        Label welcome = new Label();
        welcome.setText("Welcome to the Multiplayer");
        VBox root = new VBox();
        root.getChildren().add(welcome);
        multiPlayerScene = new Scene(root, 700, 500);
        primaryStage.setScene(multiPlayerScene);
    }
    
}
