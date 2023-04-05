
/**
 * A conversion program of Kelvin,Celsius and Fahrenheit degree scales.
 */
import java.util.Scanner;

public class Temperature
{
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        
        //declarations
        final double H2O_KELVIN_FREEZING_POINT = 273.15;
        final double H2O_FAHRENHEIT_FREEZING_POINT = 32;
        final double FAHRENHEIT_TO_UNIT_DIVIDER = 5.0/9.0;
        final double UNIT_TO_FAHRENHEIT_DIVIDER = 1.8;
        String tempChar;//temp unit input C/F/K 
        double tempValue;//numerical value input by user
        double c, k, f;//Celsius, Kelvin and Fahrenheit converted values
        char tempUnit;//temp unit character
        
        //introduction
        System.out.println("This program converts " + "a given temperature in a given temperature unit "+"out of Celcius, Fahrenheit or " + "Kelvin and compares the results");
        
        //temperature unit input by user
        System.out.println("\nPlease select a temperature unit - C/F/K " + "and a temperature numerical value");
            tempChar = scan.next();
            tempUnit = tempChar.charAt(0);//reading a single character from  a given string
            tempValue = scan.nextDouble();
        
        //swtich case satements (that includes relevant conversion formulas per each individual case)
        
        switch(tempUnit){
             case 'C':
                   f = UNIT_TO_FAHRENHEIT_DIVIDER*tempValue + H2O_FAHRENHEIT_FREEZING_POINT;
                   k = tempValue + H2O_KELVIN_FREEZING_POINT;
                   System.out.println(tempValue +" C");
                   System.out.println(f + " F");
                   System.out.println(k + " K");
                   break;
             
             case 'F':
                   c = FAHRENHEIT_TO_UNIT_DIVIDER * (tempValue - H2O_FAHRENHEIT_FREEZING_POINT);
                   k = FAHRENHEIT_TO_UNIT_DIVIDER * (tempValue - H2O_FAHRENHEIT_FREEZING_POINT) + H2O_KELVIN_FREEZING_POINT;
                   System.out.println(c + " C");
                   System.out.println(tempValue + " F");
                   System.out.println(k +" K");
                   break;
             
             case 'K':
                   c = tempValue - H2O_KELVIN_FREEZING_POINT;
                   f = UNIT_TO_FAHRENHEIT_DIVIDER*(tempValue - H2O_KELVIN_FREEZING_POINT) + H2O_FAHRENHEIT_FREEZING_POINT;
                   System.out.println("\n" + c + " C");
                   System.out.println(f + " F");
                   System.out.println(tempValue + " K");
                   break; 
             
             default :
                   System.out.println("\nInvalid temperature unit!");          
                   
      
            }//end of swtich case
    
    }//end of method

}//end of class



