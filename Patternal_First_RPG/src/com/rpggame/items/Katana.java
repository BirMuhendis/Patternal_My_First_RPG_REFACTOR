package com.rpggame.items;

import com.rpggame.mechanics.strategies.Strategies;

public class Katana extends Item { 
    public Katana() { 
        super("Katana", 15, Strategies.KENJUTSU); 
    }
}