/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package hotstone.standard;

import hotstone.framework.*;
import hotstone.variants.factory.HotStoneFactory;
import hotstone.variants.factory.ZetaStoneFactory;

import java.util.*;

/** This is the 'temporary test stub' in TDD
 * terms: the initial empty but compilable implementation
 * of the game interface.
 *
 * It already includes a bit of FAKE-IT code for the first
 * test case about hand management.
 *
 * Start solving the AlphaStone exercise by
 * following the TDD rythm: pick a one-step-test
 * from your test list, quickly add a test,
 * run it to see it fail, and then modify this
 * implementing class (and supporting classes)
 * to make your test case run. Refactor and repeat.
 *
 * While this is the implementation of Game for
 * the AlphaStone game, you will constantly
 * refactor it over the course of the exercises
 * to become the 'core implementation' which will
 * enable a lot of game variants. This is also
 * why it is not called 'AlphaGame'.
 */
public class StandardHotStoneGame implements Game {
  private HashMap<Player, List<Card>> deck;
  private HashMap<Player, HeroImpl> heroes;
  private HashMap<Player, List<Card>> hand;

  private HashMap<Player, List<Card>> field;
  private HashMap<Player,Integer> totalAttackOutput;

  private Player currentPlayerInTurn;

  private WinnerStrategy winnerStrategy;

  private ManaProductionStrategy manaProductionStrategy;
  private HeroGenerationStrategy heroGenerationStrategy;
  private EffectStrategy effectStrategy;
  private GenerateDeckStrategy generateDeckStrategy;
  private int turnNumber;
  private int roundNumber;
  private HotStoneFactory hotStoneFactory;


  public StandardHotStoneGame(HotStoneFactory hotStoneFactory){
    this.hotStoneFactory = hotStoneFactory;

    this.winnerStrategy= hotStoneFactory.createWinnerStrategy();
    this.manaProductionStrategy=hotStoneFactory.createManaProductionStrategy();
    this.heroGenerationStrategy=hotStoneFactory.createHeroGenerationStrategy();
    this.effectStrategy =hotStoneFactory.createEffectStrategy();
    this.generateDeckStrategy=hotStoneFactory.createGenerateDeckStrategy();


    generateGameStart();

  }
  private void generateGameStart(){
    currentPlayerInTurn = Player.FINDUS;

    deck = new HashMap<>();
    heroes = new HashMap<>();
    hand = new HashMap<>();
    field = new HashMap<>();
    totalAttackOutput= new HashMap<>();
    generateHeroes(Player.FINDUS);
    generateHeroes(Player.PEDDERSEN);
    generateDeck(Player.FINDUS,deck);
    generateDeck(Player.PEDDERSEN,deck);
    generateHand(Player.FINDUS);
    generateHand(Player.PEDDERSEN);
    generateEmptyField(Player.FINDUS);
    generateEmptyField(Player.PEDDERSEN);
    generateEmptyAttackOutput(Player.FINDUS);
    generateEmptyAttackOutput(Player.PEDDERSEN);




    ((HeroImpl)getHero(Player.FINDUS)).setActive(true);
    /*
     * START MANA FOR BOTH PLAYERS ! AT ROUND START =0*/
    manaProductionStrategy.manaProduction(Player.FINDUS,this);
    manaProductionStrategy.manaProduction(Player.PEDDERSEN,this);


  }
  private void generateDeck(Player who,HashMap<Player,List<Card>> deck){
    generateDeckStrategy.generateDeck(who,deck);
  }

  private void generateHeroes(Player who){
    heroGenerationStrategy.generateHeroes(who,heroes);
  }

  private void generateHand(Player who){
    List<Card> tempHand = new ArrayList<>();
    for(int i=0; i<3;i++){
      tempHand.add(0,deck.get(who).get(i));
    }

    deck.get(who).subList(0, 3).clear(); //removes the first 3 cards from the deck.

    hand.put(who,tempHand);
  }
  private void generateEmptyField(Player who){
    ArrayList<Card> field1 = new ArrayList<>();
    field.put(who,field1);
  }
  private void generateEmptyAttackOutput(Player who){
    totalAttackOutput.put(who,0);
  }
  public List<Card> getFieldList(Player who){
    return field.get(who);
  }



  @Override
  public Player getPlayerInTurn() {
    return currentPlayerInTurn;
  }
  @Override
  public Hero getHero(Player who) {
    return heroes.get(who);
  }

  @Override
  public Player getWinner() {
    return winnerStrategy.getWinner(this);
    //return null;
  }

  @Override
  public int getTurnNumber() {
    return roundNumber;
  }

  @Override
  public int getDeckSize(Player who) {
    return deck.get(who).size();

  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    return hand.get(who).get(indexInHand);
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
    return hand.get(who);
  }

  @Override
  public int getHandSize(Player who) {
    return hand.get(who).size();
  }

  @Override
  public Card getCardInField(Player who, int indexInField) {
    return field.get(who).get(indexInField);
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    return field.get(who);
  }

  @Override
  public int getFieldSize(Player who) {
    return field.get(who).size();
  }

