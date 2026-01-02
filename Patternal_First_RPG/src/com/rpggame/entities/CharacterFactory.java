package com.rpggame.entities;

import com.rpggame.core.GameEnums;
import com.rpggame.items.*;

public class CharacterFactory {

    public static GameCharacter createPlayer(GameEnums.CharacterClass type, String name) {
        GameCharacter player = null;
        switch (type) {
            case WARRIOR:
                player = new GameCharacter(name, 120, 15, 10, 0, 100);
                player.equipWeapon(new IronSword());
                break;
            case ARCHER:
                player = new GameCharacter(name, 90, 12, 5, 20, 100);
                player.equipWeapon(new Bow());
                break;
            case WIZARD:
                player = new GameCharacter(name, 70, 5, 2, 100, 50);
                player.equipWeapon(new MagicWand());
                break;
            case SAMURAI:
                player = new GameCharacter(name, 100, 18, 5, 0, 100);
                player.equipWeapon(new Katana());
                break;
            case BATTLEMAGE:
                player = new GameCharacter(name, 100, 10, 5, 60, 60);
                player.equipWeapon(new QuarterstaffAdapter());
                break;
            default:
                throw new IllegalArgumentException("Gecersiz karakter tipi!");
        }
        return player;
    }
}