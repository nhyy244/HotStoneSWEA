package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class CardImpl implements Card {
    private String name;
    private int manaCost;
    private int health;
    private int attack;
    private boolean active;
    private Player owner;

    public CardImpl(String name, int manaCost, int attack, int health,boolean active,Player owner ){
        this.name=name;
        this.manaCost=manaCost;
        this.health=health;
        this.attack=attack;
        this.active=active;
        this.owner=owner;
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
        return active;
    }
    public void setActive(boolean isActive){
        this.active=isActive;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner){
        this.owner = owner;
    }

    public void setHealth(int health){
        this.health=health;
    }
}
