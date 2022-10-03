package hotstone.TestStubs;

import hotstone.framework.*;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;

import java.util.List;
import java.util.Random;

public class FixedHeroPowerStrategy implements HeroPowerStrategy {
    private Card c;
    public FixedHeroPowerStrategy(CardImpl c ){
        this.c= c;
    }
    @Override
    public void usePower(Player who, Game game) {
        HeroImpl h = (HeroImpl) game.getHero(who);
        String heroPower = h.getHeroPower();

        if(heroPower.equals("Redwine")){
            ((CardImpl) c).setAttack(c.getAttack()-2);
            System.out.println("Opp M: (0,-2)");
        }
        if(heroPower.equals("Pasta")){
            ((CardImpl) c).setAttack(c.getAttack()+2);
            System.out.println("M: (+2,0)");
        }
    }
}
