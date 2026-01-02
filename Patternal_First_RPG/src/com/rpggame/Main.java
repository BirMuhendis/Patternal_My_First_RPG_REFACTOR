package com.rpggame;

import com.rpggame.core.GameEngineFacade;

public class Main {
    public static void main(String[] args) {
        // FACADE KULLANIMI
        // Tüm sistem kurulumu ve akışı Facade içinde gizli.
        GameEngineFacade gameEngine = new GameEngineFacade();
        gameEngine.startGame();
    }
}