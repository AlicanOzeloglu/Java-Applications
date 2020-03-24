/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alican_Ozeloglu_HW3;

/**
 *
 * @author Alican
 */
public class StackForCoordinate {
    public int size;
    int[] outpop, outtop;
    public NodeForCoordinate first;
    
    public StackForCoordinate(){
        size = 0;
    }
    
    public void push(int r, int c){
        if(size == 0){
            NodeForCoordinate n1 = new NodeForCoordinate(r,c);
            first = n1;
        }
        else{
            NodeForCoordinate n1 = new NodeForCoordinate(r, c, first);
            first = n1;
        }
        size++;
    }
    
    public int[] pop(){
        if(size != 0){
            int[] outpop = {first.row, first.col};   
            first = first.link;
            size--;
            return outpop;
        }
        else{
            System.out.println("There is no element in stack.");
            int[] outpop = {-1, -1}; 
            return outpop;
        }   
    }
    
    public int[] top(){
        if(size != 0){
            int[] outtop = {first.row, first.col};
            return outtop;
        }
        else{
            System.out.println("There is no element in stack.");
            int[] outtop = {-1, -1};
            return outtop;
        }
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
}
