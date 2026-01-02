package com.rpggame.items;

import com.rpggame.mechanics.strategies.AttackStrategy;

public abstract class Item {
    protected String name;
    protected double bonusAttack;
    protected AttackStrategy strategy;

    public Item(String name, double bonusAttack, AttackStrategy strategy) {
        this.name = name;
        this.bonusAttack = bonusAttack;
        this.strategy = strategy;
    }

    public String getName() { return name; }
    public double getBonusAttack() { return bonusAttack; }
    public AttackStrategy getStrategy() { return strategy; }
}