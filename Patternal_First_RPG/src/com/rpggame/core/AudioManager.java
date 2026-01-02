package com.rpggame.core;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioManager {
    private static AudioManager instance;
    
    private Clip currentClip;
    private GameEnums.MusicType currentMusicType = GameEnums.MusicType.NONE;

    private AudioManager() {
        
    }

    public static AudioManager getInstance() {
        if (instance == null) instance = new AudioManager();
        return instance;
    }

    public void playMusic(GameEnums.MusicType type) {
        if (this.currentMusicType == type) return;

        String filePath = "";
        switch (type) {
            case NORMAL: filePath = "resources\\bg3.wav"; break;
            case BATTLE: filePath = "resources\\skyrim.wav"; break;
            default: 
                stopMusic();
                return;
        }
        
        this.currentMusicType = type;
        playInternal(filePath); 
    }

    
    private void playInternal(String filePath) {
        stopMusic(); // Önce durdur

        try {
            File musicFile = new File(filePath);
            if (musicFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                currentClip = AudioSystem.getClip();
                currentClip.open(audioInput);
                currentClip.loop(Clip.LOOP_CONTINUOUSLY);
                currentClip.start();
            } else {
                System.out.println("UYARI: Ses dosyasi bulunamadi -> " + filePath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Ses Hatasi: " + e.getMessage());
        }
    }

    public void stopMusic() {
        this.currentMusicType = GameEnums.MusicType.NONE;
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
        }
    }
}