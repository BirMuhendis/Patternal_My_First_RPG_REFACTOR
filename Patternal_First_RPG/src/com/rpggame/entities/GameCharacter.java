package com.rpggame.entities;

import com.rpggame.items.Item;
import com.rpggame.mechanics.strategies.AttackStrategy;
import com.rpggame.mechanics.strategies.Strategies;
import com.rpggame.religion.Religion;

public class GameCharacter implements Cloneable {
    private String name;
    private double health;
    private double maxHealth;
    private double baseAttack;
    private double defense;
    private double mana;
    private double stamina;
    private Item weapon;
    private AttackStrategy specialStrategy;
    private Religion religion;

    public GameCharacter(String name, double health, double baseAttack, double defense, double mana, double stamina) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.baseAttack = baseAttack;
        this.defense = defense;
        this.mana = mana;
        this.stamina = stamina;
        this.specialStrategy = Strategies.NORMAL;
    }

    
    public GameCharacter(GameCharacter target) {
        if (target != null) {
            this.name = target.name;
            this.health = target.health;
            this.maxHealth = target.maxHealth;
            this.baseAttack = target.baseAttack;
            this.defense = target.defense;
            this.mana = target.mana;
            this.stamina = target.stamina;
            this.weapon = target.weapon; // Silah referansını kopyalar
            this.specialStrategy = target.specialStrategy;
            this.religion = target.religion;
        }
    }

   
    @Override
    public GameCharacter clone() {
      
       return new GameCharacter(this);
    }

    
    public void equipWeapon(Item weapon, boolean silent) {
        this.weapon = weapon;
        this.specialStrategy = weapon.getStrategy();
        if (!silent) {
            System.out.println(this.name + " eline [" + weapon.getName() + "] aldi.");
        }
    }

    public void equipWeapon(Item weapon) { equipWeapon(weapon, false); }
    public String getWeaponName() { return (weapon != null) ? weapon.getName() : "Yumruk"; }

    public void performNormalAttack(GameCharacter target) {
        Strategies.NORMAL.attack(this, target);
    }

    public void performSpecialAttack(GameCharacter target) {
        if (this.isAlive()) {
            this.specialStrategy.attack(this, target);
        }
    }

    public void pray() {
        if (religion != null) religion.useCombatAbility(this);
        else System.out.println("Bir inancin yok! Bosa dua ettin...");
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
        this.religion.applyPassiveBuff(this);
    }
    
    public String getReligionName() { return (religion != null) ? religion.getName() : "Inancsiz"; }

    public void takeDamage(double amount) {
        this.health -= amount;
        if (this.health < 0) this.health = 0;
        System.out.println("\t" + this.name + " Can: " + (int)this.health + "/" + (int)this.maxHealth);
    }

    public void heal(double amount) {
        this.health += amount;
        if(this.health > maxHealth) this.health = maxHealth;
        System.out.println("Can yenilendi: +" + (int)amount);
    }

    public void restoreEnergy() {
        this.mana = 100; this.stamina = 100;
        System.out.println("Enerji doldu!");
    }

    public void regenerate() {
        this.stamina = 100;
        this.health += 20;
        if(this.health > maxHealth) this.health = maxHealth;
        System.out.println(name + " dinlendi.");
    }

    public void modifyAttack(double multiplier) { this.baseAttack *= multiplier; }
    public void modifyDefense(double multiplier) { this.defense *= multiplier; }
    public void modifyHealth(double multiplier) { this.maxHealth *= multiplier; this.health = maxHealth; }
    public void modifyMana(double multiplier) { this.mana *= multiplier; }

    public boolean isAlive() { return this.health > 0; }
    public String getName() { return name; }
    public double getAttack() { return baseAttack + (weapon != null ? weapon.getBonusAttack() : 0); }
    public double getDefense() { return defense; }
    public double getMana() { return mana; }
    public double getStamina() { return stamina; }
    public double getHealth() { return health; }
    public void reduceMana(double amount) { this.mana -= amount; }
    public void reduceStamina(double amount) { this.stamina -= amount; }
}