package com.rpggame.entities;

import com.rpggame.items.*;
import com.rpggame.mechanics.strategies.*;
import java.util.HashMap;
import java.util.Map;

public class EnemyFactory {

    private static Map<String, GameCharacter> prototypes = new HashMap<>();

    static {
        
        GameCharacter goblin = new GameCharacter("Hain Goblin", 40, 8, 2, 0, 50);
        goblin.equipWeapon(new RustyDagger(), true);
        prototypes.put("Goblin", goblin);

     
        GameCharacter orc = new GameCharacter("Savasci Ork", 90, 14, 8, 0, 80);
        orc.equipWeapon(new IronSword(), true);
        prototypes.put("Orc", orc);

        
        GameCharacter witch = new GameCharacter("Orman Cadisi", 50, 10, 2, 100, 40);
        witch.equipWeapon(new MagicWand(), true);
        prototypes.put("Witch", witch);


        GameCharacter slime = new GameCharacter("Yesil Slime", 30, 5, 0, 0, 100);
        slime.equipWeapon(new RustyDagger(), true);
        prototypes.put("Slime", slime);

       
        GameCharacter dragon = new GameCharacter("Kizil Ejderha", 300, 25, 15, 200, 100);
        dragon.equipWeapon(new Item("Ejder Nefesi", 10, Strategies.FIREBALL) {}, true);
        prototypes.put("Dragon", dragon);
       
        GameCharacter wolf = new GameCharacter("Vahsi Kurt", 50, 12, 3, 0, 100);
        wolf.equipWeapon(new WolfVampireAdapter(), true);
        prototypes.put("Wolf", wolf);

       
        GameCharacter vampire = new GameCharacter("Vampir Çırak", 60, 12, 3, 100, 70);
      
        vampire.equipWeapon(new WolfVampireAdapter(), true); 
        prototypes.put("Vampire", vampire);
    }

    public static GameCharacter getEnemy(String type) {
        if (prototypes.containsKey(type)) {
            return prototypes.get(type).clone();
        } else {
            return prototypes.get("Goblin").clone();
        }
    }
}