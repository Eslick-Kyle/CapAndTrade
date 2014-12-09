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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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
    private Button defaultNamesBtn;
    private Button customNamesBtn;
    private Button submitCustomNamesBtn;
    private ComboBox inputNumTeamsComboBox;
    
    @Override
    public void start(Stage primaryStage) {
        getTeamNamesStage = primaryStage;
        Label welcome = new Label();
        welcome.setText("Multiplayer Setup");
        askInfo = new VBox(10);
        askInfo.setAlignment(Pos.CENTER);
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
        /* The container to contain the information, This will most likely need 
         to be changed to something else */
        
        Label enterTeamsLbl = new Label();
        enterTeamsLbl.setText("Number of Teams:");
        
        HBox enterNumTeamsHBox = new HBox();
        enterNumTeamsHBox.setAlignment(Pos.CENTER);
        //format enterNumTeamsHBox
        enterNumTeamsHBox.setSpacing(10);
        
        //Enter info to enterNumTeamsHBox
        createComboBoxForNumTeams();
        enterNumTeamsHBox.getChildren().add(enterTeamsLbl);
        enterNumTeamsHBox.getChildren().add(inputNumTeamsComboBox);
        
               
        defaultNamesButton();
        nonDefaultNamesButton();
        // Enter information into the base containter
        askInfo.getChildren().add(enterNumTeamsHBox);
        askInfo.getChildren().add(defaultNamesBtn);
        askInfo.getChildren().add(customNamesBtn);


    }

    /**
     * This will create a button that will handle the case of non default teams
     */
    public void nonDefaultNamesButton() {
        customNamesBtn = new Button();
        customNamesBtn.setText("Non Default Names");
        customNamesBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String numTeams = inputNumTeamsComboBox.getSelectionModel().getSelectedItem().toString();
                getCustomPowerStationNames(Integer.parseInt(numTeams));

            }
        });
    }
    
    /**
     * This will create a button that will handle the case of default names.
     */
    public void defaultNamesButton() {
        defaultNamesBtn = new Button();
        defaultNamesBtn.setText("Default Names");
        defaultNamesBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String numTeams = inputNumTeamsComboBox.getSelectionModel().getSelectedItem().toString();
                
                Controller.getInstance().setPowerStationNamesDefault(Integer.parseInt(numTeams));
                Controller.getInstance().selectGameScene("multi player");
            }
        });
    }
    
    /**
     * This changes the stage to get the power stations information
     *
     * @param numTeams - this is the number of teams
     */
    public void getCustomPowerStationNames(int numTeams) {

        Label customNamesLabel = new Label();
        customNamesLabel.setText("Enter Power Station Names:");
        askInfo = new VBox(5);
        askInfo.setAlignment(Pos.CENTER);
        askInfo.getChildren().add(customNamesLabel);

        ArrayList<TextField> inputFields = new ArrayList<>();
        for (int i = 0; i < numTeams; i++) {
            TextField inputName = new TextField();
            inputName.setMaxWidth(400);
            inputFields.add(inputName);
            askInfo.getChildren().add(inputName);
        }

        submitButton(inputFields);
        askInfo.getChildren().add(submitCustomNamesBtn);

        Scene customNamesScene = new Scene(askInfo, 700, 500);

        getTeamNamesStage.setScene(customNamesScene);
        getTeamNamesStage.setFullScreen(true);
        customNamesScene.getStylesheets().add("Style.css");
    }
    
    /**
     * This is the submit button which will submit the custom names
     */
    public void submitButton(List<TextField> inputFields) {
        submitCustomNamesBtn = new Button();
        submitCustomNamesBtn.setText("Submit Names");
        submitCustomNamesBtn.setOnAction(new EventHandler<ActionEvent>() {

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
    }
    
    /**
     * This will create a new ComboBox that will set up the get num teams info
     */
    public void createComboBoxForNumTeams() {
        inputNumTeamsComboBox = new ComboBox();
        ObservableList<String> displaySelection = FXCollections.observableArrayList();
        
        // put the numbers into the list
        for (int i = 1; i <= 13; i++) {
            displaySelection.add(Integer.toString(i));
        }
        
        inputNumTeamsComboBox.setItems(displaySelection);
        inputNumTeamsComboBox.setValue("1");
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
