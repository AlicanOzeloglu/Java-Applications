/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alican_ozeloglu_hw4;

/**
 *
 * @author Alican
 */
public class Person {
    
    String PersonName;
    Person nextPerson;
    Song nextSong;
    boolean delete;
    
    public Person(){
        PersonName = null;
        nextPerson = null;
        nextSong = null;
        delete = false;
    }
    
}
