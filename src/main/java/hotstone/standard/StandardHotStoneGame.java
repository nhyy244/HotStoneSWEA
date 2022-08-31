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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

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
  private Card uno = new CardImpl(GameConstants.UNO_CARD,1,1,1);
  private Card dos = new CardImpl(GameConstants.DOS_CARD,2,2,2);
  private Card tres = new CardImpl(GameConstants.TRES_CARD,3,3,3);
  private Card cuatro = new CardImpl(GameConstants.CUATRO_CARD,2,3,1);
  private Card cinco = new CardImpl(GameConstants.CINCO_CARD,3,5,1);
  private Card seis = new CardImpl(GameConstants.SEIS_CARD,2,1,3);
  private Card siete = new CardImpl(GameConstants.SIETE_CARD,3,2,4);

  private ArrayList<Card> deck;
  private ArrayList<Card> player1Hand = new ArrayList<>();
  private ArrayList<Card> player2Hand = new ArrayList<>();
  private HashMap<Player,ArrayList<Card>> player1DeckMap;
  private HashMap<Player,ArrayList<Card>> player2DeckMap;

  private HashMap<Player,ArrayList<Card>> player1HandMap;
  private HashMap<Player,ArrayList<Card>> player2HandMap;
  private HeroImpl player1Hero;
  private HeroImpl player2Hero;


  public StandardHotStoneGame(){
    player1Hero = new HeroImpl(GameConstants.BABY_HERO_TYPE,Player.FINDUS);
    player2Hero = new HeroImpl(GameConstants.BABY_HERO_TYPE,Player.PEDDERSEN);
    deck = new ArrayList<>();
    Collections.addAll(deck,uno,dos,tres,cuatro,cinco,seis,siete);
    player1DeckMap = new HashMap<>();
    player1DeckMap.put(Player.FINDUS,deck);
    player2DeckMap=new HashMap<>();
    player2DeckMap.put(Player.PEDDERSEN,deck);

    for (int i=2;i>=0;i--){
      player1Hand.add(deck.get(i));
      player2Hand.add(deck.get(i));

    }
    player1HandMap = new HashMap<>();
    player1HandMap.put(Player.FINDUS,player1Hand);

    player2HandMap=new HashMap<>();
    player2HandMap.put(Player.PEDDERSEN,player2Hand);
  }


  public int roundNumber;
  @Override
  public Player getPlayerInTurn() {
    if(getTurnNumber() %2 ==0){
      return Player.FINDUS;
    }
    else{
      return Player.PEDDERSEN;
    }
  }

  @Override
  public Hero getHero(Player who) {
    Hero h = (who.equals(Player.FINDUS))? player1Hero:player2Hero;
    return h;
  }

  @Override
  public Player getWinner() { //not implemented in alphaCiv
    return null;
  }

  @Override
  public int getTurnNumber() {
    return roundNumber;
  }

  @Override
  public int getDeckSize(Player who) {
    if(who.equals(Player.FINDUS)){
      return player1DeckMap.get(who).size();
    }else {
      return player2DeckMap.get(who).size();
    }
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    if(who.equals(Player.FINDUS)){
      return player1HandMap.get(who).get(indexInHand);
    }
    return player2HandMap.get(who).get(indexInHand);
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
    return null;
  }

  @Override
  public int getHandSize(Player who) {
    int handSize = (who.equals(Player.FINDUS))? player1HandMap.get(who).size() : player2HandMap.get(who).size();
    return handSize;
  } // FAKE-IT

  @Override
  public Card getCardInField(Player who, int indexInField) {
    return null;
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    return null;
  }

  @Override
  public int getFieldSize(Player who) {
    return 0;
  }

  @Override
  public void endTurn() {
    roundNumber++;
    if(getPlayerInTurn().equals(Player.FINDUS)){
      player1Hero.setMana(3);
    }
    else{
      player2Hero.setMana(3);
    }
  }

  @Override
  public Status playCard(Player who, Card card) {
    return null;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    return null;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    return null;
  }

  @Override
  public Status usePower(Player who) {
    if (!who.equals(getPlayerInTurn())){
      return Status.NOT_PLAYER_IN_TURN;
    }
    if (who.equals(Player.FINDUS)){ // checks for playerInTurn
      int manaPlayer1 = player1Hero.getMana(); //gets player
      if(manaPlayer1<2){
        return Status.NOT_ENOUGH_MANA;
      }
      player1Hero.setMana(manaPlayer1-GameConstants.HERO_POWER_COST); //calculates mana after HeroPower used
      return Status.OK;
    }

    int manaPlayer2 = player2Hero.getMana();
    if(manaPlayer2<2){
      return Status.NOT_ENOUGH_MANA;
    }
    player2Hero.setMana(manaPlayer2-GameConstants.HERO_POWER_COST);
    return Status.OK;
  }
}
