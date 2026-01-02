package com.rpggame.mechanics.strategies;
import com.rpggame.entities.GameCharacter;

public interface AttackStrategy {
    void attack(GameCharacter attacker, GameCharacter target);
}