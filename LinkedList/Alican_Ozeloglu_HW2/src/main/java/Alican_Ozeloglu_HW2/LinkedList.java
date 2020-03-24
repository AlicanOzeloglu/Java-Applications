/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alican_Ozeloglu_HW2;
import java.util.*;
/**
 *
 * @author Alican
 */
public class LinkedList implements HW2Interface {
    DLNode first,reference,memory,last,ref; //Creating pointer in the type of DLNode class
    int n,i,j,k,m;                          //n is keeping the number of the element in the linked list
    int[] array;                            //will be used in ReverseLink method
    public LinkedList() {                   //constructor for initialling number of element
        n = 0;                              //number of element is 0 at the beginning.
    }
    
    @Override
    public void Insert(int newElement, int pos) throws Exception{   //Method for inserting element into the desired pozition of linked list     
        DLNode node = new DLNode();     //inserting means that new node will be adding. Creating new node object for new element.
        node.Element = newElement;      //putting the newElement value into new node.
        if(pos > n){                    //if desired position not available for linked list, method throw exception.
            throw new LinkedListException();
        }
        else if (n == 0){               //if n is equal to zero that means node will be first elemen of the linked list. 
            first = node;               //first node of the linked list is this new node. Save it into pointer in the name of first.
            last = node;
            node.left = null;           //there is no another node around new node. So, left and right is equal to null.
            node.right = null;
            n++;                        //increasing the number of element.
        }
        else if(pos<n){
            if (pos == 0){              //this part for if we put new element to first pozition of linked list.
                reference = first;      //first node is this new node now.      
                node.left = null;       //new node will be first element. So, there is no node on the left.
                reference.left = node;  //reference is pointing the old first node, there is a new node on the left now.
                node.right = reference; //there is old first node on the right now.
                first = node;           //new first node is this new node now.
                n++;                    
            }
            else{                       //This part for inserting new element between 2 node.
                reference = first;
                for(i=1;i<pos;i++){
                    reference = reference.right;    /*Going to desired position, new node will be inserting after the node*/  
                }                                   /*that is pointing by reference.*/
                node.right = reference.right;       //addresses are carefully changing for avoiding loss any adress of node.
                node.left = reference;              //(this part will be described in the report )
                reference.right = node; 
                node.right.left = node;
                n++;
            }
        }        
    }

    @Override
    public int Delete(int pos) throws Exception {           //This is for Deleting a desired node from the linked list.
        if(n != 0 && pos < n){
            if(pos!=1){                                     //This part for Deleting any node which is between 2 nodes.
                reference = first;
                for(i=1;i<pos;i++){
                    reference = reference.right;            //Going to node which will be deleted
                }
                reference.left.right = reference.right;     //Breaking the links and make neccessery linking.
                reference.right.left = reference.left; 
                n--;
            }
            else {                                          //This part for deleting the node which is at the beginnig of list.
                reference = first;
                reference.right.left = null;
                first = reference.right;
                n--;
            }
        }
        else if(pos == n) {                                 //This part for deleting the node which is at the end of the list.
            reference = last ;
            reference.left.right = null;
            last = reference.left;                          //last node is this new node now.
            n--;
        }
        else {                                              
            throw new LinkedListException();                //if the desired position is not available for list. Method throw exception.
        }
        return reference.Element;
    }

    @Override
    public void ReverseLink() {                             //This part for reversing the linked list.
        reference = first;
        array = new int[n];         
        i = 0;
        while (reference.right != null){                    //Taking the all element into a static array. 
            array[i] = reference.Element;
            i++;
            reference = reference.right;
        }
        array[i] = reference.Element;
        reference = first;
        while(reference.right != null){                     /*Putting back the all element into linked list*/ 
            reference.Element = array[i];                   /*but starting from the opposite side of static array.*/
            i--;
            reference = reference.right;
        }
        reference.Element = array[i];    
    }

