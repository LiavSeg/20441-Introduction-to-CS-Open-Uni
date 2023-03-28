/**
 * Write a description of class Point here.
 * ID 314095209
 * @author (Liav Segev)
 * @version (16/11/20)
 */
public class Point
{
    //declartions
    private double _radius;//represents vector length from the origo to  given values
    private double _alpha;// represents the angle between the radius and x axle
    private final double RADIAN_TO_DEG_NUMERATOR = 180.0;// 
    private final double PI = Math.PI;//represents 180 degrees 
    private final double DEFAULT_VAL = 0.0;
    private final double MIN_SIZE_DIFFERENCE = 0.01;
    private final double TWO = 2.0;
    //constructors

    /**
     * Constructor for objects of class Point.Constructs a new point by a given x and y coordiantes. .If the given x/y is negative - set it to zero. 
     * @param x  x coordinate
     * @param y  y coordinate
     */ 
    public Point(double x ,double y)
    {
        if(x < DEFAULT_VAL)
        
           x = DEFAULT_VAL;
        
        if(y <= DEFAULT_VAL)    
           
           y = DEFAULT_VAL;
       
        _radius = Math.sqrt(Math.pow(x,TWO) + Math.pow(y,TWO));
        
        if(x == DEFAULT_VAL)
           _alpha =  (PI/TWO)*((RADIAN_TO_DEG_NUMERATOR)/PI);
        else
           _alpha = Math.atan(y/x)*(RADIAN_TO_DEG_NUMERATOR)/PI;

    }//end of #1 Point constructor
    
     /**
     * Constructor for objects of class Point. Constructs a new point by an initialized Point object.
     * @param other initialized Point object used by the copy constructor
     */
    public Point (Point other)
    {
         if(other!= null)  
       {
           this._alpha = other._alpha;
           this._radius = other._radius;
       }
    }//end of #2 Point (copy) constructor
    
    // setters and getters
    /**
     * This method returns the x coordinate
     * @return The x coordinate of the point
     */
    public double getX()
    {
        return _radius*Math.cos(_alpha*(PI/RADIAN_TO_DEG_NUMERATOR));
    }//end of getX method
    
    /**
     * This method returns the x coordinate
     * @return The y coordinate of the point
     * 
     */
    public double getY()
    {
        return _radius*Math.sin(_alpha*(PI/RADIAN_TO_DEG_NUMERATOR));
    }//end of getY method
    
    /**
     * This method sets the x coordinate. If the y coordiante is negative the x coordinate does not change. 
     * @param x The new x coordinate
     */
    public void setX(double x)
    {   
        if (x > DEFAULT_VAL)
        {
         double y = this.getY();
         _radius = Math.sqrt(Math.pow(x,2)+Math.pow(getY(),2));
         _alpha = (RADIAN_TO_DEG_NUMERATOR/PI)*Math.atan(y/x);
        }
         if (x==0)// avoiding division by zero and getting NaN value 
        {
         double y = this.getY();
         _radius = Math.sqrt(Math.pow(x,2)+Math.pow(getY(),2));
         _alpha = (PI/TWO)*((RADIAN_TO_DEG_NUMERATOR)/PI);
        }
        
        
    }//end of setX method
    
    /**
     * This method sets the y coordinate. If the y coordiante is negative the y coordinate does not change.
     * @param y The new y coordiante
     */
    public void setY(double y)
    {
        if(y >= DEFAULT_VAL )
        {
         double x = this.getX();
         _radius = Math.sqrt(Math.pow(getX(),TWO) + Math.pow(y,TWO));
         _alpha = Math.atan(y/x)*(RADIAN_TO_DEG_NUMERATOR/PI);
        }
    }//end of setY method
    
    /**
     * Checks if a given point is equal to this point.
     * @param other The point we equalize to
     * @returns True if the given point is equal to this point
     */
    public boolean equals(Point other)
    {//(this._radius == other._radius && this._alpha == other._alpha
       if(Math.abs(this.getX() - other.getX())<MIN_SIZE_DIFFERENCE && Math.abs(this.getY() - other.getY()) < MIN_SIZE_DIFFERENCE)
            return true;
       else
           return false;
    }//end of equals method
    
    /**
     * Returns a string representation of the Point in the format of (x,y).
     * @Overrides toString in class java.lang.Object
     * @Retruns A string representation of the Point
     */
    public String toString()
    {
        return  "(" + Math.round(_radius*Math.cos(_alpha*PI/RADIAN_TO_DEG_NUMERATOR)*10000)/(double)10000 + "," + Math.round(_radius*Math.sin(_alpha*PI/RADIAN_TO_DEG_NUMERATOR)*10000)/(double)10000 +")" ;    
    }//end of toString method
    
    /**
     * Check if this point is above a received point.
     *@param other The point to check if this point is above
     *@returns True if this point is above the other point
     */
    public boolean isAbove(Point other)
    {
        if(Math.abs(this.getY() - other.getY()) > 0.01 && this.getY() > other.getY())
            return true;
        else
            return false;
        
    }//end of isAbove method
    
    /**
     * Checks if this point is under a given point.
     * @param other The point to check if this point is below
     * returns true if this point is under the given point
     */
    public boolean isUnder(Point other)
    {
        return other.isAbove(this);
    }//end of isUnder method
    
    /**
     *Checks if the point is left to a received point.
     *@param other The point to check if this point is left of
     *returns True if this point is left to ther point
     */
    public boolean isLeft(Point other)
    {
        if(this.getX() < other.getX())
            return true;
        else
            return false;
    }//end of isLeft method
    
    /**
     *Checks if the point is right to a received Point.
     * @param other The point to check if this point is right to
     * returns True if this point is right to ther point
     */
    public boolean isRight(Point other)
    {
        return other.isLeft(this);
    } //end of isRight method  
    
    /**
     * Checks the distance between this point and the given point.
     * @param other the point to measure the distance from
     * @returns The actuall distance 
     */
    public double distance(Point other)
    {
   
         return Math.sqrt(Math.pow(other.getY()-this.getY(),TWO) + Math.pow(other.getX() - this.getX(),TWO));  
    
    }//end of distance method
  
    /**
    * Moves a point. If either coordinate becomes negative the point remains unchanged.
    * @param dx The difference to add to x
    * @param dy - The difference to add to y 
    */
    public void move(double dx ,double dy)
    {
      if(this.getX() + dx >= DEFAULT_VAL && this.getY()+ dy >= DEFAULT_VAL)
     {  
         
         this.setX(this.getX() + dx);
         this.setY(this.getY() + dy);
        
     }
    
   }//end of move method
}//end of Point class


    