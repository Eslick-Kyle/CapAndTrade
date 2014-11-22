/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capAndTradeUserInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class SinglePlayerGUI extends Application {
    Scene singlePlayerScene;
    
    @Override
    public void start(Stage primaryStage) {
        Label welcome = new Label();
        welcome.setText("Welcome to the Single Player");
        VBox root = new VBox();
        root.getChildren().add(welcome);
        singlePlayerScene = new Scene(root, 700, 500);
        primaryStage.setScene(singlePlayerScene);
    }
    
}
