/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author Kyle
 */
public class PowerStation {

    private String powerStationName;
    private int energyProd;
    private int emissions;
    private int permits;
    private int cleanRate;
    private int permitsTraded;
    private int tradeIncome;

    /**
      Default constructor for the power station, sets the variables
     */
    public PowerStation() {
        powerStationName = "a";
        energyProd = 100;
        emissions = 200;
        permits = 100;
        calcCleanRate();
        permitsTraded = 0;
        tradeIncome = 0;
    }

    /**
     * calculates the extra emissions after the permits are used
     * 
     * @return the extra emissions
     */
    public int calcExtraEmitions() {
        int currentPermits = permitsTraded + permits;
        return emissions - currentPermits;
    }

    /**
     * calculates the clean rate of the power stations.
     */
    public void calcCleanRate() {
        //make clean rate a random number
        cleanRate = 0;
        Random r = new Random();
        //Only keep number if it is 10 20 or 60
        while (cleanRate < 10 || (cleanRate > 20 && cleanRate < 50)) {
            cleanRate = r.nextInt(61);
            
            cleanRate -= cleanRate % 10;
            if (cleanRate >= 50) {
                cleanRate = 60;
            }
        }
    }
    
    /**
     * Resets the power station to its default state
     */
    public void powerStationReset() {
        energyProd = 100;
        emissions = 200;
        permits = 100;
        permitsTraded = 0;
        tradeIncome = 0;
    }

    /**
     * This calculates the number of permits used
     * 
     * @return returns the emissions amount
     */
    public int calcPermitUsed() {
        int currentPermits = permitsTraded + permits;
        if (emissions <= currentPermits) {
            return emissions;
        }
        return emissions - (emissions - currentPermits);
    }

    /**
     * Calculates clean cost based on the extra emissions and the clean rate
     * 
     * @return the calculated clean cost
     */
    public int calcCleanCost() {
        return cleanRate * (emissions - calcPermitUsed());
    }

    /**
     * calculates the sales based on the energy production
     * 
     * @return the sales
     */
    public int calcSales() {
        return energyProd * 100;
    }

    /**
     * calculates profit based on the the sales, the trade income and the clean
     * cost
     * 
     * @return returns the profit
     */
    public int calcProfit() {
        return (calcSales() + tradeIncome - calcCleanCost());
    }

    /**
     * calculates marginal profit which is the money made after all expenses
     * have been paid, and the regular sales price is subtracted
     * 
     * @return
     */
    public int calcMarginalProfit() {
        return calcProfit() - (calcSales() - (emissions - permits) * cleanRate);
    }

    /**
     * This gets the name of the power station
     * 
     * @return
     */
    public String getPowerStationName() {
        return powerStationName;
    }

    /**
     * Sets the power station name
     * 
     * @param powerStationName
     */
    public void setPowerStationName(String powerStationName) {
        this.powerStationName = powerStationName;
    }

    /**
     * gets the energy production
     * 
     * @return
     */
    public int getEnergyProd() {
        return energyProd;
    }

    /**
     * sets the energy production rate
     * 
     * @param energyProd amount of production
     */
    public void setEnergyProd(int energyProd) {
        this.energyProd = energyProd;
    }

    /**
     * gets emissions
     * 
     * @return the waste that is produced
     */
    public int getEmissions() {
        return emissions;
    }

    /**
     * sets emissions or the amount of waste produced
     * 
     * @param emissions
     */
    public void setEmissions(int emissions) {
        this.emissions = emissions;
    }

    /**
     * gets the permits
     * 
     * @return the amount of waste that does not need to be cleaned up
     */
    public int getPermits() {
        return permits;
    }

    /**
     * sets the permits, or the amount of waste that does not need to be cleaned
     * up
     * 
     * @param permits
     */
    public void setPermits(int permits) {
        this.permits = permits;
    }

    /**
     * gets the clean rate
     * 
     * @return the cost of cleanup
     */
    public int getCleanRate() {
        return cleanRate;
    }

    /**
     * sets the clean rate
     * 
     * @param cleanRate - the cost of cleanup
     */
    public void setCleanRate(int cleanRate) {
        this.cleanRate = cleanRate;
    }

    /**
     * gets the permits traded
     * 
     * @return the number of permits that have been sold, or bought
     */
    public int getPermitsTraded() {
        return permitsTraded;
    }

    /** 
     * sets the permits traded
     * 
     * @param permitsTraded - negative for sold, positive for bought
     */
    public void setPermitsTraded(int permitsTraded) {
        this.permitsTraded = permitsTraded;
    }

    /**
     * gets the trade income or the price that was received or paid for the 
     * permits
     * 
     * @return positive for sale, negative for expense
     */
    public int getTradeIncome() {
        return tradeIncome;
    }

    /**
     * sets the trade income of the amount that is earned or spent for permits
     * 
     * @param tradeIncome positive for sale, negative for expense
     */
    public void setTradeIncome(int tradeIncome) {
        this.tradeIncome = tradeIncome;
    }
}
