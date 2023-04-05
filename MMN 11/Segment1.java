
/**
 * segment1 represents a line (parallel to the x-axis) using two Points.
 * @author (Liav Segev)
 * @version (20/11/20)
 */
public class Segment1
{
    //declarations
    private Point _poLeft;
    private Point _poRight;
    private final double MIN_SIZE_DIFFERENCE = 0.01;
    private final double DEFAULT_VAL = 0.0;
    
    //constructors
    /**
     * Constructs a new segment using two Points. If the y coordinates are different, change the y of the right point to be equal to the y of the left point. 
     * 
     * @param left the left point of the segment
     * @param right the right point of the segment
     */
    public Segment1(Point left ,Point right)
    {
         if(left.isAbove(right))
             right.setY(left.getY());
         if(left.isUnder(right))
            right.setY(left.getY());
        _poLeft = new Point(left);
        _poRight = new Point (right);

    }//end of #1 Segment1 constructor

    /**
     *Constructs a new segment using 4 specified x y coordinates: Two coordinates for the left point and two coordinates for the right point.  
     *If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     *param@ leftX  X value of left point
     *param@ leftY  Y value of left point
     *param@rightX  X value of right point
     *param@ rightY  Y value of right point
     */
    public Segment1(double leftX, double leftY, double rightX, double rightY)
    {
        _poLeft = new Point(leftX,leftY);
        _poRight = new Point(rightX,rightY);
        if(_poRight.getY()  != _poLeft.getY())
        {
           _poRight.setY(_poLeft.getY());
            
        } 
     
    }//end of #2 Segment1 constructor    
    
    /**
     * Copy Constructor. Construct a segment using a reference segment.
     * @param other the reference segment

     */
    public Segment1(Segment1 other)
    {
            if(other!= null)
           {    this._poLeft = new Point(other._poLeft);
                this._poRight = new Point(other._poRight);
           }
    }  //end of #3 Segment1 (copy) constructor  
    
    /**
     * Returns the left point of the segment.
     * @returns The left point of the segment

     */
    public Point getPoLeft()
    {
        return new Point(_poLeft);
    }//end of getPoLeft method
    
    /**
     * Returns the right point of the segment.
     * @returns the right point of the segment.
     */
    public Point getPoRight()
    {
        return new Point(_poRight);
    }//end of getPoRight method
    
    /**
     * Returns the segment length
     * @returns the segment length
     */
    public double getLength()
    {
        return _poRight.distance(_poLeft);
    }//end of getLength method
    
    /**
     * Checks if the reference segment is equal to this segment.
     * @param other the reference segment
     * @returns True if the reference segment is equal to this segment

     */
    public boolean equals(Segment1 other)
       {
        return this._poLeft.equals(other._poLeft) && this._poRight.equals(other._poRight);

      }//end of equals method
   
    /**
     * Check if this segment is above a reference segment
     * @param other  the reference segment.
     * @returns True if this segment is above the reference segment 
     */
    public boolean isAbove(Segment1 other)
    {
        return this._poLeft.getY() > other._poLeft.getY();
   
    }//end of isAbove method
    
    /**
     * Check if this segment is under a reference segment.
     *@param other  the reference segment.
     *@returns True if this segment is under the reference segment
     */
    public boolean isUnder(Segment1 other)
    {
        return other.isAbove(this);
    
    }//end of isUnder method
    
    /**
     * Check if this segment is left of a received segment.
     *@param other the reference segment
     *@returns True if this segment is left to the reference segment
     */
    public boolean isLeft(Segment1 other)
    {
        if(this._poRight.getX() < other._poLeft.getX())
            return true;
        else
            return false;
    }//end of isLeft method
    
    /**
     * Check if this segment is right of a received segment.
     *@param other the reference segment
     *@returns True if this segment is right to the reference segment
     */
    public boolean isRight(Segment1 other)
    {
        return other.isLeft(this);
    } //end of isRight method
    
    /**
       * Move the segment horizontally by delta.
     * @param  the displacement size
     * 
     */
    void moveHorizontal(double delta)
    {
        if(_poLeft.getX() + delta > DEFAULT_VAL &&_poRight.getX() + delta > DEFAULT_VAL)
        {
            _poLeft.move(delta,DEFAULT_VAL); 
            _poRight.move(delta,DEFAULT_VAL);
            
        }
    }//end of moveHorizontal method
    
