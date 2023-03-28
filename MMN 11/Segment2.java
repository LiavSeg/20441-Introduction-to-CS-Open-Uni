
/**
 * Segment2 represents a line (parallel to the x-axis) using a center point and length.
 * ID 314095209
 * @author (Liav Segev)
 * @version (27.11.2020)
 */
public class Segment2
{
   //declarations
   private Point _poCenter;
   private double _length;
   private final double DEFAULT_VAL = 0.0;
   private final int TWO = 2;
   
  
   /**
    *Constructs a new segment using two Points. If the y coordinates are different, change the y of the right point to be equal to the y of the left point. 
    *@param left the left point of the segment
    *@param right the right point of the segment
    */
   public Segment2(Point left ,Point right)
    { 
      _length = right.getX() - left.getX(); 
      if(right.getY() != left.getY())       
        right.setY(left.getY());
      _poCenter = new Point((left.getX()+right.getX())/TWO ,left.getY());
      
    } //end of #1 Segment2 constructor
   
   /**
     * Constructs a new segment using two Points. If the y coordinates are different, change the y of the right point to be equal to the y of the left point. 
     * 
     * @param poCenter the center point of the segment
     * @param length the length of the segment
     */
    public Segment2(Point poCenter,double length)
    {
       _poCenter = new Point(poCenter);
       _length = length;

    }//end of  #2 Segment2 constructor

    /**
     *Constructs a new segment using 4 specified x y coordinates: Two coordinates for the left point and two coordinates for the right point.  
     *If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     *param@ leftX  X value of left point
     *param@ leftY  Y value of left point
     *param@ rightX  X value of right point
     *param@ rightY  Y value of right point
     */
    public Segment2(double leftX, double leftY, double rightX, double rightY)
    {
        _length = rightX - leftX;
        if(leftY != rightY)
            rightY = leftY;
        _poCenter = new Point ((rightX + leftX) / TWO , leftY);

    }//end of  #3 Segment2 constructor
    
    /**
     * Copy Constructor. Construct a segment using a reference segment.
     * @param other the reference segment
     */
    public Segment2(Segment2 other)
    {
            if(other!= null)
           {    this._poCenter = new Point(other._poCenter);
                this._length = other._length;
           }
    } //end of #4 (copy) segment2 constructor   
    
    /**
     * Returns the left point of the segment.
     * @returns The left point of the segment

     */
    public Point getPoLeft()
    {
        return new Point(_poCenter.getX() - (_length/TWO),_poCenter.getY());
    }//end of getpoLeft method
    
    /**
     * Returns the right point of the segment.
     * @returns the right point of the segment.
     */
    public Point getPoRight()
    {
        return new Point(_poCenter.getX() + (_length/TWO),_poCenter.getY());
    }//end of getPoRight method
    
    /**
     * Returns the segment length
     * @returns the segment length
     */
    public double getLength()
    {
        return _length;
    }//end of getLength method
    
    /**
     * Checks if the reference segment is equal to this segment.
     * @param other the reference segment
     * @returns True if the reference segment is equal to this segment

     */
    public boolean equals(Segment2 other)
       {
        return this._poCenter.equals(other._poCenter) && this._length == other._length;
    }//end of equals method
    
    /**
     * Check if this segment is above a reference segment
     * @param other  the reference segment.
     * @returns True if this segment is above the reference segment 
     */
    public boolean isAbove(Segment2 other)
    {
        return this._poCenter.getY() > other._poCenter.getY();
   
    }//end of is above method
    
    /**
     * Check if this segment is under a reference segment.
     *@param other  the reference segment.
     *@returns True if this segment is under the reference segment
     */
    public boolean isUnder(Segment2 other)
    {
        return other.isAbove(this);
    
    }//end of isUnder method
   
    /**
     * Check if this segment is left of a received segment.
     *@param other the reference segment
     *@returns True if this segment is left to the reference segment
     */
    public boolean isLeft(Segment2 other)
    {
        if(this._poCenter.getX() < other._poCenter.getX())
            return true;
        else
            return false;
    }//end of is left method
    
    /**
     * Check if this segment is right of a received segment.
     *@param other the reference segment
     *@returns True if this segment is right to the reference segment
     */
    public boolean isRight(Segment2 other)
    {
        return other.isLeft(this);
    } //end of is right method
    
    /**
     * Move the segment horizontally by delta.Will be implemented only for a valid delta
     * @param delta the displacement size
     * 
     */
    void moveHorizontal(double delta)
    {
        if(getPoRight().getX() + delta > DEFAULT_VAL)
        {
            getPoRight().move(delta,DEFAULT_VAL); 
        }
    }//end of moveHorizontal method
    
