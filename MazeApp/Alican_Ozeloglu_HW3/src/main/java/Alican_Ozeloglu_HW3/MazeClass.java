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
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MazeClass implements MazeInterface {
    String transform = "abcdefghijklmnopqrstuvwxyz";    //we will find sequential letters through this array (using index)
        
    int m, n; //size of matrix = mxn
    int lastpath;
    boolean move, foundLoop;
    char[][] maze;
    char[][] Maze;
    int[][] Loops;
    int dg = 0; //variable of dontgo
    /**
     *
     * @param FileName
     */

    @Override
    public void InputMaze(String FileName) { //public void InputMaze(String FileName)
      
        String string1;
        File f = new File(FileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MazeClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        String row = null;
        try {
            row = reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(MazeClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int i = 0;
        int k = 0;
        
        while (true) {
            if(i == 0){
                string1 = row.charAt(0) + "" + row.charAt(1);   //Reading the row and column size of maze from file and convert to integer.
                m=Integer.parseInt(string1);  
                string1 = row.charAt(3) + "" + row.charAt(4);
                n=Integer.parseInt(string1); 
                i++;
                Maze = new char[m][n];
            }
        try {
            row = reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(MazeClass.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(row == null)
                break;
            for(int j = 0; j < n; j++)
                Maze[k][j] = row.charAt(2 * j);
            k++;
        }
        
        maze = new char[m+2][n+2]; //a new maze of char type and (m+2)x(m+2) size is created for padding
        for(i=1;i<m+1;i++)
            for(k=1; k<n+1; k++){
                maze[i][k] = Maze[i-1][k-1];
            }
        
        for(i=0; i<n+2; i++){  // the 'a' character was placed around the outermost part of the new maze (padding)
            maze[0][i] = maze[m+1][i] = 'a';
        }
        for(i=0; i<m+2; i++){  
            maze[i][0] = maze[i][n+1] = 'a';
        }      
    }

    @Override
    public void FindLoops() {
        Position pos = new Position(1,1); //The object that holds the information of where i am at that moment is initially 1,1
        Position ref = new Position(1,1);  
        Loops = new int[1000][1000]; //The array that i created to put the loops which I found
        int LP = 0;
        StackForCoordinate s0 = new StackForCoordinate();   //Stack for store the path
        StackForCoordinate s1 = new StackForCoordinate();   //Stack for reverse operation
        Position[] dontgo;
        int[] memory;
        dontgo = new Position[4];                      //to avoid entering wrong way again
        dontgo[0] = new Position(-1,-1);
        dontgo[1] = new Position(-1,-1);                
        dontgo[2] = new Position(-1,-1);
        dontgo[3] = new Position(-1,-1);
        
        for(int i = 0; i < m; i++){                     // 2 for loop for making the operations for each element of maze     
            pos.col = 1;  
            for(int j = 0; j < n; j++){
                ref.row = pos.row;
                ref.col = pos.col;
                move = false;
                foundLoop = false;                      //when a loop found, foundLoop will be true.
                dontgo[0].row = -1;
                dontgo[0].col = -1;
                dontgo[1].row = -1;
                dontgo[1].col = -1;
                dontgo[2].row = -1;
                dontgo[2].col = -1;
                dontgo[3].col = -1;
                dontgo[3].row = -1;         
                dg = 0;
                
                while(true){               
                    move = false;
                    //checking the available way. Let say way is available but if we've been there before and come back.
                    //dontgo object giving us information about that and we dont go this way again.
                    if(transform.indexOf(maze[pos.row + 1][pos.col]) == transform.indexOf(maze[pos.row][pos.col]) + 1 && pos.row + 1 != dontgo[0].row && pos.col != dontgo[0].col && pos.row + 1 != dontgo[1].row && pos.col != dontgo[1].col && pos.row + 1 != dontgo[2].row && pos.col != dontgo[2].col && pos.row + 1 != dontgo[3].row && pos.col != dontgo[3].col){   //can it go down
                        s0.push(pos.row, pos.col);   //Let's throw our place to the stack and then go to the down
                        pos.row += 1;
                        move = true;
                    }   
                    else if(transform.indexOf(maze[pos.row][pos.col - 1]) == transform.indexOf(maze[pos.row][pos.col]) + 1 && pos.row != dontgo[0].row && pos.col - 1 != dontgo[0].col && pos.row != dontgo[1].row && pos.col - 1 != dontgo[1].col && pos.row != dontgo[2].row && pos.col - 1 != dontgo[2].col && pos.row != dontgo[3].row && pos.col - 1 != dontgo[3].col){   //can it go left
                        s0.push(pos.row, pos.col);   //Let's throw our place to the stack and then go to the left
                        pos.col -= 1;
                        move = true;       
                    }
                    else if(transform.indexOf(maze[pos.row - 1][pos.col]) == transform.indexOf(maze[pos.row][pos.col]) + 1 && pos.row - 1 != dontgo[0].row  && pos.col != dontgo[0].col && pos.row - 1 != dontgo[1].row  && pos.col != dontgo[1].col && pos.row - 1 != dontgo[2].row  && pos.col != dontgo[2].col && pos.row - 1 != dontgo[3].row  && pos.col != dontgo[3].col){   //can it go up
                        s0.push(pos.row, pos.col);   //Let's throw our place to the stack and then to the up
                        pos.row -= 1;
                        move = true;
                    }
                    else if(transform.indexOf(maze[pos.row][pos.col + 1]) == transform.indexOf(maze[pos.row][pos.col]) + 1 && pos.row != dontgo[0].row && pos.col + 1 != dontgo[0].col  && pos.row != dontgo[1].row && pos.col + 1 != dontgo[1].col  && pos.row != dontgo[2].row && pos.col + 1 != dontgo[2].col  && pos.row != dontgo[3].row && pos.col + 1 != dontgo[3].col){   //can it go right
                        s0.push(pos.row, pos.col);   //Let's throw our place to the stack and then to the right
                        pos.col += 1;
                        move = true;

                    }
                    if(!move){  //If the path was found there is movement and move has been true. If no path is found, move remains false, so it enters the if block.
                        if(pos.row == ref.row && pos.col + 1 == ref.col && s0.size > 2){ //Is there a starting point around? If it exists and creates a loop (at least 4 elements required)
                            foundLoop = true; 
                            s0.push(pos.row, pos.col);
                            break;
                        }
                        else if (pos.row + 1 == ref.row && pos.col == ref.col && s0.size > 2){
                            foundLoop = true;
                            s0.push(pos.row, pos.col);
                            break;
                        }
                        else if (pos.row == ref.row && pos.col - 1 == ref.col && s0.size > 2){
                            foundLoop = true;
                            s0.push(pos.row, pos.col);
                            break;
                        }
                        else if (pos.row - 1 == ref.row && pos.col == ref.col && s0.size > 2){
                            foundLoop = true;
                            s0.push(pos.row, pos.col);
                            break;
                        }
                        if(!foundLoop){
                            if(s0.size > 0){
                                dontgo[dg].row = pos.row;
                                dontgo[dg].col = pos.col;
                                if(dg == 3)
                                    dg = 0;
                                else
                                    dg++;
                                pos.row = s0.top()[0];
                                pos.col = s0.pop()[1];   
                            }
                            else{
                                s0.push(pos.row, pos.col);
                                break;
                            }
                        }
                    }
                }            
                if(foundLoop && s0.size > 3){//In order to be loop, there must be at least 4 elements in the stack.
                    int LPcount = 0;
                    while(s0.size > 0){
                        memory = s0.pop();
                        s1.push(memory[0], memory[1]);
                    }                      
                    while(s1.size > 0){       
                        memory = s1.pop();
                        Loops[LP][LPcount] = memory[0];
                        Loops[LP][LPcount + 1] = memory[1];
                        LPcount+=2;
                    }
                    Loops[LP][LPcount] = -1;           
                    LP++;
                }
                else
                    while(s0.size > 0){
                    int[] pop = s0.pop();
                    }
                pos.row = ref.row;
                pos.col = ref.col;
                pos.col++;
            }
            pos.row++;
        }
        System.out.println("This program has been written by : Alican Ozeloglu");
        System.out.println("This maze has " + LP + " loops");
        for(int d=0; d<LP;d++){
            System.out.print("Loop " + (d+1) + " : " );
            int f = 0;
            while(Loops[d][f] != -1){
                System.out.print(Loops[d][f] + "," + Loops[d][f+1] + " - ");
                f+=2; 
            }
            System.out.println("");        
        }    
    }

    @Override
    public void FindLoops(String FileName) {    //this method following the same ideas with other method. 
        Position pos = new Position(1,1);       //Only differences --> This method is writing the result in a file.
        Position ref = new Position(1,1);  
        Loops = new int[1000][1000]; 
        int LP = 0;
        StackForCoordinate s0 = new StackForCoordinate();   
        StackForCoordinate s1 = new StackForCoordinate();   
        Position[] dontgo;
        int[] memory;
        dontgo = new Position[4];                     
        dontgo[0] = new Position(-1,-1);
        dontgo[1] = new Position(-1,-1);                
        dontgo[2] = new Position(-1,-1);
        dontgo[3] = new Position(-1,-1);
        
        for(int i = 0; i < m; i++){                           
            pos.col = 1;    
            for(int j = 0; j < n; j++){
                ref.row = pos.row;
                ref.col = pos.col;
                move = false;
                foundLoop = false;
                dontgo[0].row = -1;
                dontgo[0].col = -1;
                dontgo[1].row = -1;
                dontgo[1].col = -1;
                dontgo[2].row = -1;
                dontgo[2].col = -1;
                dontgo[3].col = -1;
                dontgo[3].row = -1;              
                dg = 0;
               
                while(true){                   
                    move = false;                   
                    if(transform.indexOf(maze[pos.row + 1][pos.col]) == transform.indexOf(maze[pos.row][pos.col]) + 1 && pos.row + 1 != dontgo[0].row && pos.col != dontgo[0].col && pos.row + 1 != dontgo[1].row && pos.col != dontgo[1].col && pos.row + 1 != dontgo[2].row && pos.col != dontgo[2].col && pos.row + 1 != dontgo[3].row && pos.col != dontgo[3].col){   //can it go down
                        s0.push(pos.row, pos.col);   //Let's throw our place to the stack and then to the down
                        pos.row += 1;
                        move = true;
                    }                  
                    else if(transform.indexOf(maze[pos.row][pos.col - 1]) == transform.indexOf(maze[pos.row][pos.col]) + 1 && pos.row != dontgo[0].row && pos.col - 1 != dontgo[0].col && pos.row != dontgo[1].row && pos.col - 1 != dontgo[1].col && pos.row != dontgo[2].row && pos.col - 1 != dontgo[2].col && pos.row != dontgo[3].row && pos.col - 1 != dontgo[3].col){   //can it go left
                        s0.push(pos.row, pos.col);   //Let's throw our place to the stack and then to the left
                        pos.col -= 1;
                        move = true;                     
                    }
                    else if(transform.indexOf(maze[pos.row - 1][pos.col]) == transform.indexOf(maze[pos.row][pos.col]) + 1 && pos.row - 1 != dontgo[0].row  && pos.col != dontgo[0].col && pos.row - 1 != dontgo[1].row  && pos.col != dontgo[1].col && pos.row - 1 != dontgo[2].row  && pos.col != dontgo[2].col && pos.row - 1 != dontgo[3].row  && pos.col != dontgo[3].col){   //can it go up
                        s0.push(pos.row, pos.col);   //Let's throw our place to the stack and then to the up
                        pos.row -= 1;
                        move = true;
                    }
                    else if(transform.indexOf(maze[pos.row][pos.col + 1]) == transform.indexOf(maze[pos.row][pos.col]) + 1 && pos.row != dontgo[0].row && pos.col + 1 != dontgo[0].col  && pos.row != dontgo[1].row && pos.col + 1 != dontgo[1].col  && pos.row != dontgo[2].row && pos.col + 1 != dontgo[2].col  && pos.row != dontgo[3].row && pos.col + 1 != dontgo[3].col){   //can it go right
                        s0.push(pos.row, pos.col);   //Let's throw our place to the stack and then to the right
                        pos.col += 1;
                        move = true;
                    }
                    if(!move){  //If the path was found there is movement and move has been true. If no path is found, move remains false, so it enters the if block.
                        if(pos.row == ref.row && pos.col + 1 == ref.col && s0.size > 2){ //Is there a starting point around? If it exists and creates a loop (at least 4 elements required)
                            foundLoop = true; 
                            s0.push(pos.row, pos.col);
                            break;
                        }
                        else if (pos.row + 1 == ref.row && pos.col == ref.col && s0.size > 2){
                            foundLoop = true;
                            s0.push(pos.row, pos.col);
                            break;
                        }
                        else if (pos.row == ref.row && pos.col - 1 == ref.col && s0.size > 2){
                            foundLoop = true;
                            s0.push(pos.row, pos.col);
                            break;
                        }
                        else if (pos.row - 1 == ref.row && pos.col == ref.col && s0.size > 2){
                            foundLoop = true;
                            s0.push(pos.row, pos.col);
                            break;
                        }
                        if(!foundLoop){
                            if(s0.size > 0){
                                dontgo[dg].row = pos.row;
                                dontgo[dg].col = pos.col;
                                if(dg == 3)
                                    dg = 0;
                                else
                                    dg++;
                                pos.row = s0.top()[0];
                                pos.col = s0.pop()[1];   
                            }
                            else{
                                s0.push(pos.row, pos.col);
                                break;
                            }
                        }
                    }
                }                  
                if(foundLoop && s0.size > 3){//In order to be loop, there must be at least 4 elements in the stack.
                    int LPcount = 0;
                    while(s0.size > 0){
                        memory = s0.pop();
                        s1.push(memory[0], memory[1]);
                    }                      
                    while(s1.size > 0){
                       
                        memory = s1.pop();
                        Loops[LP][LPcount] = memory[0];
                        Loops[LP][LPcount + 1] = memory[1];
                        LPcount+=2;
                    }
                    Loops[LP][LPcount] = -1;
                    
                    LP++;
                }
                else
                    while(s0.size > 0){
                    int[] pop = s0.pop();
                    }
                
                pos.row = ref.row;
                pos.col = ref.col;
                pos.col++;
            }
            pos.row++;
        }
        File file = new File(FileName);
        FileWriter filewriter;
        try {
            filewriter = new FileWriter(file);
            filewriter.write("This program has been written by : Alican Ozeloglu\n");
            filewriter.write("this maze has " + LP + " loops\n");
            for(int d=0; d<LP;d++){
                filewriter.write("Loop " + (d+1) + " : " );
                int f = 0;
                while(Loops[d][f] != -1){
                    filewriter.write(Loops[d][f] + "," + Loops[d][f+1] + " - ");
                    f+=2; 
                }
                filewriter.write("\n");
            }
            filewriter.close();
        } catch (IOException ex) {
            Logger.getLogger(MazeClass.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }  
}
