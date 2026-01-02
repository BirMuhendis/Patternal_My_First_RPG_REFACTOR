package com.rpggame.mechanics.strategies;
import com.rpggame.entities.GameCharacter;
import com.rpggame.items.Item;


public class WolfVampireAdapter extends Item {
    
    private VampireBite vampireBite; // İçerideki Vampir özelliği

    public WolfVampireAdapter() {
        // İsim: Lanetli Kurt Dişi
        // Bonus Hasar: 12 (Kurdun kendi gücü)
        
        super("Lanetli Kurt Disi", 12, null);
        
        this.vampireBite = new VampireBite();

        // ADAPTER MANTIĞI: İki saldırıyı peş peşe yap
        this.strategy = new AttackStrategy() {
            @Override
            public void attack(GameCharacter attacker, GameCharacter target) {
                // 1. Önce Normal Pençe At
                System.out.println(attacker.getName() + " VAHSI PENCELERIYLE saldiriyor!");
                Strategies.NORMAL.attack(attacker, target);

                // 2. Sonra Isırıp Can Çal (VampireBite içindeki stratejiyi kullan)
                System.out.println("   ...ve ardindan boynuna yapisiyor!");
                vampireBite.getStrategy().attack(attacker, target);
            }
        };
    }
}