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

    public PowerStation() {
        powerStationName = "a";
        energyProd = 100;
        emissions = 200;
        permits = 100;
        calcCleanRate();
        permitsTraded = 0;
        tradeIncome = 0;
    }

    public int calcExtraEmitions() {
        int currentPermits = permitsTraded + permits;
        return emissions - currentPermits;
    }

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

    public int calcPermitUsed() {
        int currentPermits = permitsTraded + permits;
        if (emissions <= currentPermits) {
            return emissions;
        }
        return emissions - (emissions - currentPermits);
    }

    public int calcCleanCost() {
        return cleanRate * (emissions - calcPermitUsed());
    }

    public int calcSales() {
        return energyProd * 100;
    }

    public int calcProfit() {
        return (calcSales() + tradeIncome - calcCleanCost());
    }

    public int calcMarginalProfit() {
        return calcProfit() - (calcSales() - (emissions - permits) * cleanRate);
    }

    public String getPowerStationName() {
        return powerStationName;
    }

    public void setPowerStationName(String powerStationName) {
        this.powerStationName = powerStationName;
    }

    public int getEnergyProd() {
        return energyProd;
    }

    public void setEnergyProd(int energyProd) {
        this.energyProd = energyProd;
    }

    public int getEmissions() {
        return emissions;
    }

    public void setEmissions(int emissions) {
        this.emissions = emissions;
    }

    public int getPermits() {
        return permits;
    }

    public void setPermits(int permits) {
        this.permits = permits;
    }

    public int getCleanRate() {
        return cleanRate;
    }

    public void setCleanRate(int cleanRate) {
        this.cleanRate = cleanRate;
    }

    public int getPermitsTraded() {
        return permitsTraded;
    }

    public void setPermitsTraded(int permitsTraded) {
        this.permitsTraded = permitsTraded;
    }

    public int getTradeIncome() {
        return tradeIncome;
    }

    public void setTradeIncome(int tradeIncome) {
        this.tradeIncome = tradeIncome;
    }
}
