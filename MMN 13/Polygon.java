             
 /**
  * This class represents a polygon on quadrant I .the polygon is represented by an array of points .
  * ID 314095209
  * @author (Liav Segev)
  * @version (7/12/2020)
  */
 public class Polygon
 {
    // instance variables declarations
    private Point[]_vertices ;
    private int _noOfVertices ;//the actuall number of vertices on the array
    private final int MAX_VERTICES = 10;// maximum amount of the polygon's vertices (points in the array)
                
    /**
     * Constructs and initializes an array of points. The array will be initialized by default to the size 10 "cells". 
     */
    public Polygon()
    {
        _vertices = new Point[MAX_VERTICES];
        _noOfVertices = 0;
    }
            
    /**
     * Adds a vertice to the array 
     * @param x the x value of the point 
     * @param y the y value of the point
     * @returns true if the array isn't in full capicity and the point(vertice) was added 
     */
    public boolean addVertex(double x, double y)
    {
        if(_noOfVertices == MAX_VERTICES)
            return false;
        _vertices[_noOfVertices] = new Point(x,y);
        _noOfVertices ++;
         return true;
    }//end of addVertex method
            
    /**
     * checks which point is the highest among the polygon's vertices
     * @returns the highest vertice  
     */
    public Point highestVertex()
    {
        if (_noOfVertices == 0 )
            return null;
        Point highestVertex = _vertices[0];
                
        for(int i = 1; i < _noOfVertices ; i++)
        {
            if(_vertices[i].isAbove(highestVertex))
                highestVertex = _vertices[i];
        }    
        return new Point(highestVertex);
    }//end of highestVertex method   
   
    /**
     * Returns a string representation of this array in the format of - the polygon has 5 vertices: \n ((2.0,1.0),(5.0,0.0),(7.0,5.0),(4.0,6.0),(1.0,4.0)).
     * @overrides toString in class java.lang.Object
     * returns String representation of this Polygon's vertices
     */
    public String toString()
    {
        String toReturn = "";
        
        if(_noOfVertices == 0)
            return "The polygon has 0 vertices.";
 
        for( int i = 1; i < _noOfVertices && _vertices[i] != null ; i++) 
             toReturn +=  "," + _vertices[i] ;  
        toReturn = "(" + _vertices[0] + toReturn + ")";
             return "The polygon has " + _noOfVertices + " vertices:" + "\n" + toReturn ;   
    }//end of toString method
    
    /**
     * Compute the Polygon's perimeter, which is constructed by the points of the array.
     * @returns the polygon's perimeter
     */
    public double calcPerimeter()
    {
        double perimeter = 0; //will be the polygon's perimeter
        
        if(_noOfVertices == 0  || _noOfVertices == 1)
            return 0.0;
        
        else if(_noOfVertices == 2 )
            return _vertices[0].distance(_vertices[1]);
        else    
            for (int i = 0 ; i < _noOfVertices - 1 && _vertices[i+1] != null ; i++)
                perimeter += _vertices[i].distance(_vertices[i+1]);
        perimeter += _vertices[0].distance(_vertices[_noOfVertices-1]);// adding the distance between the first and last vertices
        return perimeter;
    }//end of calcPerimeter method
    
    /**
     * Compute the polygon's area using Heron's formula.
     * @returns the polygon's area
     */
    public double calcArea()
    {
        double s = 0;//will be the perimeter of the polygon divided by two
        double a = 0;//will be the length of the 1st edge of the triangle for Heron's formula 
        double b= 0; //will be the length of the 2nd edge of the triangle for Heron's formula
        double c = 0;//will be the length of the 3rd edge of the triangle for Heron's formula
        double area = 0 ;// will be the area of the polygon
        
        if( _noOfVertices < 3)
            return 0 ;
        
        if (_noOfVertices >= 3)
            for(int i = 2 ; i < _noOfVertices ; i ++)
            {
                  a = _vertices[0].distance(_vertices[i-1]);
                  b = _vertices[i-1].distance(_vertices[i]);
                  c = _vertices[0].distance(_vertices[i]);
                  s = ((a + b + c)/2);
                  area += Math.sqrt(s*(s-a)*(s-b)*(s-c));
            }       
            return area;    
    }//end of calcArea method

    /**
     * Checks if this polygon is bigger of a received polygon.
     * @param other the reference polygon
     * @returns true if this polygon is bigger than the reference polygon 
     */
    public boolean isBigger(Polygon other)
     {
      return this.calcArea() > other.calcArea();//size comparison between two polygons
     }//end of isBigger method

    /**
     * Checks if a given point exists in the array. if it does, check for the exact location in the array. 
     * @param p a point to be checked
     * @returns the location of point p in the array
     */
    public int findVertex(Point p)
    {
        for(int i =0 ; i < _noOfVertices  ; i++)
             if(p.equals(_vertices[i]))
                return i;   //the actuall location of p in the array 
        return -1;//in case that point p isn't located in the array 

    }//end of findVertex method
    
    /**
     * Checks if a given point (vertice) exists in the array. if it does, get the next point (vertice) in line. 
     * @param  p the reference point
     * @return the next vertice of the polygon
     */
    public Point getNextVertex(Point p)
    {
        for(int i = 0 ; i < _noOfVertices ;i++)
        {
            if(findVertex(p) == _noOfVertices -1)//in case that point p is the last/only point on the array return the first point on the array (0th place)
                return new Point(_vertices[0]);
            else if(findVertex(p) == i)
                return new Point(_vertices[i+1]);   //in case that p isn't the last point on the array return the following one 
                
        }    
        return null;//in case there isn't a point p in the array - return null
    }//end of getNextVertex method
    
    /**
     * Returns the polygon's axis-aligned bounding box
     * @return the polygon's bounding box as a polygon 
     */
        public Polygon getBoundingBox()
    {
        if(_noOfVertices < 3)// in case there isn't enough points on the array to represent a polygon - null
            return null;
        double maxY = this.highestVertex().getY();//the largest y value in the array
        double maxX = _vertices[0].getX();
        double minY = _vertices[0].getY();
        double minX = _vertices[0].getX();
        for(int i = 1 ; i < _noOfVertices ; i ++)//finds the the point on the array with the smallest x value
            if(minX > _vertices[i].getX())
                minX = _vertices[i].getX();
        
        for(int i = 1 ; i < _noOfVertices ; i ++)//finds the the point on the array with the smallest y value
            if(minY > _vertices[i].getY())
                minY = _vertices[i].getY();
        
        for(int i = 1 ; i < _noOfVertices ; i ++)//finds the the point on the array with the largest x value
            if(maxX < _vertices[i].getX())
                maxX = _vertices[i].getX();
        
        Polygon poly = new Polygon();//constructing a new polygon
        poly.addVertex(minX,minY);//lower left point addition
        poly.addVertex(maxX,minY);// lower right point addition
        poly.addVertex(maxX,maxY);//upper right point addition
        poly.addVertex(minX,maxY);//upper left point addition
        return poly;//the bounding box as a polygon
    }//end of getBoundingBox method
 }//end of Polygon class
    
