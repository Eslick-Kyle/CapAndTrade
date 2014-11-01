/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capandtradesimulation;

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

    public Trade (String numPermitsTraded, String totalPriceOfTrade) {
        if (!totalPriceOfTrade.matches("^\\d{1,15}$") || totalPriceOfTrade.equals("")) {
            System.out.println("ERROR: (Class Trade, String constructor) Invalid price Price will be set to 0");
            priceOfTrade = 0;
        } else {
            priceOfTrade = Integer.parseInt(totalPriceOfTrade);
        }
        
        if (!numPermitsTraded.matches("^\\d{1,15}$") || numPermitsTraded.equals("")) {
            System.out.println("ERROR: (Class Trade, String constructor) Invalid number of permits"
                    + " The number permits sold will be set to 0");
            permitsTraded = 0;
        } else {
            permitsTraded = Integer.parseInt(numPermitsTraded);
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
