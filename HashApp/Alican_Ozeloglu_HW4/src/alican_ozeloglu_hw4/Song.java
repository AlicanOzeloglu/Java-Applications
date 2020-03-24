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
public class Song {
    
    String SongName;
    Song nextSong;
    Person nextPerson;
    
    public Song(){
        SongName = null;
        nextSong = null;
        nextPerson = null;
    }
}
