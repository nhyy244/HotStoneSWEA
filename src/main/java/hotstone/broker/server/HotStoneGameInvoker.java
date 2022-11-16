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

package hotstone.broker.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.framework.Status;

import javax.servlet.http.HttpServletResponse;

public class HotStoneGameInvoker implements Invoker {
  private Game game;
  private Gson gson;

  public HotStoneGameInvoker(Game servant) {
    this.game = servant;
    gson = new Gson();
  }

  @Override
  public String handleRequest(String request) {
    RequestObject requestObject = gson.fromJson(request, RequestObject.class);

    //used for getting parameters
    JsonArray arrayJson = JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

    ReplyObject reply;

    try {
      if (requestObject.getOperationName().equals(OperationNames.GAME_GET_TURN_NUMBER)) {
        //EXAMPLE ON HOW TO GET PARAMETERS FROM A METHOD REQUEST.
        //Parameters from the request EXAMPLE. Do nothing if method has no parameters.
        //String parameter = gson.fromJson(arrayJson.get(0),String.class);

        //prepare reply answer
        int turnNumber = game.getTurnNumber();

        //marshall reply answer.
        reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(turnNumber));
        return gson.toJson(reply);
      }
      if(requestObject.getOperationName().equals(OperationNames.GAME_GET_PLAYER_IN_TURN)){

        Player player = game.getPlayerInTurn();
        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(player));
        return gson.toJson(reply);
      }
      if (requestObject.getOperationName().equals(OperationNames.GAME_GET_WINNER)) {
        Player winner = game.getWinner();
        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(winner));
        return gson.toJson(reply);
      }
      if (requestObject.getOperationName().equals(OperationNames.GAME_GET_DECK_SIZE)) {
        int deckSize = game.getDeckSize(gson.fromJson(arrayJson.get(0),Player.class));
        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(deckSize));
        return gson.toJson(reply);
      }
      if (requestObject.getOperationName().equals(OperationNames.GAME_GET_HAND_SIZE)) {
        int handSize = game.getHandSize(gson.fromJson(arrayJson.get(0),Player.class));
        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(handSize));
        return gson.toJson(reply);
      }
      if (requestObject.getOperationName().equals(OperationNames.GAME_GET_FIELD_SIZE)) {
        int fieldSize = game.getFieldSize(gson.fromJson(arrayJson.get(0),Player.class));
        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(fieldSize));
        return gson.toJson(reply);
      }
      if (requestObject.getOperationName().equals(OperationNames.GAME_END_OF_TURN)) {
        game.endTurn();
        reply = new ReplyObject(HttpServletResponse.SC_CREATED,"END TURN");
        return gson.toJson(reply);
      }
      if (requestObject.getOperationName().equals(OperationNames.GAME_USE_POWER)) {
        Status usePower = game.usePower(gson.fromJson(arrayJson.get(0),Player.class));
        reply = new ReplyObject(HttpServletResponse.SC_CREATED,gson.toJson(usePower));
        return gson.toJson(reply);
      }
    } catch(Exception ignored) {

    }

    return null;
  }
}
