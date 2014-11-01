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
    
    Trade () {
        permitsTraded = 0;
        priceOfTrade = 0;
    }
    
    Trade (int numPermitsTraded, int totalPriceOfTrade) {
        permitsTraded = numPermitsTraded;
        priceOfTrade = totalPriceOfTrade;
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
