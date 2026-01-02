package com.rpggame.religion;

import com.rpggame.entities.GameCharacter;

// Başına 'public' ekledik
public interface Religion {
    void applyPassiveBuff(GameCharacter character);
    void useCombatAbility(GameCharacter character);
    String getName();
}