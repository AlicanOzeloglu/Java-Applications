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
public class Hash {

    Person[] newPersonHash;
    Song[] newSongHash;
    Song refSong;
    Person refPerson;
    int modKey = 9973; 
    
    int size;
    @SuppressWarnings("empty-statement")
    public Hash(){
        size = 0;
        newPersonHash = new Person[10000];  // Creating hash to save persons
        newSongHash = new Song[10000];  // Creating hash to save songs
    }

    
    public void I(String name, int keyperson){  // Insert Method.
        if(newPersonHash[keyperson] == null){   //  if this index of hash is empty, put there the new person.
            Person p = new Person();
            p.PersonName = name;
            newPersonHash[keyperson] = p;
        }
        else{
            if(name.compareTo(newPersonHash[keyperson].PersonName) != 0){   //  if this index of hash is not empty, go next index until find empty place
                Person p = new Person();
                //System.out.println("collision");
                while(newPersonHash[keyperson] != null){
                    keyperson++;
                    
                } 
                newPersonHash[keyperson] = p;
            }
            else{
                System.out.println(newPersonHash[keyperson].PersonName +" can not be created");
            }
        }
    }
    
    public void L(String s, String p, int keyperson, int keysong){  //  Like Method
        Person p1 = new Person();
        Song s1 = new Song();
        Song s2 = new Song();
        int a=0;
        p1.PersonName = p;
        s1.SongName = s;
        s2.SongName = s;
        if(newPersonHash[keyperson] != null){   //  Checking, is there any person to like song, and is this person who i looking for
            try{
                while(newPersonHash[keyperson].PersonName.compareTo(p) != 0 || newPersonHash[keyperson].delete){    //  if any collision happend before and deleted it,
                    keyperson++;                                                                                    //   check the delete sign when searching.
                }
            }catch(NullPointerException e){   
            }               
            if(newPersonHash[keyperson] != null){                                               // take the new song and put into the linked list which is created for 
                if(newSongHash[keysong] == null){                                               // key index of Hash tables.
                    newSongHash[keysong] = s1;
                    s1.nextPerson = p1;    
                }
                else{
                    refPerson = newSongHash[keysong].nextPerson;
                    while(refPerson.nextPerson != null){
                        if(p.compareTo(refPerson.PersonName) == 0)
                            a=1;
                        refPerson = refPerson.nextPerson;
                    }
                    if(p.compareTo(refPerson.PersonName) == 0)
                            a=1;
                    if(a == 0)
                        refPerson.nextPerson = p1;
                }
                a = 0;
                if(newPersonHash[keyperson].nextSong == null)
                    newPersonHash[keyperson].nextSong = s2;
                else{
                    refSong = newPersonHash[keyperson].nextSong;
                    while(true){
                        if(refSong.nextSong == null)
                            break;
                        if(s.compareTo(refSong.SongName) == 0)
                            a=1;
                        refSong = refSong.nextSong;
                    }
                    if(s.compareTo(refSong.SongName) == 0)
                            a=1;
                    if(a == 0)
                        refSong.nextSong = s2;

                }

            }
            else
                System.out.println(p + " is not created so Song cannot be liked”");
        }
        else
            System.out.println(p + " is not created so Song cannot be liked”");
    }
    
    public void E(String s, String p, int keyperson, int keysong){  // Erase Method      
        try{
            while(newPersonHash[keyperson].PersonName.compareTo(p) != 0 || newPersonHash[keyperson].delete){    // Search the correct person, maybe there was a collision happened before.
                keyperson++;
            }
        }catch(NullPointerException e){

        }
        
        refSong = newPersonHash[keyperson].nextSong;                                    //  finding the song to be deleted (in linked list which is holding the songs) and delete it.
        if(refSong.SongName.compareTo(s) == 0){                                         //  go song hash and desired song, delete the person from song lovers linked list. 
            if(refSong.nextSong != null)
                newPersonHash[keyperson].nextSong = refSong.nextSong;
            else
                newPersonHash[keyperson].nextSong = null;
        }
        else{
            while(refSong != null){
                if(refSong.nextSong.SongName.compareTo(s) == 0 ){
                    refSong.nextSong = refSong.nextSong.nextSong;
                    break;
                }
                if(refSong.nextSong.nextSong == null)
                    break;
                refSong = refSong.nextSong;
            }
        }
        
        refPerson = newSongHash[keysong].nextPerson;
        
        if(refPerson.PersonName.compareTo(p) == 0){
            if(refPerson.nextPerson != null)
                newSongHash[keysong].nextPerson = refPerson.nextPerson;
            else{
                newSongHash[keysong].nextPerson = null;  
                newSongHash[keysong] = null;
            }
        }
        else{
            while(refPerson != null){
                if(refPerson.nextPerson.PersonName.compareTo(p) == 0){
                    refPerson.nextPerson = refPerson.nextPerson.nextPerson;
                    break;
                }
                if(refPerson.nextPerson == null)
                    break;
                refPerson = refPerson.nextPerson;
            }
        }
        
    }
    
