/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alican_ozeloglu_hw4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alican
 */
public class Alican_Ozeloglu_HW4 {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here    
        Hash h1 = new Hash();
        int modKey = 9973;
        int keyperson;
        int keysong;
        int mainLoop = 0;
        
        while(mainLoop != 1){
            keyperson = 0;
            keysong = 0;
            Scanner sc = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter the command: ");
            String input = sc.nextLine();  // Read user input
            int commandLength = input.length();
            int bosluk1 = -1, bosluk2 = -1;                 // Detecting the spaces to split command, person name and song name
            for(int i = 0 ; i < commandLength ; i++){
                if((int) input.charAt(i) == 32){
                    if(bosluk1 == -1)
                        bosluk1 = i;
                    else{
                        bosluk2 = i;
                        break;
                    }
                }
            }
            String name;
            String songName = null;
            if (bosluk2 < commandLength && bosluk2 != -1){
                songName = input.substring(bosluk2+1,commandLength);
                name = input.substring(bosluk1+1,bosluk2);

            }
            else
                name = input.substring(bosluk1+1,commandLength);

            int lenofperson = name.length();
            for(int i = 0 ; i < lenofperson ; i++){ //  Key function for person
                char c = name.charAt(i);
                keyperson += (int) c;
                keyperson = keyperson % modKey;
            }

            if (bosluk2 < commandLength && bosluk2 != -1){  //  Key function for song
                int lenofsong = songName.length();
                for(int i = 0 ; i < lenofsong ; i++){
                    char c = songName.charAt(i);
                    keysong += (int) c; 
                    keysong = keysong % modKey;
                }
            }
            //"C:\\Users\\Alican\\Desktop\\dosya.txt"
            char Command = input.charAt(0); // Command is the first letter of input
            if(Command == 'O'){         // if command is 'O', we will read from file
                try{
                FileReader f = new FileReader(name);
                BufferedReader br = new BufferedReader(f);
                String row = null;
                while(true){
                    row = null;
                    try {
                            row = br.readLine();
                        } catch (IOException ex) {
                            System.out.println("Reading Error!");
                        }
                        if(row == null)
                            break;

                        commandLength = row.length();
                        bosluk1 = -1; bosluk2 = -1;     // Splitting the read line for detecting command, person name and song name again.

                        for(int i = 0 ; i < commandLength ; i++){
                            if((int) row.charAt(i) == 32){
                                if(bosluk1 == -1)
                                    bosluk1 = i;
                                else{
                                    bosluk2 = i;
                                    break;
                                }
                            }
                        }

                        Command = row.charAt(0);    
                        songName = null;
                        if (bosluk2 < commandLength && bosluk2 != -1){
                            songName = row.substring(bosluk2+1,commandLength);
                            name = row.substring(bosluk1+1,bosluk2);

                        }
                        else
                            name = row.substring(bosluk1+1,commandLength);

                        keyperson = 0;
                        keysong = 0;
                        lenofperson = name.length();
                        for(int i = 0 ; i < lenofperson ; i++){
                            char c = name.charAt(i);
                            keyperson += (int) c;
                            keyperson = keyperson % modKey;
                        }

                        if (bosluk2 < commandLength && bosluk2 != -1){
                            int lenofsong = songName.length();
                            for(int i = 0 ; i < lenofsong ; i++){
                                char c = songName.charAt(i);
                                keysong += (int) c;
                                keysong = keysong % modKey;
                            }
                        }
                        switch (Command) {
                            case 'I':
                                h1.I(name, keyperson);
                                break;
                            case 'L':
                                h1.L(songName, name, keyperson, keysong);
                                break;
                            case 'E':
                                h1.E(songName, name, keyperson, keysong);
                                break;
                            case 'D':
                                h1.D(name, keyperson);
                                break;
                            case 'P':
                                h1.P(name, keyperson);
                                break;
                            case 'M':
                                h1.M(name,keyperson);
                                break;    
                            case 'R':
                                h1.R(name, keyperson);
                                break;
                            case 'X':
                                mainLoop = 1;
                                break;
                            default:
                                System.out.println("Wrong Command Try Again!");
                                break;
                            }

                    }
                }catch(FileNotFoundException e){
                    System.out.println("Wrong Command Try Again!");
                }
            }
            else if(Command == 'X') // if Command is 'X', Exit the program.
                break;
            else{   
            switch (Command) {  // Calling the methods according to command
                    case 'I':
                        h1.I(name, keyperson);
                        break;
                    case 'L':
                        h1.L(songName, name, keyperson, keysong);
                        break;
                    case 'E':
                        h1.E(songName, name, keyperson, keysong);
                        break;
                    case 'D':
                        h1.D(name, keyperson);
                        break;
                    case 'P':
                        h1.P(name, keyperson);
                        break;
                    case 'M':
                        h1.M(name, keyperson);
                        break;    
                    case 'R':
                        h1.R(name, keyperson);
                        break;
                    default:
                        System.out.println("Wrong Command Try Again!");
                        break;
                }
            }
        }
    }
}
