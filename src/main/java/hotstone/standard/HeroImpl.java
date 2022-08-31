package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.Player;

public class HeroImpl implements Hero {
    private int mana;
    private int health;
    private String type;
    private Player owner;
    private String heroPower;

    public HeroImpl(String type,Player owner){
        this.health=GameConstants.HERO_MAX_HEALTH;
        this.type=type;
        this.owner=owner;
        mana=3;
        this.heroPower="Cute";
    }
    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Player getOwner() {
        return owner;
    }
    public void setMana(int mana){
        this.mana=mana;
    }
    public String getHeroPower(){
        return heroPower;
    }
}
