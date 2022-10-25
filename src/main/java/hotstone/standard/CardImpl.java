package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.MutableCard;
import hotstone.framework.Player;

public class CardImpl implements Card,MutableCard {
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
    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setActive(boolean active) {
        this.active=active;
    }

    @Override
    public void setAttack(int attack) {
        this.attack=attack;
    }

    @Override
    public void setHealth(int health) {
        this.health=health;
    }

    @Override
    public void setMana(int mana) {
        this.manaCost=mana;
    }
}
