 
 /**
 * This class represents a long type number using a linked list 
 * ID 314095209 
 * @author (LiavSegev)
 * @version (29/1/2021)
 */
public class BigNumber
{
    private IntNode _head;//the first node of the list 
    /**
     * Constructs a linked list with one digit - zero 
     * Space complexity - O(1)
     * Time complexity - O(1) 
     */     
    public BigNumber()// creating a list with a single number - 0 
    {
                _head = new IntNode(0);
    }
    
    /**
     * Constructs a long number using long number input 
     *@param bigNumber the long number which will become the linked list
     * Time complexity - O(n) 
     * Space complexity - O(n) - (depends on the long  number input size) 
     */     
    public BigNumber(long bigNumber)//gets an exsisting node and puts him in the head of the list
    {
        IntNode ptr = null;  
        while(bigNumber != 0 )
        {
            long digit = bigNumber%10;
            IntNode digitNode = new IntNode((int)digit);
            if(empty())
            {
                _head = digitNode;
                ptr = digitNode;
            }
            else
            {
                ptr.setNext(digitNode);
                ptr = ptr.getNext();
            }
            bigNumber /= 10;
        }
    }
    
    /**
      * Constructs a long number using an int linked list 
      * @param other the reference linked list
      * Time complexity - O(n)
      * Space complexity - O(n) -(depends on the BigNumber list size)
      */
    public BigNumber(BigNumber other)//gets an exsisting node and puts him in the head of the list
    {
        IntNode otherHead = other._head;
        IntNode thisHead = new IntNode(0);
        this._head = thisHead;
        while(otherHead != null)
        {
                thisHead.setValue(otherHead.getValue());
                
                otherHead = otherHead.getNext();
                if(otherHead != null)
                {
                    IntNode thisHeadNext = new IntNode(0);
                    thisHead.setNext(thisHeadNext);
                    thisHead = thisHead.getNext();
                }
        }
         
    }
    
    /**
      * Returns a string representation of the a long number
      * @Overrides toString in class java.lang.Object
      * @Retruns A string representation of a long numer
      * timeComplexity O(n)
      * SpaceComplexity O(n)
      */        
    public String toString()
    {
                return toString(_head);
    }// end of toString method
    private String toString(IntNode p)
    {
        if(p.getNext() == null)
        {
            System.out.print(p);       
            return"";
        }
        return  toString(p.getNext()) + p.getValue();// calling to the next node recursively + the node's value
    }  //end of toString overoading     
            
    /**
      * This method compares two linked lists that represent two long number and 
      *
      * @param  other -  the reference BigNumber linked list 
      * @return which list is bigger  -> 1 this is bigger , -1 other is bigger , 0 eqaul
      * timeComplexity O(n*m) - in the worest case - the numbers are equal in their length, and the method will check every digit in each number
      * spaceComplexity O(1)
      */
    public int compareTo(BigNumber other)
        
    {
        IntNode ptr1 = this._head;
        IntNode ptr2 = other._head;
        int flag = 0;// if the numbers are equal flag will keep the value - 0
        while( ptr1  != null || ptr2 != null)
        {   
            if(ptr1 != null && ptr2 == null)// in case this BigNumber has more digits than other BigNumber this is bigger -> return 1
                return 1;
            else if(ptr1 == null && ptr2 != null)//in case other BigNumber has more digits than this BigNumber other is bigger -> return -1
                return -1;
            else if(ptr1.getValue() < ptr2.getValue())// 
                flag = -1;   
            else if(ptr2.getValue() < ptr1.getValue())//
                flag = 1;
            
            ptr1 = ptr1.getNext();
            ptr2 = ptr2.getNext();
        }
        return flag;
    }//end of compareTo method
    
