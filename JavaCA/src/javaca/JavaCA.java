/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaca;

import javaca.util.CAUtil;

/**
 *
 * @author name: Pruthvi Mulik
 * Student number:2021405
 * GitHub link: https://github.com/CCT-Dublin/ca1-Pruthvi1-123.git
 */
public class JavaCA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CAUtil caUtil=new CAUtil();
        while(true)
        {
            try{
            int option=caUtil.printMenu();
            switch(option)
            {
                case 1:
                    caUtil.loadData();
                    break;
                case 2:
                    caUtil.performStandaredDeviation();
                    break;
                case 3:
                    caUtil.performMatrixMultiplication();
                    break;
                case 4:
                    caUtil.performMergeSort();
                    break;
                case 5:
                    System.err.println("Thank you!");
                    System.exit(0);
                default:
                    System.err.println("Invalid option, please try again");
            }
            }catch(Exception e)
            {
            System.err.println("Error : "+e.getMessage());
            }
        }
        
    
    }
}
    

