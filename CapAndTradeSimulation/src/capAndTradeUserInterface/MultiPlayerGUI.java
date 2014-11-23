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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PowerStation;

/**
 *
 * @author Benjamin
 */
public class MultiPlayerGUI extends Application{
    private Scene multiPlayerScene;
    private Stage secondaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        
        TextArea displayTeamsArea = new TextArea();
        
        String displayInfo = "";
        for (PowerStation ps : Controller.getInstance().getPowerStations()) {
            displayInfo += ps.getPowerStationName() + "\n";
        }
        
        displayTeamsArea.setText(displayInfo);
        
        /* This is very basic information, the VBox will need to be replaced, 
        this was mostly to show what needed to be done */
        Label welcome = new Label();
        welcome.setText("Welcome to the Multiplayer");
        VBox root = new VBox();
        root.getChildren().add(welcome);
        root.getChildren().add(displayTeamsArea);
        
        multiPlayerScene = new Scene(root, 700, 500);
        primaryStage.setScene(multiPlayerScene);
    }
    
    /**
     * This function creates a new stage to get the number of teams and then 
     * send the info to name those teams the default names.
     */
    public void getNumberOfTeams() {
        TextField numberInputField = new TextField();    
        
        Button enterBtn = new Button();
        enterBtn.setText("Submit");
        enterBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String numTeams = numberInputField.getText();
                Controller.getInstance().setPowerStationNamesDefault(Integer.parseInt(numTeams));
                start(Controller.getInstance().getPrimaryStage());
                secondaryStage.close();
            }
        });
        
        /* The container to contain the information, This will most likely need 
         to be changed to something else */
        VBox askInfo = new VBox();
        askInfo.getChildren().add(numberInputField);
        askInfo.getChildren().add(enterBtn);
        
        Scene getTeamInfoScene = new Scene(askInfo, 300, 250);
        
        // Creates the secondary stage or the info window
        secondaryStage = new Stage();
        secondaryStage.setTitle("Power Station Information");
        secondaryStage.setScene(getTeamInfoScene);
        secondaryStage.show();
        secondaryStage.toFront();
    }
}