    /**
      * This method adds two linked lists to a third linked list of thier sum 
      *
      * @param  other -  the reference BigNumber linked list 
      * @return big -  the sum of this and other
      * timeComplexity O(n*m)
      * spaceComplexity O(n)
      */    
    public BigNumber addBigNumber(BigNumber other)
    {
        BigNumber big = new BigNumber();// TheBigNumber sum
        IntNode ptr = big._head; // points on the sum's values
        IntNode ptrThis = this._head;
        IntNode ptrOther = other._head;
        int nextVal = ptrThis.getValue() + ptrOther.getValue();// addition values 
        int temp = 1;// in case nextVal >= 10 add temp to the next 'nextVal'
        if(nextVal >= 10)
        {
            ptr.setValue((nextVal % 10));
            nextVal = (ptrThis.getNext().getValue() + ptrOther.getNext().getValue()) + temp;     
        }
        else
            {   ptr.setValue((nextVal));
                nextVal = (ptrThis.getNext().getValue() + ptrOther.getNext().getValue());
            }
       
        ptrThis = ptrThis.getNext() ;
        
        ptrOther = ptrOther.getNext();      
        
        while(ptrThis != null ||  ptrOther != null)
        {
            if(ptrThis == null && ptrOther != null)// in case that the linked list is empty and the _
            {
                nextVal =  ptrOther.getValue();
                IntNode nextValNode  = new IntNode(nextVal);
                ptr.setNext(nextValNode);
                ptrOther = ptrOther.getNext() ;
            }
            else if(ptrThis != null && ptrOther == null)
            {
                nextVal = ptrThis.getValue();
                IntNode nextValNode  = new IntNode(nextVal);
                ptr.setNext(nextValNode);
                ptrThis = ptrThis.getNext() ;
            }
            else 
            {
                if(nextVal >= 10 )
                {
                    IntNode nextValNode  = new IntNode(nextVal%10);
                    ptr.setNext(nextValNode);
                    if(ptrThis.getNext() !=null && ptrOther.getNext() != null)
                    {
                            nextVal = (ptrThis.getNext().getValue() + ptrOther.getNext().getValue()) + temp;
                            ptrThis= ptrThis.getNext() ;
                            ptrOther = ptrOther.getNext();
                    }
                        else if(ptrThis.getNext() == null && ptrOther.getNext() == null)
                    {
                         ptr= ptr.getNext();
                         IntNode nextValNode1  = new IntNode(temp);
                         ptr.setNext(nextValNode);   
                         
                    }
                }
                else
                {
                    IntNode nextValNode  = new IntNode(nextVal);
                    ptr.setNext(nextValNode);
                    ptrThis= ptrThis.getNext() ;
                    ptrOther = ptrOther.getNext();
                    if(ptrThis!=null && ptrOther != null)
                        nextVal = ptrThis.getValue() + ptrOther.getValue();
                }
            }
            ptr = ptr.getNext();
        }
        return big;  
    }// end addBigNumber method     
    
    /**
      * This method adds a long number value to a link lists using addBigNumber method to a third linked list of thier sum 
      *
      * @param  other -  the reference BigNumber linked list 
      * @return big -  the sum of this and num (using addBigNumber method)
      * Time complexcity - O(n*m) -  the same as addBigNumber method (using addBigNumber)
      * Space complexcity - O(n)  
      */   
    public BigNumber addLong(long num)
    {
        BigNumber bigNum = new BigNumber(num);  
        return this.addBigNumber(bigNum);
    }// end addLong method
    
    /**
     * This method subtracts this linked list from other linked list number value to a link lists  to a third linked list of thier difference 
     * @param  other -  the reference BigNumber linked list 
     * @return bigSub -  the difference of this and other (BigNumber object)
     * time complexity O(n*m) - going through both of the lists
     * Space comlexity O(n)
     */   
    public BigNumber subtractBigNumber(BigNumber other)
    {
        BigNumber bigSub = new BigNumber();//the BigNumber difference
        IntNode ptr = bigSub._head; //points on the difference's values
        int subVal = 0;// sub values
        IntNode ptrThis = this._head;
        IntNode ptrOther = other._head;
        IntNode ptrZero = bigSub._head;
        if(this.compareTo(other) == -1)// in case this bigNumber is smaller than the referenece BigNumber call for the same method reversly
        {
            return other.subtractBigNumber(this);
        }
            
        if(this._head.getValue() < other._head.getValue())//
        {
            this._head.getNext().setValue(this._head.getNext().getValue() - 1);
            this._head.setValue(this._head.getValue() + 10);
            subVal = this._head.getValue() - other._head.getValue();
            ptr.setValue(subVal);
        }
        else
        {
            subVal = this._head.getValue() - other._head.getValue();
            ptr.setValue(subVal);
        }
            
        this._head = this._head.getNext();
        other._head =  other._head.getNext();
            
        while(this._head != null ||  other._head != null)
        {
                if(this._head != null && other._head == null)
                {
                    subVal = this._head.getValue();
                    IntNode nextValNode = new IntNode(subVal);
                    ptr.setNext(nextValNode);
                    this._head = this._head.getNext();
                }
                
                else if(this._head.getValue() < other._head.getValue())//
                {
                        this._head.getNext().setValue(this._head.getNext().getValue() - 1);
                        this._head.setValue(this._head.getValue() + 10);
                        subVal = this._head.getValue() - other._head.getValue();
                        IntNode nextValNode = new IntNode(subVal);
                        ptr.setNext(nextValNode);
                        this._head = this._head.getNext();
                        other._head =  other._head.getNext();
                }
                else
                {
                        subVal = this._head.getValue() - other._head.getValue();
                        IntNode nextValNode = new IntNode(subVal);
                        ptr.setNext(nextValNode);
                        this._head = this._head.getNext();
                        other._head =  other._head.getNext();
                }
                ptr = ptr.getNext(); 
                if(ptrZero.getNext().getValue() != 0 )
                    ptrZero = ptrZero.getNext();
        }
        
        if(ptr.getValue() == 0 && ptr.getNext() == null)
            ptrZero.setNext(null);
        this._head = ptrThis;
        other._head = ptrOther;
        return bigSub;
        }// end subtractBigNumber method
         