    public void D(String p, int keyperson){ //  Delete Method
        
        Person p1 = new Person();   //  For marking the index (something was deleted before, could be someone on the next index, dont forget him/her)
        p1.PersonName = "deleted";
        p1.delete = true;
        
        try{
            while(newPersonHash[keyperson].PersonName.compareTo(p) != 0 || newPersonHash[keyperson].delete){
                keyperson++;
            }
        }catch(NullPointerException e){

        }
        
        if(newPersonHash[keyperson] != null){
            refSong = newPersonHash[keyperson].nextSong;                            //  go person hash and take the first song of person
            newPersonHash[keyperson].nextSong = null;                               //  delete person from person hash and song hash
            newPersonHash[keyperson] = p1;
            int keysong = 0;
            while(refSong != null){
                for(int i = 0 ; i < refSong.SongName.length() ; i++){
                char c = refSong.SongName.charAt(i);
                keysong += (int) c; 
                keysong = keysong % modKey;
                }
                refPerson = newSongHash[keysong].nextPerson;
                if(refPerson.PersonName.compareTo(p) == 0){
                    newSongHash[keysong].nextPerson = refPerson.nextPerson;
                    if(newSongHash[keysong].nextPerson == null)
                        newSongHash[keysong] = null;
                }

                while(refPerson.nextPerson != null){
                    if(refPerson.nextPerson.PersonName.compareTo(p) == 0){
                        refPerson.nextPerson = refPerson.nextPerson.nextPerson;
                        break;
                    }
                    refPerson = refPerson.nextPerson;
                }
                if(refSong.nextSong == null)
                    break;          
                refSong = refSong.nextSong; 
                keysong = 0;
            }
        }
        else
            System.out.println(p +" is not in the list");
    }

    public void P(String p, int keyperson){ //  Print Method
        if(newPersonHash[keyperson] != null){   
            
            try{
                while(newPersonHash[keyperson].PersonName.compareTo(p) != 0 || newPersonHash[keyperson].delete){
                    keyperson++;
                }
            }catch(NullPointerException e){                         
                
            }
            
            System.out.println(p + " likes");
            try{
            if(newPersonHash[keyperson].nextSong != null){
                refSong = newPersonHash[keyperson].nextSong;                        // Go to the key and print the songs
                while(refSong != null){
                    System.out.println(refSong.SongName);
                    if(refSong.nextSong == null)
                        break;
                    refSong = refSong.nextSong;      
                } 
            }
            else
                System.out.println(p +" has no song");    
            }catch(java.lang.NullPointerException e){
                System.out.println(p +" has no song");
            }
            
        }
        else
            System.out.println(p +" is not in the list");
    }

    public void M(String p, int keyperson){ // Match Method
        
        try{
            while(newPersonHash[keyperson].PersonName.compareTo(p) != 0 || newPersonHash[keyperson].delete){
                keyperson++;
            }
        }catch(NullPointerException e){

        }
        
        if(newPersonHash[keyperson] != null){          
        int[] Marray = new int[10000];
        for(int i = 0 ; i < 10000 ; i++)
            Marray[i] = 0;
        int keySong = 0;
        int keyperson2 = 0;
        int NoS = 0;
        refSong = newPersonHash[keyperson].nextSong;
        
            System.out.println("Possible friend of " + p + ":");
        
        while(refSong != null){                                             // Take the songs one by one and match with other person. if a song matches, increment 1 the key index of the 
            NoS++;                                                          // counter array.
            for(int i = 0 ; i < refSong.SongName.length() ; i++){
                char c = refSong.SongName.charAt(i);
                keySong += (int) c; 
                keySong = keySong % modKey;
            }
            refPerson = newSongHash[keySong].nextPerson;
            keyperson2 = 0;
            while(refPerson != null){
                
                for(int i = 0 ; i < refPerson.PersonName.length() ; i++){
                    char c = refPerson.PersonName.charAt(i);
                    keyperson2 += (int) c; 
                    keyperson2 = keyperson2 % modKey;
                }

                if(keyperson2 != keyperson){
                    Marray[keyperson2] = Marray[keyperson2] + 1 ;
                }
                if(refPerson.nextPerson == null)
                    break;
                refPerson = refPerson.nextPerson;   
                keyperson2 = 0;
            }
            if(refSong.nextSong == null)
                break;
            refSong = refSong.nextSong; 
            keySong = 0;
            
            
        }
        
        for(int i = 0 ; i < refPerson.PersonName.length() ; i++){
                    char c = refPerson.PersonName.charAt(i);
                    keyperson2 += (int) c;
                    keyperson2 = keyperson2 % modKey;
                }
        
        for(int i = 0 ; i < 1000 ; i++){
            if(Marray[i] > 0 ){
                System.out.println(newPersonHash[i].PersonName + " " + (int)((float)((float)Marray[i]/(float)NoS) *100) + "% Match" + " (" + Marray[i] + " song out of " + NoS + ")" );
            }
        }
      }  
       else 
            System.out.println(p +" is not in the list");
    }
    
