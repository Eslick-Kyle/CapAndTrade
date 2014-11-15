/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Benjamin
 */
public class Trade {
    private int permitsTraded;
    private int priceOfTrade;
    
    public Trade () {
        permitsTraded = 0;
        priceOfTrade = 0;
    }
    
    public Trade (int numPermitsTraded, int totalPriceOfTrade) {
        permitsTraded = numPermitsTraded;
        priceOfTrade = totalPriceOfTrade;
    }

    /**
     * String constructor, takes two strings and calls helper function to parse 
     * data
     * @param numPermitsTraded - number of permits traded
     * @param totalPriceOfTrade - price from the trade
     */
    public Trade (String numPermitsTraded, String totalPriceOfTrade) {
        parseInfo(numPermitsTraded, totalPriceOfTrade);
    }
    
    /**
     * Parses the strings with info in them, is mostly a helper to the string
     * constructor
     * @param parsableStringPermits - number of Permits traded
     * @param parsableStringPrice  - price from traded permits
     */
    public void parseInfo(String parsableStringPermits, String parsableStringPrice) {
        if (!parsableStringPrice.matches("^-?\\d{1,15}$") || parsableStringPrice.equals("")) {
            System.out.println("ERROR: (Class Trade, String constructor) Invalid price Price will be set to 0");
            priceOfTrade = 0;
        } else {
            priceOfTrade = Integer.parseInt(parsableStringPrice);
        }
        
        if (!parsableStringPermits.matches("^-?\\d{1,15}$") || parsableStringPermits.equals("")) {
            System.out.println("ERROR: (Class Trade, String constructor) Invalid number of permits"
                    + " The number permits sold will be set to 0");
            permitsTraded = 0;
        } else {
            permitsTraded = Integer.parseInt(parsableStringPermits);
        }
    }


    public int getPermitsTraded() {
        return permitsTraded;
    }

    public void setPermitsTraded(int permitsTraded) {
        this.permitsTraded = permitsTraded;
    }

    public int getPriceOfTrade() {
        return priceOfTrade;
    }

    public void setPriceOfTrade(int priceOfTrade) {
        this.priceOfTrade = priceOfTrade;
    }
    
    
}
