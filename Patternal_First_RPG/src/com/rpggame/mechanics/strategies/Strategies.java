package com.rpggame.mechanics.strategies;

import com.rpggame.core.Dice;
import com.rpggame.entities.GameCharacter;

public class Strategies {
    
    // Strateji Nesneleri (Singleton gibi tek örnekler)
    public static final AttackStrategy NORMAL = new NormalAttack();
    public static final AttackStrategy KENJUTSU = new KenjutsuStrategy();
    public static final AttackStrategy FIREBALL = new FireballStrategy();
    public static final AttackStrategy BOW = new BowStrategy();
    public static final AttackStrategy LIFESTEAL = new LifeStealStrategy(); 

    // 1. NORMAL SALDIRI
    private static class NormalAttack implements AttackStrategy {
        @Override
        public void attack(GameCharacter attacker, GameCharacter target) {
            double rawDmg = Dice.calculateDamage(attacker.getAttack(), attacker.getName());
            // Basit bir zırh formülü: Defansın %20'si hasarı engeller
            double finalDmg = Math.max(0, rawDmg - (target.getDefense() * 0.2));
            
            if (rawDmg <= 0) System.out.println("   -> Hasar yok!");
            else {
                System.out.println("   -> Vurulan Hasar: " + (int)finalDmg);
                target.takeDamage(finalDmg);
            }
        }
    }

    // 2. KENJUTSU (Samurai)
    private static class KenjutsuStrategy implements AttackStrategy {
        @Override
        public void attack(GameCharacter attacker, GameCharacter target) {
            if (attacker.getStamina() >= 35) {
                System.out.println(attacker.getName() + " KENJUTSU teknigini uyguluyor!");
                attacker.reduceStamina(35);
                // 3 Hızlı vuruş
                for (int i = 1; i <= 3; i++) {
                    if (!target.isAlive()) break;
                    System.out.print("  " + i + ". Kesik: ");
                    // Her vuruş normalden biraz daha az hasar verir ama 3 kere vurur
                    double dmg = Dice.calculateDamage(attacker.getAttack() * 0.8, attacker.getName());
                    if (dmg > 0) target.takeDamage(dmg);
                }
            } else {
                System.out.println("Yorgun! Normal vuruyor.");
                Strategies.NORMAL.attack(attacker, target);
            }
        }
    }

    // 3. FIREBALL (Wizard)
    private static class FireballStrategy implements AttackStrategy {
        @Override
        public void attack(GameCharacter attacker, GameCharacter target) {
            if (attacker.getMana() >= 20) {
                System.out.println(attacker.getName() + " ATES TOPU atiyor!");
                attacker.reduceMana(20);
                
                int d20 = Dice.rollD20() + 2; // Büyü bonusu
                if(d20 > 20) d20 = 20;
                
                double multiplier = (d20 == 20) ? 2.5 : (d20 >= 10 ? 1.5 : 0.5);
                double dmg = attacker.getAttack() * 1.5 * multiplier; 
                
                System.out.println("Patlama! [Zar: " + d20 + "] -> " + (int)dmg + " Hasar!");
                target.takeDamage(dmg);
            } else {
                System.out.println("Mana bitti! Asa vurusu.");
                Strategies.NORMAL.attack(attacker, target);
            }
        }
    }
    
    // 4. BOW (Archer)
    private static class BowStrategy implements AttackStrategy {
        @Override
        public void attack(GameCharacter attacker, GameCharacter target) {
            System.out.println(attacker.getName() + " nisan aliyor...");
            int d20 = Dice.rollD20();
            // Okçu uzaktan vurduğu için kritik şansı daha yüksek (19-20)
            // Ama düşük atarsa (1-7) ıska şansı da var
            double multiplier = (d20 >= 19) ? 2.5 : (d20 >= 8 ? 1.0 : 0.0);
            
            if(d20 >= 19) System.out.println("KRITIK VURUS (Headshot)!");
            else if(d20 < 8) System.out.println("Iska!");
            
            double finalDmg = attacker.getAttack() * multiplier;
            if(finalDmg > 0) target.takeDamage(finalDmg);
        }
    }

    // 5. LIFESTEAL (Vampire & WolfAdapter)
    private static class LifeStealStrategy implements AttackStrategy {
        @Override
        public void attack(GameCharacter attacker, GameCharacter target) {
            System.out.println(attacker.getName() + " kanini emmek icin saldiriyor!");
            double dmg = Dice.calculateDamage(attacker.getAttack(), attacker.getName());
            
            if (dmg > 0) {
                target.takeDamage(dmg);
                // Verilen hasarın ATILAN ZAR KATSAYISI kadar iyileş
                double healAmount = dmg * Dice.rollD6() * 0.25;
                attacker.heal(healAmount); 
                System.out.println("   -> " + (int)healAmount + " can emildi (Sifa)!");
            } else {
                System.out.println("   -> Isiramaz! Zirh cok kalin.");
            }
        }
    }
}