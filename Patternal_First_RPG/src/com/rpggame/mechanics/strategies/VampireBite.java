package com.rpggame.mechanics.strategies;
import com.rpggame.items.Item;

public class VampireBite extends Item { 
    public VampireBite() { 
        // Hasar: 5, Özellik: Can Çalma
        super("Vampir Isirigi", 5, Strategies.LIFESTEAL); 
    }
}