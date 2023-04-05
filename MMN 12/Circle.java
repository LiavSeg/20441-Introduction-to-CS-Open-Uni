
/**
 * This program calculates radius, area and peremiter of an incricle and excircle of a given rectangle
 */
import java.util.Scanner;

public class Circle
{
    public static void main(String[]args)
    { 
       Scanner scan = new Scanner(System.in);
       //declerations 
       final double PI = Math.PI;
       final double TWO = 2.0;
       double inRadius,inArea,inPerimeter;
       double exRadius,exArea,exPerimeter;
        
       //intro
       System.out.println("This program calculates " + "radius,area and perimeter " + "of an incircle and excircle " + "of a given rectangle\n" );
       System.out.println( "Please enter A set of values " + "that represents the " + "left-upper point of the rectangle ");
       
       //first set of values - left upper point
       int leftUpX = scan.nextInt();
       int leftUpY = scan.nextInt();
        
       System.out.println("Please enter A set of values " + "that represents the " + "right-lower point of the rectangle ");
        
       //second set of values - right lower point
       int rightDownX = scan.nextInt();
       int rightDownY = scan.nextInt();
      
       // calculation stage 
       double xDistance = Math.pow(leftUpX-rightDownX,TWO);
       double yDistance = Math.pow(leftUpY-rightDownY,TWO);
       
       inRadius = Math.abs(leftUpY-rightDownY)/TWO; //Inradius calculation
       inArea = PI*Math.pow(inRadius,TWO);//Incircle area calcultaion
       inPerimeter = TWO*PI*inRadius;//Incricle perimeter calculation
       
       
       exRadius = Math.sqrt(xDistance+yDistance)/TWO; //Pythagoras distance between two given points 
       exArea = PI*Math.pow(exRadius,TWO);//Excircle Area calculation
       exPerimeter = TWO*PI*exRadius;//Excircle Perimeter calcultation
        
       System.out.println("Incircle: "+"Radius = " + inRadius +"," + " Area = " + inArea +","+ " Perimeter = " + inPerimeter);
       System.out.println("Excircle: "+"Radius = " + exRadius +"," + " Area = " + exArea +","+ " Perimeter = " + exPerimeter);
       
        
    } //end of the main method    
}//end of Circle class
