package hotstone.framework;

public interface MutableCard extends Card {

    void setActive(boolean active);

    void setAttack(int attack);

    void setHealth(int health);

    void setMana(int mana);
}
