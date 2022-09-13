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
import hotstone.variants.FindusWinsAt4RoundsStrategy;
import hotstone.variants.ManaProductionAlphaStone;
import hotstone.variants.ManaProductionBetaStone;
import hotstone.variants.WinnerStrategyBetaStone;

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
  private Player currentPlayerInTurn;

  private WinnerStrategy winnerStrategy;
  private ManaProductionStrategy manaProductionStrategy;
  private int turnNumber;
  private int roundNumber;


  public StandardHotStoneGame(WinnerStrategy winnerStrategy,ManaProductionStrategy manaProductionStrategy){
    this.winnerStrategy= winnerStrategy;
    this.manaProductionStrategy=manaProductionStrategy;


    currentPlayerInTurn = Player.FINDUS;

    deck = new HashMap<>();
    heroes = new HashMap<>();
    hand = new HashMap<>();
    field = new HashMap<>();
    generateHeroes(Player.FINDUS);
    generateHeroes(Player.PEDDERSEN);
    generateDeck(Player.FINDUS);
    generateDeck(Player.PEDDERSEN);
    generateHand(Player.FINDUS);
    generateHand(Player.PEDDERSEN);
    generateEmptyField(Player.FINDUS);
    generateEmptyField(Player.PEDDERSEN);

    /*
    * START MANA FOR BOTH PLAYERS ! AT ROUND START =0*/
    manaProductionStrategy.manaProduction(Player.FINDUS,this);
    manaProductionStrategy.manaProduction(Player.PEDDERSEN,this);
  }
  private void generateDeck(Player who){

    ArrayList<Card> Deck1 = new ArrayList<>();
    Deck1.add(new CardImpl(GameConstants.UNO_CARD, 1, 21, 1, false, who));
    Deck1.add(new CardImpl(GameConstants.DOS_CARD, 2, 2, 2, false, who));
    Deck1.add(new CardImpl(GameConstants.TRES_CARD, 3, 3, 3, false, who));
    Deck1.add(new CardImpl(GameConstants.CUATRO_CARD, 2, 3, 1, false, who));
    Deck1.add(new CardImpl(GameConstants.CINCO_CARD, 3, 5, 1, false, who));
    Deck1.add(new CardImpl(GameConstants.SEIS_CARD, 2, 1, 3, false, who));
    Deck1.add(new CardImpl(GameConstants.SIETE_CARD, 3, 4, 2, false, who));
    deck.put(who, Deck1);
  }

  private void generateHeroes(Player who){
    HeroImpl tempHero;
    if(who == Player.FINDUS) {
      tempHero = new HeroImpl(GameConstants.BABY_HERO_TYPE,who);
    }else{
      tempHero = new HeroImpl(GameConstants.BABY_HERO_TYPE,who);
    }
    heroes.put(who, tempHero);
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
    if(turnNumber % 2 == 0){
      roundNumber++;
    }
    //System.out.println(getTurnNumber());
    if(getPlayerInTurn().equals(Player.FINDUS)) {
      HeroImpl findusHero = heroes.get(Player.FINDUS);
      List<Card> findusDeck = deck.get(Player.FINDUS);
      manaProductionStrategy.manaProduction(Player.FINDUS,this);
      if(findusDeck.isEmpty()){
        findusHero.setHealth(findusHero.getHealth()-2);
      }
      else{
        hand.get(Player.FINDUS).add(0, findusDeck.get(0)); //adds card from deck to the hand.
        findusDeck.remove(0);
      }

      //sets minions on field active
      if (field.get(Player.FINDUS) != null) {
        for (Card c : field.get(Player.FINDUS)) {
          if (!(c.isActive())) {
            ((CardImpl) c).setActive(true);
          }
        }
      }
    }
    else {
      HeroImpl peddersenHero = heroes.get(Player.PEDDERSEN);
      List<Card> peddersenDeck = deck.get(Player.PEDDERSEN);
      manaProductionStrategy.manaProduction(Player.PEDDERSEN,this);
      if(peddersenDeck.isEmpty()){
        peddersenHero.setHealth(peddersenHero.getHealth()-2);
      }
      else{
        hand.get(Player.PEDDERSEN).add(0, peddersenDeck.get(0)); //adds card from deck to the hand.
        peddersenDeck.remove(0);
      }


      //sets minions on field active
      if (field.get(Player.PEDDERSEN) != null) {
        for (Card c : field.get(Player.PEDDERSEN)) {
          if (!(c.isActive())) {
            ((CardImpl) c).setActive(true);
          }
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
    hand.get(who).remove(card);

    return Status.OK;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    if(getPlayerInTurn() != playerAttacking){
      return Status.NOT_PLAYER_IN_TURN;
    }

    if(!(attackingCard.isActive())){
      return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
    }
    if(defendingCard.getOwner().equals(getPlayerInTurn()) && attackingCard.getOwner().equals(getPlayerInTurn())){
      return Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION;
    }
    if(!attackingCard.getOwner().equals(getPlayerInTurn())){
      return Status.NOT_OWNER;
    }
    ((CardImpl)attackingCard).setHealth(attackingCard.getHealth()-defendingCard.getAttack());
    ((CardImpl)defendingCard).setHealth(defendingCard.getHealth()-attackingCard.getAttack());

    if(attackingCard.getHealth() <=0){
      field.get(playerAttacking).remove(attackingCard);
    }
    else{
      ((CardImpl) attackingCard).setActive(false);
    }
    if(defendingCard.getHealth() <=0){
      field.get(defendingCard.getOwner()).remove(defendingCard);
    }
    return Status.OK;
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

   if(playerAttacking == Player.FINDUS){
     heroes.get(Player.PEDDERSEN).setHealth(heroes.get(Player.PEDDERSEN).getHealth()-attackingCard.getAttack());
     ((CardImpl)attackingCard).setActive(false);
   }
   else{
     heroes.get(Player.FINDUS).setHealth(heroes.get(Player.FINDUS).getHealth()-attackingCard.getAttack());
     ((CardImpl)attackingCard).setActive(false);
   }
   return Status.OK;

  }

  @Override
  public Status usePower(Player who) {
    if (!who.equals(getPlayerInTurn())) {
      return Status.NOT_PLAYER_IN_TURN;
    }
    if (getHero(who).getMana() < 2) {
      return Status.NOT_ENOUGH_MANA;
    }
    ((HeroImpl) getHero(who)).setMana(getHero(who).getMana() - GameConstants.HERO_POWER_COST);
    //((HeroImpl))getHero(who).
    return Status.OK;
  }
}
