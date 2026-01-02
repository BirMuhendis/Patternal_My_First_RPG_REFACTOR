package com.rpggame.core;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class JavaSoundDriver implements AudioDriverInterface {
    private Clip currentClip;

    @Override
    public void play(String filePath) {
        stop(); // Yeni bir ses çalmadan önce eskisi varsa durdur

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
            System.out.println("Ses Sürücüsü Hatasi: " + e.getMessage());
        }
    }

    @Override
    public void stop() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
        }
    }
   
    @Override
    public void setVolume(double volume) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVolume'");
    }

    @Override
    public void mute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mute'");
    }

    @Override
    public void unmute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unmute'");
    }

    @Override
    public boolean isPlaying() {
        boolean key = false;
        if(currentClip != null && currentClip.isRunning()){
            key=true;
            System.out.println("It is playing...");
        }
        else{
            key = false;
            System.out.println("It isn't playing...");
        }
        return key;
    }
}