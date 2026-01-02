package com.rpggame.core;

import com.rpggame.entities.CharacterFactory;
import com.rpggame.entities.GameCharacter;
import com.rpggame.religion.Religions;
import com.states.NavigationState;
import java.util.Scanner;

public class GameEngineFacade {
    private GameManager gameManager;
    private AudioManager audioManager;
    private Scanner scanner;

    public GameEngineFacade() {
        // Alt sistemlerin başlatılması
        this.gameManager = GameManager.getInstance();
        this.audioManager = AudioManager.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        // 1. Müzik Sistemini Başlat
        audioManager.playMusic(GameEnums.MusicType.NORMAL);
        
        // 2. Arayüz Hoşgeldin Mesajı 
        gameManager.printLine("=== RPG OYUNU ===");
        
        // 3. Karakter Yaratma Süreci 
        initializePlayer();
        
        // 4. Oyunu Başlat
        gameManager.printLine("Oyun Yukleniyor...");
        gameManager.changeLocation(new NavigationState());
    }

    private void initializePlayer() {
        gameManager.print("Isim: ");
        String name = scanner.nextLine();

        gameManager.printLine("Sinif: 1.Warrior 2.Archer 3.Wizard 4.Samurai 5.BattleMage");
        int typeIndex = getSafeIntInput(1, 5);
        
        GameEnums.CharacterClass selectedClass = GameEnums.CharacterClass.values()[typeIndex - 1];
        
        GameCharacter player = CharacterFactory.createPlayer(selectedClass, name);
        gameManager.setPlayer(player);

        gameManager.printLine("Din: 1.ULU RÜSTEM HOCA DİNİ(Güç buff) 2.Koruyucu Alperen Dini(Defans buff) 3.Hamsi Uğur Dini(Şifa) 4.Deli Göktuğ Dini(Mana Buff)");
        int rel = getSafeIntInput(1, 4);
        
        if(rel==1) player.setReligion(new Religions.UluRustemHocaDini());
        else if(rel==2) player.setReligion(new Religions.KoruyucuAlperenDini());
        else if(rel==3) player.setReligion(new Religions.HamsiUgurDini());
        else player.setReligion(new Religions.DeliGoktugDini());

        gameManager.printLine("Karakter Hazir: " + player.getName());
    }

  
    private int getSafeIntInput(int min, int max) {
        int choice = -1;
        while (true) {
            gameManager.print("Seciminiz: ");
            try {
                String input = scanner.next();
                choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) break;
                gameManager.printLine("Lutfen " + min + "-" + max + " arasi giriniz.");
            } catch (NumberFormatException e) {
                gameManager.printLine("Hatali giris!");
            }
        }
        scanner.nextLine(); // Buffer temizle
        return choice;
    }
}