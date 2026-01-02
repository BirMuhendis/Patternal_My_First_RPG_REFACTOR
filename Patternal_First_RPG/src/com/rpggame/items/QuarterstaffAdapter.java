package com.rpggame.items;

import com.rpggame.mechanics.strategies.Strategies;

public class QuarterstaffAdapter extends Item {
    private MagicWand magicWand; 

    public QuarterstaffAdapter() {
        super("Buyulu Sopa (Quarterstaff)", 0, Strategies.FIREBALL);
        this.magicWand = new MagicWand();
    }

    @Override
    public double getBonusAttack() {
        return 3 + magicWand.getBonusAttack();
    }
}