package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Hero;
import hotstone.framework.Player;

import java.util.ArrayList;

public class HeroImpl implements Hero {
    private int mana;
    private int health;
    private String type;
    private Player owner;
    private String heroPower;
    private boolean active;


    public HeroImpl(String type, Player owner,String heroPower) {
        this.health = GameConstants.HERO_MAX_HEALTH;
        this.type = type;
        this.owner = owner;
        //this.mana=0;
        this.heroPower = heroPower;
        this.active=false;
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
        return active;
    }
    /*
    public void setActive(boolean active){
        this.active=active;
    }*/

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setActive(boolean active){
        this.active=active;
    }

    public String getHeroPower() {
        return heroPower;
    }
}
