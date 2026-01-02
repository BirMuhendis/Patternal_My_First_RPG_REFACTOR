package com.rpggame.core;

public interface AudioDriverInterface {
    void play(String filePath);
    void stop();
    void setVolume(double volume);
    void mute();
    void unmute();
    boolean isPlaying();
    
}