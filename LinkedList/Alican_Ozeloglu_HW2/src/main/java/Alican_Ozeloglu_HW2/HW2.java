/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alican_Ozeloglu_HW2;

/**
 *
 * @author Alican
 */
public class HW2 {
    public static void main(String[] args) {
 // TODO code application logic here
    LinkedList myList = new LinkedList();
        try {
            myList.Insert(1, 0);
            myList.Insert(2, 0);
            myList.Insert(3, 0);
            myList.Insert(3, 0);
            myList.Insert(3, 0);
            myList.Insert(3, 0);
            myList.Insert(2, 0);
            myList.Insert(2, 0);
            myList.Insert(2, 0);
            myList.Insert(2, 0);
            myList.Insert(9, 5);
            myList.Insert(9, 6);
            myList.Insert(1, 0);
            myList.Insert(2, 0);
            myList.Insert(2, 0);
            myList.Insert(3, 0);
            myList.Insert(2, 0);
            myList.Insert(5, 54);

            } catch (Exception ex) {
            System.out.println(ex.toString());
            }
            myList.Output();
            myList.ReverseLink();
            myList.Output();
            myList.SquashL(); 
            myList.Output();
            myList.OplashL();
            myList.Output();
            myList.ROutput();
            System.out.println(myList);
        }
} 

