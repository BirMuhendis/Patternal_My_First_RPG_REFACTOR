package com.rpggame.core;

import com.rpggame.entities.GameCharacter;
import com.states.LocationState;
import java.util.Scanner;

public class GameManager {
    private static GameManager instance;
    private GameCharacter player;
    private LocationState currentLocation;
    
    
    private Scanner scanner;

    private GameManager() {
        this.scanner = new Scanner(System.in);
    }
    
    public static GameManager getInstance() { 
        if(instance==null) instance=new GameManager(); 
        return instance; 
    }

 
    
    public void print(String message) {
        System.out.print(message);
    }

    public void printLine(String message) {
        System.out.println(message);
    }

    public String readString() {
        return scanner.next();
    }
    // -------------------------------------------

    public void setPlayer(GameCharacter player) { this.player = player; }
    public GameCharacter getPlayer() { return player; }
    
    public void changeLocation(LocationState newLocation) {
        this.currentLocation = newLocation;
        this.currentLocation.onEnter(this);
    }

    public int getSafeIntInput(String message, int min, int max) {
        int choice = -1;
        while (true) {
            this.print(message);
            try {
                String input = this.readString();
                choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) break;
                
                this.printLine("Lutfen " + min + "-" + max + " arasi bir sayi giriniz.");
            } catch (NumberFormatException e) {
                this.printLine("Hatali giris! Lutfen sayi giriniz.");
            }
        }
        return choice;
    }
}