    /**
     * Move the segment vertically by delta.Will be implemented only for a valid delta
     *@param delta the displacement size
     */
    public void moveVertical(double delta)
    {
        if(_poCenter.getY() + delta > DEFAULT_VAL)
        {
            _poCenter.move(DEFAULT_VAL,delta);
            
        }
        
    }//end of move vertically method
    
    /**
     * Change the segment size by moving the right point by delta. Will be implemented only for a valid delta: only if the new right point remains the right point.
     * @param delta the length change
     */
   public void changeSize(double delta)
    {
        
      if(getPoRight().getX() + delta > getPoLeft().getX())
       {
           _length = _length + delta;  
           _poCenter.setX(_poCenter.getX() + (delta/TWO));
        }
    }//end of change size method
    
    /**
     *Check if a point is located on the segment.
     *@param p  a point to be checked 
     *@returns True if p is on this segmentgetPoRight()getPoLeft()
     */
    public boolean pointOnSegment(Point p)
    {
        return getPoLeft().getX() <= p.getX() && getPoRight().getX() >= p.getX() &&Math.abs( getPoLeft().getY() - p.getY())<0.01;
    
    }// end of point on segment method
    
    /**
     *Check if this segment is bigger than a reference segment 
     *@param other the reference segment
     *@returns True if this segment is bigger than the reference segment
     */
    public boolean isBigger(Segment2 other)
    {
        return this.getLength() > other.getLength();
    }//end of isBigger method
   
    /**
     *Returns the overlap size of this segment and a reference segment. 
     *@param other the reference segment
     *@returns ther overlap size 
     */
    public double overlap(Segment2 other) 
    { 
        
        if(this.getPoLeft().getX() < other.getPoLeft().getX() && this.getPoRight().getX() < other.getPoRight().getX() && this.getPoRight().getX() < other.getPoLeft().getX())
            return DEFAULT_VAL;
        
        if(this.getPoLeft().getX() > other.getPoLeft().getX() && this.getPoRight().getX() > other.getPoRight().getX() && this.getPoLeft().getX() > other.getPoRight().getX())
            return DEFAULT_VAL;    
        
        if(this.getPoLeft().getX() >= other.getPoLeft().getX() && this.getPoRight().getX() >= other.getPoRight().getX() && this.getPoLeft().getX() < other.getPoRight().getX())  
            return Math.round(other.getPoRight().getX() - this.getPoLeft().getX());
        
     
        if(this.getPoLeft().getX() <= getPoLeft().getX() && this.getPoRight().getX() <= other.getPoRight().getX() && this.getPoLeft().getX() <= other.getPoRight().getX())  
             return Math.round(this.getPoRight().getX() - other.getPoLeft().getX());
       
        if( this.getPoLeft().getX() > getPoLeft().getX() && this.getPoRight().getX() > other.getPoRight().getX() && other.getPoRight().getX() > this.getPoLeft().getX())
            return other.getPoRight().getX()  - this.getPoLeft().getX();
        
        if(this.getPoLeft().getX() < other.getPoLeft().getX() && this.getPoRight().getX() > other.getPoRight().getX() && other.getPoRight().getX() > this.getPoLeft().getX()) 
            return other.getPoLeft().distance(other.getPoRight());
        
        if(this.getPoLeft().getX() > other.getPoLeft().getX() && this.getPoRight().getX() < other.getPoRight().getX() && other.getPoRight().getX() > this.getPoLeft().getX()) 
            return this.getPoLeft().distance(this.getPoRight());
            
        else
              return DEFAULT_VAL;
    }//end of overlap method
    
    /**
     *Compute the trapeze perimeter, which is constructed by this segment and a reference segment. 
     *@param other  the reference segment
     */
    public double trapezePerimeter(Segment2 other)
    {
        if(this._poCenter.getY() != other._poCenter.getY())
           return Math.round(this.getPoLeft().distance(other.getPoLeft()) + this.getPoRight().distance(other.getPoRight()) + this.getLength() + other.getLength());
       else 
           return DEFAULT_VAL;
    }//end of trapeze perimeter method
       
        
     
        
      
    
    /**
     * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
     *@overrides toString in class java.lang.Object
     *returns String representation of this segment
     */
    public String toString()
    {
        return getPoLeft()+"---"+getPoRight() ; 
            
    }//end of toString method
     
}//end of Segment2 class
