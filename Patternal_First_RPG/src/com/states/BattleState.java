package com.states;

import com.rpggame.core.AudioManager;
import com.rpggame.core.GameEnums;
import com.rpggame.core.GameManager;
import com.rpggame.entities.EnemyFactory;
import com.rpggame.entities.GameCharacter;
import java.util.Random;

public class BattleState extends LocationState {

    private String biome;
    private GameCharacter enemy;
    private GameCharacter player;
    private Random random;

    public BattleState(String biome) {
        this.biome = biome;
        this.random = new Random();
    }

    @Override
    public void onEnter(GameManager game) {
        AudioManager.getInstance().playMusic(GameEnums.MusicType.BATTLE);
        this.player = game.getPlayer();
        
        spawnEnemy();

        game.printLine("\n==========================================");
        game.printLine("⚔️  DİKKAT! " + enemy.getName() + " SANA SALDIRDI!  ⚔️");
        game.printLine("==========================================");

       
        boolean isBattleOver = false;
        while (!isBattleOver) {
            printStatus(game);

            game.printLine("\nHAMLEN NEDİR?");
            game.printLine("[1] Normal Saldir");
            game.printLine("[2] Ozel Saldir (" + player.getWeaponName() + ")");
            game.printLine("[3] Dua Et (" + player.getReligionName() + ")");
            game.printLine("[4] Kacmaya Calis");

            int choice = game.getSafeIntInput("Secim (1-4): ", 1, 4);

            if (choice == 1) player.performNormalAttack(enemy);
            else if (choice == 2) player.performSpecialAttack(enemy);
            else if (choice == 3) player.pray();
            else if (choice == 4) {
                if (random.nextBoolean()) {
                    game.printLine("💨 Tozu dumana katip kactin!");
                    game.changeLocation(new NavigationState());
                    return;
                } else game.printLine("🚫 Kacamazsin! Düşman önünü kesti.");
            }

            if (!enemy.isAlive()) {
                game.printLine("\n🏆 ZAFER! " + enemy.getName() + " yok edildi.");
                player.heal(20); 
                game.getSafeIntInput("[1] Zaferini kutla ve yola devam et: ", 1, 1);
                AudioManager.getInstance().playMusic(GameEnums.MusicType.NORMAL);
                game.changeLocation(new NavigationState());
                return;
            }

            game.printLine("\n🔻 " + enemy.getName() + " hamle yapiyor...");
            
            // Basit yapay zeka
            if (random.nextInt(100) < 25) enemy.performSpecialAttack(player);
            else enemy.performNormalAttack(player);

            if (!player.isAlive()) {
                game.printLine("\n💀 OLDU! " + enemy.getName() + " seni yendi...");
                System.exit(0);
            }
        }
    }

    private void spawnEnemy() {
        // MapSystem'den gelen isimlere göre düşman seçimi
        
        if (biome.contains("Ejder")) {
            enemy = EnemyFactory.getEnemy("Dragon");
        } 
        else if (biome.contains("Mezarlik")) { // HARİTADA 'G' İŞARETİ
            int roll = random.nextInt(100);
            
            // %20 Ejderha, %40 Vampir, %40 Cadı
            if (roll < 20) enemy = EnemyFactory.getEnemy("Dragon");
            else if (roll < 60) enemy = EnemyFactory.getEnemy("Vampire"); 
            else enemy = EnemyFactory.getEnemy("Witch");
        } 
        else if (biome.contains("Orman")) { // HARİTADA 'F' İŞARETİ
            int roll = random.nextInt(3);
            if (roll == 0) enemy = EnemyFactory.getEnemy("Goblin");
            else if (roll == 1) enemy = EnemyFactory.getEnemy("Witch");
            else enemy = EnemyFactory.getEnemy("Wolf");
        } 
        else {
            // Vahşi Doğa
            if (random.nextBoolean()) enemy = EnemyFactory.getEnemy("Slime");
            else enemy = EnemyFactory.getEnemy("Orc");
        }
    }

    private void printStatus(GameManager game) {
        game.printLine("------------------------------------------");
        game.printLine(String.format("SEN: %d HP  VS  %s: %d HP", 
            (int)player.getHealth(), enemy.getName(), (int)enemy.getHealth()));
        game.printLine("------------------------------------------");
    }
}