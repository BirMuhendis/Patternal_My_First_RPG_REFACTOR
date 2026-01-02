package com.rpggame.religion;

import com.rpggame.entities.GameCharacter;

public class Religions {
    
    
    public static class UluRustemHocaDini implements Religion {
        public String getName() { return "ULU RÜSTEM HOCA DİNİ"; }
        public void applyPassiveBuff(GameCharacter c) { c.modifyAttack(1.2); }
        public void useCombatAbility(GameCharacter c) { 
            System.out.println("Guc Duasi!"); c.modifyAttack(1.5); 
        }
    }

    public static class KoruyucuAlperenDini implements Religion {
        public String getName() { return "Koruyucu Alperen Dini"; }
        public void applyPassiveBuff(GameCharacter c) { c.modifyDefense(1.2); }
        public void useCombatAbility(GameCharacter c) { 
            System.out.println("Koruma Duasi!"); c.heal(10); c.modifyDefense(1.5); 
        }
    }

    public static class HamsiUgurDini implements Religion {
        public String getName() { return "Hamsi Ugur Dini"; }
        public void applyPassiveBuff(GameCharacter c) { c.modifyHealth(1.2); }
        public void useCombatAbility(GameCharacter c) { 
            System.out.println("Sifa Duasi!"); c.heal(30); 
        }
    }

    public static class DeliGoktugDini implements Religion {
        public String getName() { return "Deli Göktuğ Dini"; }
        public void applyPassiveBuff(GameCharacter c) { c.modifyMana(1.2); }
        public void useCombatAbility(GameCharacter c) { 
            System.out.println("Enerji Duasi!"); c.restoreEnergy(); 
        }
    }
}