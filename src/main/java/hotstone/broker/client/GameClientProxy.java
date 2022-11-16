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

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.*;
import hotstone.observer.GameObserver;
import hotstone.standard.StandardHotStoneGame;

/** Template/starter code for your ClientProxy of Game.
 */
public class GameClientProxy implements Game, ClientProxy {

  private final static String GAME_OBJECTID="singleton";
  private final Requestor requestor;
  public GameClientProxy(Requestor requestor) {
    this.requestor=requestor;
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
    return null;
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
    return null;
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
    return null;
  }

  @Override
  public int getHandSize(Player who) {
    return requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_GET_HAND_SIZE,Integer.class,who);
  }

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
    return requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_GET_FIELD_SIZE,Integer.class,who);
  }

  @Override
  public void endTurn() {
    requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_END_OF_TURN,null);
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
    return requestor.sendRequestAndAwaitReply(GAME_OBJECTID,OperationNames.GAME_USE_POWER,Status.class,who);
  }

  @Override
  public void addObserver(GameObserver observer) {

  }
}