    @Override
    public void SquashL() {                     //Method for writing the element and how many element of that element are succession.
        if(n == 0){
            System.out.println("Linked List is Empty");
        }
        else{
            reference = first;                  
            j = 1;
            while(j == 1){
                k = 1;
                if(reference.left == null){                                 //this part for first element.                           
                    DLNode node1 = new DLNode();
                    node1.left = null;
                    node1.Element = reference.Element;                      //writing the element itself.
                    first = node1;
                    while(reference.Element == reference.right.Element){    //to find out how many times it repeats.
                        k++;
                        reference = reference.right;                        //to go ahead, go next node.
                    }
                    DLNode node2 = new DLNode();                            //creating new node to writing the repeats value(k) into it.
                    node1.right = node2;
                    node2.Element = k;
                    memory = node2;
                    last = node2;
                    reference = reference.right;
                }
                else if(reference.right == null){               //if we came to the last node. This node will be repeat 1 time.
                    DLNode node1 = new DLNode();
                    DLNode node2 = new DLNode();
                    node1.Element = reference.Element;          //putting element.
                    node2.Element = 1;                          //putting the repeat value. ofc it is 1 for last node.
                    memory.right = node1;                       //if this node not alone, we never get into this else if btw.
                    node1.right = node2;
                    node2.right = null;
                    last = node2;
                    memory = node2;
                    break;
                }
                else{                                               //this part for middle of the list(not first, not last)
                    DLNode node1 = new DLNode();
                    node1.Element = reference.Element;
                    while(reference.Element == reference.right.Element){
                        k++;
                        reference = reference.right;
                        if(reference.right == null){
                            break;
                        }
                    }
                    reference = reference.right;
                    DLNode node2 = new DLNode();
                    node1.right = node2;
                    memory.right = node1;                   
                    node2.Element = k;
                    node2.right = null;
                    memory = node2;
                    last = node2;                    
                }
            }
            reference = first;                                              
            n=1;
            while(reference.right != null){             //We just linked the node.right pointers above 
                reference.right.left = reference;       //now we linked the left pointers and determine the new list size(n)
                n++;
                reference = reference.right;
            }        
        }
    }
        
    
    @Override
    public void OplashL() {                      //OplashL is the reverse of the SquashL method. 
        m=1;
        int b = 1;
        ref = first;
        if(n == 0){
            System.out.println("Linked List is Empty");
        }
        else{
            while(b == 1){
                int element = ref.Element;          //getting the element
                int n_element = ref.right.Element;  //getting the how many of this element
                ref = ref.right.right;              
                try {
                    Delete(m+1);                    //after we get the necessery value. Deleting the node which is keeping the info about how many is there.
                } catch (Exception ex) {            //according to this value. we inserting that many element into linked list.
                    System.out.println(ex.toString());
                }
                for (j=1 ; j<n_element ; j++){
                    try {
                        Insert(element,m);
                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                    }
                }                
                if(ref.right.right == null)
                    break;
                m += n_element;
            }
            
            int element = ref.Element;              //this part for the last element. 
            int n_element = ref.right.Element;      //Because, we left the loop before the manage operation for last element.
            m+=n_element;
            try {
                Delete(m+1);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
            for (j=1 ; j<n_element ; j++){
                try {
                    Insert(element,m);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }  
        }    
    } 
      
    @Override
    public void Output() {              //method for printing the linked list.
        reference = first;              //we get the first element of linked list.
        System.out.print("The Elements in the list are : ");
        while(reference.right != null){
            System.out.print(reference.Element + " ");      //writing the elements one by one and going next node.
            reference = reference.right;
        }
        System.out.println(reference.Element);
    }

    @Override
    public void ROutput() {             //method for printing the linked list reverse.
        reference = last;               //we get the last element.
        System.out.print("The Reverse Elements in the list are : ");
        while(reference.left != null){
            System.out.print(reference.Element + " ");      //writing the elements one by one and going to the left.
            reference = reference.left;
        }
        System.out.println(reference.Element);
    }

    @Override
    public Exception LinkedListException() {
        return null;      
    }
    
    @Override
    public String toString() {      //this part for system.out.println(LinkedList)command. The adresses will be printed without this part.
        reference = first;          //this part allows the priting element of linked list.
        String out = reference.Element + " ";
        reference = reference.right;
        while(reference.right != null){
            out = out + reference.Element + " ";        //to string operation.
            reference = reference.right;
        }
        out = out + reference.Element + " ";
        return out ;
    }
}