  @Override
  public void endTurn() {
    turnNumber++;
    currentPlayerInTurn =(turnNumber %2 ==0)?Player.FINDUS:Player.PEDDERSEN; //computes playerInTurn
    if(turnNumber % 2 == 0){ //computes roundNumber 2TurnNumber=1roundNumber
      roundNumber++;
    }
    HeroImpl hero = heroes.get(currentPlayerInTurn);
    List<Card> deckHero = deck.get(currentPlayerInTurn);
    manaProductionStrategy.manaProduction(currentPlayerInTurn,this);

    hero.setActive(true);
    drawCard(hero,deckHero);
    setMinionOnFieldActive();
  }
  public void drawCard(HeroImpl h, List<? extends Card> d ){
    if(d.isEmpty()){
      h.setHealth(h.getHealth()-2);
    }
    else{
      hand.get(currentPlayerInTurn).add(0, d.get(0)); //adds card from deck to the hand.
      d.remove(0);
    }
  }
  public List<Card> getDeck(Player who){
    return deck.get(who);
  }
  private void setMinionOnFieldActive(){
    //sets minions on field active
    if (field.get(currentPlayerInTurn) != null) {
      for (Card c : field.get(currentPlayerInTurn)) {
        if (!(c.isActive())) {
          ((CardImpl) c).setActive(true);
        }
      }
    }
  }

  @Override
  public Status playCard(Player who, Card card) {
    HeroImpl h = (HeroImpl) getHero(who);

    if(!(who == getPlayerInTurn())){
      return Status.NOT_PLAYER_IN_TURN;
    }
    if(who != card.getOwner()){
      return Status.NOT_OWNER;
    }
    if(getHero(who).getMana()<card.getManaCost()){
      return Status.NOT_ENOUGH_MANA;
    }
    h.setMana(h.getMana()- card.getManaCost()); //updates mana  when card is played
    field.get(who).add(0,card); //updates field when card is played
    effectStrategy.applyCardEffects(this,who,card);
    hand.get(who).remove(card);

    return Status.OK;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    boolean attackOnOwnMinion = defendingCard.getOwner().equals(getPlayerInTurn()) && attackingCard.getOwner().equals(getPlayerInTurn());
    boolean ownerOfAttackCard = attackingCard.getOwner().equals(getPlayerInTurn());
    if(getPlayerInTurn() != playerAttacking){
      return Status.NOT_PLAYER_IN_TURN;
    }
    if(!(attackingCard.isActive())){
      return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
    }
    if(attackOnOwnMinion){
      return Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION;
    }
    if(!ownerOfAttackCard){
      return Status.NOT_OWNER;
    }
    winnerStrategy.setTotalAttackOutput(playerAttacking,attackingCard,totalAttackOutput);

    ((CardImpl)attackingCard).setHealth(attackingCard.getHealth()-defendingCard.getAttack());
    ((CardImpl)defendingCard).setHealth(defendingCard.getHealth()-attackingCard.getAttack());

    checkMinionsFor0Health(playerAttacking,attackingCard,defendingCard);


    return Status.OK;
  }

  private void checkMinionsFor0Health(Player playerAttacking, Card attackingCard, Card defendingCard){
    if(attackingCard.getHealth() <=0){
      field.get(playerAttacking).remove(attackingCard);
    }
    else{
      ((CardImpl) attackingCard).setActive(false);
    }
    if(defendingCard.getHealth() <=0){
      field.get(defendingCard.getOwner()).remove(defendingCard);
    }
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    if(getPlayerInTurn() != playerAttacking){
      return Status.NOT_PLAYER_IN_TURN;
    }
    if(!(attackingCard.isActive())){
      return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
    }
    if(!attackingCard.getOwner().equals(getPlayerInTurn())){
      return Status.NOT_OWNER;
    }

    Player opponentPlayer = Utility.computeOpponent(playerAttacking);
    HeroImpl opponentHero = heroes.get(opponentPlayer);
    winnerStrategy.setTotalAttackOutput(playerAttacking,attackingCard,totalAttackOutput);

    int opponentHeroHealthAfterAttacked = opponentHero.getHealth()-attackingCard.getAttack();
    opponentHero.setHealth(opponentHeroHealthAfterAttacked);
    ((CardImpl)attackingCard).setActive(false);

   return Status.OK;

  }

  public int getTotalAttackOutput(Player who){
    return totalAttackOutput.get(who);
  }

  @Override
  public Status usePower(Player who) {
    if (!who.equals(getPlayerInTurn())) {
      return Status.NOT_PLAYER_IN_TURN;
    }
    if (getHero(who).getMana() < 2) {
      return Status.NOT_ENOUGH_MANA;
    }
    if(!getHero(who).isActive()){
      return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
    }
    ((HeroImpl) getHero(who)).setMana(getHero(who).getMana() - GameConstants.HERO_POWER_COST);
    effectStrategy.usePower(who,this);
    ((HeroImpl) getHero(who)).setActive(false);

    //((HeroImpl))getHero(who).
    return Status.OK;
  }
}
