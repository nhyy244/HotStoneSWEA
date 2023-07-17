/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.broker.client;

import com.google.gson.reflect.TypeToken;
import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.*;
import hotstone.observer.GameObserver;
import hotstone.standard.StandardHotStoneGame;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/** Template/starter code for your ClientProxy of Game.
 */
public class GameClientProxy implements Game, ClientProxy {

  private final static String GAME_OBJECTID="singleton";
  //private static String objectId;
  private final Requestor requestor;
  public GameClientProxy(Requestor requestor) {
    this.requestor=requestor;
    //this.objectId = objectId
  }

  @Override
  public int getTurnNumber() {
    return requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_GET_TURN_NUMBER,Integer.class);
  }

  @Override
  public Player getPlayerInTurn() {
    return requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_GET_PLAYER_IN_TURN,Player.class);
  }

  @Override
  public Hero getHero(Player who) {
    String id = requestor.sendRequestAndAwaitReply("none",OperationNames.GAME_GET_HERO,String.class,who);
    return new HeroClientProxy(requestor,id);
  }

  @Override
  public Player getWinner() {
    return requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_GET_WINNER,Player.class);
  }

  @Override
  public int getDeckSize(Player who) {
    return requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_GET_DECK_SIZE,Integer.class,who);
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    String id = requestor.sendRequestAndAwaitReply("none",OperationNames.GAME_GET_CARD_IN_HAND,String.class,who,indexInHand);
    return new CardClientProxy(requestor,id);
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
    List<CardClientProxy> cardClientProxyList = new ArrayList<>();
    Type collectionType = new TypeToken<List<String>>(){}.getType();
    List<String> theIDList = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_GET_HAND,collectionType,who);
    for(String id: theIDList){
      CardClientProxy c = new CardClientProxy(requestor,id);
      cardClientProxyList.add(c);
    }
    return cardClientProxyList;
  }

  @Override
  public int getHandSize(Player who) {
    return requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_GET_HAND_SIZE,Integer.class,who);
  }

  @Override
  public Card getCardInField(Player who, int indexInField) {
    String id = requestor.sendRequestAndAwaitReply("none",OperationNames.GAME_GET_CARD_IN_FIELD,String.class,who,indexInField);
    return new CardClientProxy(requestor,id);
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    List<CardClientProxy> cardClientProxyList = new ArrayList<>();
    Type collectionType = new TypeToken<List<String>>(){}.getType();
    List<String> theIDList = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_GET_FIELD,collectionType,who);
    for(String id: theIDList){
      CardClientProxy c = new CardClientProxy(requestor,id);
      cardClientProxyList.add(c);
    }
    return cardClientProxyList;
  }

  @Override
  public int getFieldSize(Player who) {
    return requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_GET_FIELD_SIZE,Integer.class,who);
  }

  @Override
  public void endTurn() {
    requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_END_OF_TURN,null);
  }

  @Override
  public Status playCard(Player who, Card card) {
    return requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_PLAY_CARD,Status.class,who,card.getID());
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    return requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_ATTACK_CARD,Status.class,playerAttacking,attackingCard.getID(),defendingCard.getID());
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    return requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_ATTACK_HERO,Status.class,playerAttacking,attackingCard.getID());
  }

  @Override
  public Status usePower(Player who) {
    return requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_USE_POWER,Status.class,who);
  }

  @Override
  public void addObserver(GameObserver observer) {

  }
}
