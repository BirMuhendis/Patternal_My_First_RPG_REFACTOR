package com.states;

import com.rpggame.core.GameManager;
import com.rpggame.core.MapSystem;

public class NavigationState extends LocationState {

    @Override
    public void onEnter(GameManager game) {
        MapSystem.getInstance().printMap(); 

       
        game.printLine("\n--- YONLENDIRME ---");
        game.printLine("[W] Kuzey  [S] Guney");
        game.printLine("[A] Bati   [D] Dogu");
        game.printLine("[R] Haritayi Yenile");
        game.printLine("[I] Envanter");

        game.print("Komutun (Harf + Enter): ");
        
        String input = game.readString().toUpperCase(); 
        
        char command = (input.length() > 0) ? input.charAt(0) : ' ';

        switch (command) {
            case 'W': movePlayer(game, 'W'); break;
            case 'S': movePlayer(game, 'S'); break;
            case 'A': movePlayer(game, 'A'); break;
            case 'D': movePlayer(game, 'D'); break;
            case 'R': 
                game.changeLocation(new NavigationState()); 
                break;
            case 'I': 
                game.printLine("Cantan bos...");
                game.readString(); // Bekletmek için
                game.changeLocation(new NavigationState());
                break;
            default:
                game.printLine("Gecersiz komut! Sadece W, A, S, D, R veya I kullan.");
                game.changeLocation(new NavigationState());
                break;
        }
    }

    private void movePlayer(GameManager game, char direction) {
        String locationName = MapSystem.getInstance().move(direction);
    
        if (locationName.equals("BLOCKED")) {
            game.changeLocation(new NavigationState());
        } 
        else {
            game.printLine("Bolge: " + locationName);
            
            if (!locationName.contains("Guvenli Sehir") && !locationName.contains(".")) {
                 if (new java.util.Random().nextInt(100) < 50) {
                     game.changeLocation(new BattleState(locationName));
                     return;
                 }
            }
            
            game.changeLocation(new NavigationState());
        }
    }
}