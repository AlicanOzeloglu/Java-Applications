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
public class NodeForCoordinate {
    public int row, col;
    public NodeForCoordinate link;
    
    public NodeForCoordinate(int r, int c){
        row = r;
        col = c;
        link = null;
    }
    public NodeForCoordinate(int r, int c, NodeForCoordinate n1){
        row = r;
        col = c;
        link = n1;
    } 
}
