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
public class Test {
    public static void main(String[] args) {
        // TODO code application logic here
        MazeClass maze = new MazeClass();
        maze.InputMaze("C:\\Users\\Alican\\Desktop\\dosya.txt");
        maze.FindLoops();
        maze.FindLoops("C:\\Users\\Alican\\Desktop\\output.txt");  
    }
}