    public void R(String p, int keyperson){ // Recommend Method
        
        try{
            while(newPersonHash[keyperson].PersonName.compareTo(p) != 0 || newPersonHash[keyperson].delete){
                keyperson++;
                System.out.println("--");
            }
        }catch(NullPointerException e){

        }
        
        if(newPersonHash[keyperson] != null){
            int[] Marray = new int[10000];
            int rListSize = 0;
            int a = 0, b= 0;
            String[] rArray = new String[5];
            Song rList = new Song();
            Song refRList;
            for(int i = 0 ; i < 10000 ; i++)
                Marray[i] = 0;
            int keySong = 0;
            Song refSong2;
            int keyperson2 = 0;
            int NoS = 0;
            refSong = newPersonHash[keyperson].nextSong;

            System.out.println("For " + p + " :");
            
            while(refSong != null){
                NoS++;
                for(int i = 0 ; i < refSong.SongName.length() ; i++){
                    char c = refSong.SongName.charAt(i);
                    keySong += (int) c;
                    keySong = keySong % modKey;
                }
                refPerson = newSongHash[keySong].nextPerson;
                keyperson2 = 0;
                while(refPerson != null){
                    for(int i = 0 ; i < refPerson.PersonName.length() ; i++){
                        char c = refPerson.PersonName.charAt(i);
                        keyperson2 += (int) c;
                        keyperson2 = keyperson2 % modKey;
                    }

                    if(keyperson2 != keyperson){
                        Marray[keyperson2] = Marray[keyperson2] + 1 ;
                    }
                    if(refPerson.nextPerson == null)
                        break;
                    refPerson = refPerson.nextPerson;   
                    keyperson2 = 0;
                }
                if(refSong.nextSong == null)
                    break;
                refSong = refSong.nextSong; 
                keySong = 0;

            }
            
            int max;
            int maxIndex;
            while(true){                    // Take the counter array building by match method, take the biggest value and its key. Go to person who has this key and print songs.
                max = 0;                    
                maxIndex = 0;
                for(int i = 0 ; i < 10000 ; i++){
                    if(Marray[i] > max ){
                        max = Marray[i];
                        maxIndex = i;
                    }
                }
                if(max == 0)
                    break;
                Marray[maxIndex] = 0;

                refSong = newPersonHash[maxIndex].nextSong;
                refSong2 = newPersonHash[keyperson].nextSong;
                while(refSong != null){
                    a = 0;
                    while(refSong2 != null){
                        if(refSong2.SongName.compareTo(refSong.SongName) == 0)  
                            a= 1;

                        refSong2 = refSong2.nextSong;
                    }
                    refSong2 = newPersonHash[keyperson].nextSong;
                    if(a == 0){
                        if(rListSize == 0){
                            rArray[rListSize] = refSong.SongName;
                            rListSize++;
                        }
                        else{
                            b = 0;
                            for(int r = 0 ; r < rListSize ; r++)
                                if(refSong.SongName.compareTo(rArray[r]) == 0)
                                    b = 1;
                            
                            if(b == 0 & rListSize < 5){
                                rArray[rListSize] = refSong.SongName;
                                rListSize++;
                            }    
                            
                        }
                   
                    }
                    a = 0;
                    
                    refSong = refSong.nextSong;
                }
            }
            for(int r = 0 ; r < rListSize ; r++)
                System.out.println(rArray[r] + " ");                   
            
        }  
        else 
            System.out.println(p +" is not in the list");
    }
       
}
    
    
        
   
    
    

