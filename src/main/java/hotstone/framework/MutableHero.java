package hotstone.framework;

public interface MutableHero extends Hero {

   void setMana(int mana);
   void setHealth(int health);
   void setActive(boolean active);
   String getHeroPower();
}
