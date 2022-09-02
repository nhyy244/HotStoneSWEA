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

    private ArrayList<Card> field;
    private ArrayList<Card> hand;
    private ArrayList<Card> deck;

    public HeroImpl(String type,Player owner){
        this.health=GameConstants.HERO_MAX_HEALTH;
        this.type=type;
        this.owner=owner;
        mana=3;
        this.heroPower="Cute";
        field = new ArrayList<>();
        hand = new ArrayList<>();
        deck= new ArrayList<>();
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
    public ArrayList<Card> getField(){
        return field;
    }
    public void updateField(Card c ){
        field.add(0,c);
    }
    public ArrayList<Card> getHand(){
        return hand;
    }
    public void setHand(ArrayList<Card> hand){
        this.hand=hand;
    }
    public void updateHand(Card c){
        hand.add(0,c);
    }
    public ArrayList<Card> getDeck(){
        return deck;
    }
    public void setDeck(ArrayList<Card> deck){
        this.deck=deck;
    }
    public void updateDeck(Card c ){
        deck.add(c);
    }

}
