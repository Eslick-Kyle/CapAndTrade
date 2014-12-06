/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capAndTradeUserInterface;

import capandtradesimulation.Controller;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Benjamin
 */
public class InfoSetupMultiPlayerGUI extends Application {
    private Scene multiPlayerSetupScene;
    private Stage getTeamNamesStage;
    private VBox root;
    private VBox askInfo;

    
    @Override
    public void start(Stage primaryStage) {
        getTeamNamesStage = primaryStage;
        Label welcome = new Label();
        welcome.setText("Multiplayer Setup");
        askInfo = new VBox();
        askInfo.getChildren().add(welcome);
        getNumberOfTeams();
        multiPlayerSetupScene = new Scene(askInfo, 700, 500);
        primaryStage.setScene(multiPlayerSetupScene);
        multiPlayerSetupScene.getStylesheets().add("Style.css");
    }

        public void displayGetNumberOfTeams () {

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
                Controller.getInstance().selectGameScene("multi player");
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
        askInfo.getChildren().add(numberInputField);
        askInfo.getChildren().add(defaultNamesBtn);
        askInfo.getChildren().add(customNamesBtn);


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
                Controller.getInstance().selectGameScene("multi player");
            }
        });
        askInfo.getChildren().add(submitCostomNamesBtn);

        Scene customNamesScene = new Scene(askInfo, 700, 500);

        getTeamNamesStage.setScene(customNamesScene);
        getTeamNamesStage.setFullScreen(true);
        customNamesScene.getStylesheets().add("Style.css");
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

        askInfo.getChildren().add(menuBtn);
    }
}
