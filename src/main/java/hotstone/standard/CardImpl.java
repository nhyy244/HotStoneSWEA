package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class CardImpl implements Card {
    private String name;
    private int manaCost;
    private int health;
    private int attack;
    private boolean isActive = false;
    private Player owner;

    public CardImpl(String name, int manaCost, int attack, int health){
        this.name=name;
        this.manaCost=manaCost;
        this.health=health;
        this.attack=attack;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getManaCost() {
        return manaCost;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive){
        this.isActive=isActive;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    public void setHealth(int health){
        this.health=health;
    }
}
