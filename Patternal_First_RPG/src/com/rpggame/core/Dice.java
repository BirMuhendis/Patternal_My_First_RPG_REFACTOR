package com.rpggame.core;
import java.util.Random;

public class Dice {
    private static final Random rn = new Random();

    public static int rollD20() { return rn.nextInt(20) + 1; }
    public static int rollD6() { return rn.nextInt(6) + 1; }

    public static double calculateDamage(double baseAttack, String attackerName) {
        int d20 = rollD20();
        int d6 = rollD6();
        double multiplier = 0;
        String message = "";

        if (d20 == 20) { multiplier = 2.0; message = "KRİTİK (NAT 20)!"; } 
        else if (d20 >= 15) { multiplier = 1.2; message = "Güçlü"; } 
        else if (d20 >= 10) { multiplier = 1.0; message = "İsabetli"; } 
        else if (d20 > 1) { multiplier = 0.5; message = "Sıyırma"; } 
        else { multiplier = 0.0; message = "ISKA!"; }

        if (multiplier > 0) {
            multiplier += (d6 * 0.05); // D6 bonusu
        }

        double finalDamage = baseAttack * multiplier;
        System.out.println(String.format("%s Zar: [D20:%d] [D6:%d] -> %s (x%.2f)", attackerName, d20, d6, message, multiplier));
        return finalDamage;
    }
}