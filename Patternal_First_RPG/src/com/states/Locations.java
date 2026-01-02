package com.states;

import com.rpggame.core.GameManager;

public class Locations {

    public static LocationState getCity() {
        
        return new LocationBuilder()
                .setDescription("Guvenli Sehir Merkezindesin. Etraf kalabalik.")
                .addOption("Ormana Git", getForest())
                .addOption("Magaraya Git", getCave())
                .addOption("Navigasyon Moduna Don", new NavigationState()) 
                .addAction("Hana Gir (Dinlen)", () -> {
                    
                    GameManager.getInstance().printLine("Handa dinlendin. Canin yenilendi.");
                    GameManager.getInstance().getPlayer().regenerate();
                })
                .build();
    }

    public static LocationState getForest() {
        return new LocationBuilder()
                .setDescription("Karanlik Orman. Kus sesleri urkutucu.")
                .addOption("Sehre Don", getCity())
                .addAction("Etrafi Ara", () -> {
                   
                    GameManager.getInstance().printLine("Yerlerde mantar buldun!");
                })
                .build();
    }
    
    public static LocationState getCave() {
        return new LocationBuilder()
                .setDescription("Islak ve nemli bir magara. Yarasa sesleri geliyor.")
                .addOption("Sehre Don", getCity())
                .build();
    }

    public static LocationState getGrave() {
        return new LocationBuilder()
                .setDescription("Gün doğarken ardından tepelerin ölülerin fısıltısı etrafında esiyor. ")
                .addOption("Mezarlıktan kaçış", getCity())
                .build();
            }
}