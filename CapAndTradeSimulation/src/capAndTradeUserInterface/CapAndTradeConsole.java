/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capAndTradeUserInterface;

import capandtradesimulation.Controller;
import model.PowerStation;
import model.Trade;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Benjamin
 */
public class CapAndTradeConsole {

    /**
     * default constructor does nothing
     */
    public CapAndTradeConsole() {
    }

    /**
     * This gets the number of teams that the user would like in the simulation
     * 
     * @return returns the number of teams as an int
     */
    public int getNumTeams() {
        int numTeams;
        Scanner in = new Scanner(System.in);
        System.out.print("How many teams would you like for this simulation: ");
        numTeams = in.nextInt();

        return numTeams;
    }

    /**
     * This is a prompt that asks the user if they would like to set their own names
     * or use the default names
     * 
     * @return returns true if the user wants to use default names
     */
    public boolean useDefaultNames() {
        boolean isDefault = true;
        Scanner in = new Scanner(System.in);

        //prompt for default teams
        System.out.print("Do you want to set the team names(y/n)");
        String checkValue = in.nextLine();
        if (checkValue.matches("(?i)y")) {
            isDefault = false;
        }

        return isDefault;
    }

    /**
     * Description: This is for the console version, it will prompt the user for
     * powerStation names. It will then initialize the power stations with those
     * names
     *
     * @param numberOfTeams this is the number of teams to be created
     * @return this will return the list of names
     */
    public List<String> getPowerStationNamesConsole(int numberOfTeams) {
        List<String> teamNames = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        
        //loops through the number of teams getting each teams name
        for (int i = 0; i < numberOfTeams; i++) {
            System.out.print("Power Station Name " + Integer.toString(i + 1) + ": ");
            String powerStationName = input.nextLine();
            teamNames.add(powerStationName);
        }
        return teamNames;
    }

    /**
     * Description: This function will get the trade information for the number
     * of teams from the console. The information can then be used to assign
     * these values to the power stations.
     *
     * @param powerStations the power stations
     * @return - this returns a array list of the information
     */
    public List<Trade> getPowerStationsTradeInformationConsole(List<PowerStation> powerStations) {
        List<Trade> theTrades = new ArrayList<>();
        
        // get information from console
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < powerStations.size(); i++) {
            
            //permits traded
            System.out.print(powerStations.get(i).getPowerStationName() + "'s Permits Traded: ");
            String numPermitsTraded = input.nextLine();
            
            //price of the trade
            System.out.print(powerStations.get(i).getPowerStationName() + "'s Permits Total Price:"
                    + " ");
            String permitsTotalPrice = input.nextLine();

            theTrades.add(new Trade(numPermitsTraded, permitsTotalPrice)); //sets the trades
        }

        return theTrades;
    }

    /**
     * Description: This will display the information that is found in the power
     * Stations
     *
     * @param powerStations the power stations info
     */
    public void displayPowerStationsInfo(List<PowerStation> powerStations) {
        System.out.println("------------------------------");
        
        //loops through each power station and displays the marginal profit
        for (PowerStation thePowerStation : powerStations) {
            System.out.println(thePowerStation.getPowerStationName()
                    + "'s marginal profit:" + thePowerStation.calcMarginalProfit());
        }
    }

    /**
     * this will display the power stations clean rate
     * 
     * @param powerStations - list of the power stations to be displayed
     */
    public void displayPowerStationCleanRate(List<PowerStation> powerStations) {
        for (PowerStation thePowerStation : powerStations) {
            System.out.println(thePowerStation.getPowerStationName()
                    + "'s clean rate is: " + thePowerStation.getCleanRate());
        }
    }

    /**
     *  prompts the user to quit the game
     * 
     * @return returns true if the user would like to continue the game
     */
    public boolean promptToQuit() {
        boolean isDefault = true;
        Scanner in = new Scanner(System.in);

        System.out.println("\nDo you want to play another round?");
        String checkValue = in.nextLine();
        if (checkValue.matches("(?i)y")) {
            isDefault = false;
        }

        return isDefault;
    }

    /**
     * finds and displays the winner of all the rounds
     * 
     * @param totalMarginalProfit - list of the total marginal profit
     * @param powerStations - list of the power stations
     */
    public void displayWinner(List<Integer> totalMarginalProfit, List<PowerStation> powerStations) {
        int temp = 0;
        int index = 0;
        int i = 0;
        
        //finds the index of the top team
        for (int profit : totalMarginalProfit) {
            if (temp < profit) {
                temp = profit;
                index = i;
            }
            i++;
        }
        
        //outputs the top team, does not take into account a tie
        System.out.println("The winner is : " 
                + powerStations.get(index).getPowerStationName() 
                + " with a total marginal profit of: " + temp);
    }
}
