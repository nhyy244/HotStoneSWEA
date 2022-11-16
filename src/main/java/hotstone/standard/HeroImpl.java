package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Hero;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;

import java.util.ArrayList;

public class HeroImpl implements Hero, MutableHero {
    private int mana;
    private int health;
    private String type;
    private Player owner;
    private String heroPower;
    private boolean active;


    public HeroImpl(String type, Player owner,String heroPower) {
        this.health = 1;
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

    @Override
    public void setMana(int mana) {
        this.mana=mana;
    }

    @Override
    public void setHealth(int health) {
        this.health=health;
    }

    @Override
    public void setActive(boolean active) {
        this.active=active;
    }

    @Override
    public String getHeroPower() {
        return heroPower;
    }
}