    /**
     * Move the segment vertically by delta.
     *@param the displacement size
     */
    public void moveVertical(double delta)
    {
        if(_poLeft.getY() + delta > DEFAULT_VAL && _poRight.getY() + delta > DEFAULT_VAL)
        {
            _poLeft.move(DEFAULT_VAL,delta);
            _poRight.move(DEFAULT_VAL,delta);
            
        }
        
    }//end of moveVertical method
   
    /**
     * Change the segment size by moving the right point by delta. Will be implemented only for a valid delta: only if the new right point remains the right point.
     * @param delta the length change
     */
    public void changeSize(double delta)
    {
        
        if(_poRight.getX() + delta > _poLeft.getX())
        {
            _poRight.move(delta,DEFAULT_VAL);
        }
    }//end of changeSize method
    
    /**
     *Check if a point is located on the segment.
     *@param p  a point to be checked 
     *@returns True if p is on this segment
     */
    public boolean pointOnSegment(Point p)
    {
        return _poLeft.getX() <= p.getX() && _poRight.getX() >= p.getX() &&Math.abs( _poLeft.getY() - p.getY())<0.01;
    
    }//end of pointOnSegment method
    
    /**
     *Check if this segment is bigger than a reference segment 
     *@param other the reference segment
     *@returns True if this segment is bigger than the reference segment
     */
    public boolean isBigger(Segment1 other)
    {
        return this.getLength() > other.getLength();
    }//end of isBigger method
   
    /**
     *Returns the overlap size of this segment and a reference segment. 
     *@param other the reference segment
     *@returns ther overlap size 
     */
    public double overlap(Segment1 other) 
    { 
        
        if(this._poLeft.getX() < other._poLeft.getX() && this._poRight.getX() < other._poRight.getX() && this._poRight.getX() < other._poLeft.getX())
            return DEFAULT_VAL;
        
        if(this._poLeft.getX() > other._poLeft.getX() && this._poRight.getX() > other._poRight.getX() && this._poLeft.getX() > other._poRight.getX())
            return DEFAULT_VAL;    
        
        if(this._poLeft.getX() >= other._poLeft.getX() && this._poRight.getX() >= other._poRight.getX() && this._poLeft.getX() < other._poRight.getX())  
            return Math.round(other._poRight.getX() - this._poLeft.getX());
        
     
        if(this._poLeft.getX() <= other._poLeft.getX() && this._poRight.getX() <= other._poRight.getX() && this._poLeft.getX() <= other._poRight.getX())  
             return Math.round(this._poRight.getX() - other._poLeft.getX());
       
        if( this._poLeft.getX() > other._poLeft.getX() && this._poRight.getX() > other._poRight.getX() && other._poRight.getX() > this._poLeft.getX())
            return other._poRight.getX()  - this._poLeft.getX();
        
        if(this._poLeft.getX() < other._poLeft.getX() && this._poRight.getX() > other._poRight.getX() && other._poRight.getX() > this._poLeft.getX()) 
            return other._poLeft.distance(other._poRight);
        
        if(this._poLeft.getX() > other._poLeft.getX() && this._poRight.getX() < other._poRight.getX() && other._poRight.getX() > this._poLeft.getX()) 
            return this._poLeft.distance(this._poRight);
            
        else
              return DEFAULT_VAL;
    }//end of overlap method
    
    /**
     *Compute the trapeze perimeter, which is constructed by this segment and a reference segment. 
     *@param other  the reference segment
     */
    public double trapezePerimeter(Segment1 other)
    {
        if(this._poLeft.getY() != other._poLeft.getY())
           return this._poLeft.distance(other._poLeft) + this._poRight.distance(other._poRight) + this.getLength() + other.getLength();
       else 
           return DEFAULT_VAL;
    }//end of trapezePerimeter method
   
    /**
     * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
     *@overrides toString in class java.lang.Object
     *returns String representation of this segment
     */
    public String toString()
    {
        return getPoLeft()+"---"+getPoRight() ; 
            
    }//end of toString method

}//end of Segment1 class


   