    /**
     * This method multiplies this BigNumber by other BigNumber 
     * @param  other -  the reference BigNumber linked list 
     * @return bigMult -  the result of the bigNumbers multiplication
     * Time complexity - O(m*n) - going through both of the bigNumbers
     * Space complexity - O(n) 
     */
    public BigNumber multBigNumber(BigNumber other)
    {
        BigNumber bigMult = new BigNumber();// inizitallizing the multiplication linked list 
        IntNode ptr = bigMult._head;// points on the multiplication's list
        IntNode ptrThis =  this._head;// points on this list's values
        IntNode ptrOther = other._head;// points on the other list's values
        int counter = 0;// counts how many zeros needs be added to a certain sum / transision
        int multVal = 0;// multiplication value of two digit(same as in elementary school multiplictaion, two digits in the same time)
        int decimal = 0;// will keep the tens digit in case multval will be >= 10
        int temp1 = 0;//
        
        while(ptrThis != null || ptrOther != null)
        {

             if(ptrThis == null && ptrOther.getNext() !=null)
             {
                ptrThis = this._head;
                ptrOther = ptrOther.getNext();
                ptr = bigMult._head;
                counter ++;
                temp1 = counter;
                multVal = ptrThis.getValue() * ptrOther.getValue();
                while(temp1 > 0)
                {
                    ptr = ptr.getNext();
                    temp1 -- ;
                }
             }
             else
                multVal = ptrThis.getValue() * ptrOther.getValue();
             if(multVal >=10)
             {
                     ptr.setValue((multVal%10) + ptr.getValue());
                     
                     decimal = (multVal)/10;
                     if(ptr.getNext() != null)
                        ptr.getNext().setValue(ptr.getNext().getValue() + decimal);
                     
                     else
                     {
                         IntNode multNode = new IntNode(decimal);
                         ptr.setNext(multNode);
                     }
             }
               
             else if(ptr.getNext() == null && ptrOther.getNext() != null || ptr.getNext() == null && ptrThis.getNext() != null) 
             {
                IntNode multNode = new IntNode(0);
                ptr.setNext(multNode); 
                ptr.setValue((multVal)+(ptr.getValue()));
             }
             else         
                ptr.setValue((multVal)+(ptr.getValue()));

             if(ptr.getValue() >= 10 && ptr.getNext() == null)
             {
                    int dec = (ptr.getValue())/(10);
                    ptr.setValue(ptr.getValue()%10);
                    IntNode multNode = new IntNode(dec);
                    ptr.setNext(multNode);
                    
             }
             if(ptr.getValue() >= 10 && ptr.getNext() != null)//if one of the values is >= than 10 as a result of the addition of values(during calcultaions)
             {
                int dec = (ptr.getValue())/(10);
                ptr.setValue(ptr.getValue()%10);
                ptr.getNext().setValue(dec + ptr.getNext().getValue());
             }
             ptr = ptr.getNext(); 
             ptrThis = ptrThis.getNext();
             if(ptrThis == null && ptrOther.getNext() == null)//while loop "stopping criteria" 
                    ptrOther = ptrOther.getNext();
        }
        return  bigMult;
    }//end of multBigNumber method
         
    /**
     * This method checks if the linked list is empty
     *  True - the list is empty 
     * False -  not empty
     * O(1)
     * O(1)
     */
    public boolean empty()
    {
        return _head == null;   
    }// end of isEmpty method
}//end of BigNumber class
//Menny thank you for a great semester !        
        
     
    
   

