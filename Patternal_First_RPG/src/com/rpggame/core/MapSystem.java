package com.rpggame.core;

public class MapSystem {
    private static MapSystem instance;
    private String[][] worldMap;
    private int playerX = 3, playerY = 3; 
    private int mapSize = 7; 

    private MapSystem() {
        // Harita Tanımları:
        // M: Dağ (Mountain), G: Mezarlık (Graveyard)
        // K: Kale/Şehir (City), F: Orman (Forest), .: Boş (Wilderness)
        worldMap = new String[][]{
            {".", "M", "M", "M", ".", ".", "."},
            {".", "M", "G", "M", ".", "F", "."},
            {".", ".", ".", ".", ".", "F", "."},
            {".", ".", "K", ".", ".", ".", "."},
            {".", "F", ".", ".", ".", ".", "."},
            {".", "F", "F", ".", "G", ".", "."},
            {".", ".", ".", ".", ".", ".", "."}
        };
    }
    public static MapSystem getInstance() { if(instance==null) instance=new MapSystem(); return instance; }

    public void printMap() {
        System.out.println("\n--- HARITA ---");
        for(int i=0; i<mapSize; i++) {
            System.out.print("\t");
            for(int j=0; j<mapSize; j++) {
                if(i==playerY && j==playerX) System.out.print("[P] ");
                else {
                    String tile = worldMap[i][j];
                    if(tile.equals(".")) System.out.print(" .  ");
                    else System.out.print(" " + tile + "  ");
                }
            }
            System.out.println();
        }
        System.out.println("\t[M]: Dag [G]: Mezarlik [K]: Sehir [F]: Orman");
    }

    public String move(char dir) {
        int nx = playerX, ny = playerY;
        if(dir=='W') ny--; if(dir=='S') ny++; if(dir=='A') nx--; if(dir=='D') nx++;
        
        if(nx < 0 || nx >= mapSize || ny < 0 || ny >= mapSize) { 
            System.out.println("Dünyanın sonu!"); 
            return "BLOCKED"; 
        }
        playerX=nx; playerY=ny;
        
        // --- BURAYI TÜRKÇE İSİMLERLE GÜNCELLEDİK ---
        String tile = worldMap[ny][nx];
        if(tile.equals("M")) return "Ejder Daglari";
        if(tile.equals("G")) return "Lanetli Mezarlik";
        if(tile.equals("K")) return "Guvenli Sehir";
        if(tile.equals("F")) return "Karanlik Orman";
        return "Vahsi Doga"; // Boş arazi (.)
    }
}