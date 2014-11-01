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
public class CapAndTradeSimulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PowerStation ps = new PowerStation();
        System.out.println(ps.getCleanRate());
        ps.setCleanRate(10);
        ps.setPermitsTraded(-100);
        ps.setTradeIncome(2000);
        //calcProfit() - (calcSales() - (emissions - permits) * cleanRate);
        System.out.println("calcPermitUsed" + ps.calcPermitUsed());
        System.out.println("cleancost" + ps.calcCleanCost());
        System.out.println("calcProfit" + ps.calcProfit());
        System.out.println("calcSales" + ps.calcSales());
        System.out.println(ps.calcMarginalProfit());
    }
    
}
