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

package hotstone.visualtestcase;

import hotstone.figuretestcase.doubles.FakeObjectGame;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.factory.AlphaGUIStoneFactory;
import hotstone.view.core.HotStoneDrawing;
import hotstone.view.core.HotStoneDrawingType;
import hotstone.view.core.HotStoneFactory;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** Visual tests of the ability of HotStoneDrawing to respond to
 * observer events notified by the Game instance - i.e. the Domain
 * to the UI flow of events.
 */
public class ShowUpdate {
  public static void main(String[] args) {
    Game game = new StandardHotStoneGame(new AlphaGUIStoneFactory());

    DrawingEditor editor =
      new MiniDrawApplication( "Click anywhere to progress in an update sequence...",
                               new HotStoneFactory(game, Player.FINDUS,
                                       HotStoneDrawingType.HOTSEAT_MODE) );
    editor.open();
    editor.setTool( new TriggerGameUpdateTool(editor, game) );
  }
}

class TriggerGameUpdateTool extends NullTool {
  private DrawingEditor editor;
  private Game game;
  private int count;

  public TriggerGameUpdateTool(DrawingEditor editor, Game game) {
    this.editor = editor;
    this.game = game;
    count = 0;
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    switch (count) {
      case 0: {
        editor.showStatus("Playing Findus Card # 0");
        Card c = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, c);
        break;
      }
      case 1: {
        editor.showStatus("Playing Findus Card # 1");
        Card c = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, c);
        break;
      }
      case 2: {
        editor.showStatus("Playing Findus Card # 2");
        Card c = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, c);
        break;
      }

      case 3: {
       // Card c = game.getCardInHand(Player.PEDDERSEN,1);
       // game.playCard(Player.PEDDERSEN,c);
        Card attacker = game.getCardInField(Player.FINDUS, 2);
        Card defender = game.getCardInField(Player.PEDDERSEN, 0);
        editor.showStatus("Attack/Findus with " + attacker.getName() + " on " + defender.getName()
                + "; Findus Card REMOVED; Peddersen's card Health reduced.");
        game.attackCard(Player.FINDUS, attacker, defender);
        break;
      }
      case 4:{
        game.usePower(Player.FINDUS);
        editor.showStatus("Findus Uses Hero Power");
        break;
      }
      case 5: {
        game.endTurn();
        // TODO: keep adding to this 'list' until all game mutator calls
        // have been tested and verified that the UI responds correctly.
        editor.showStatus("Peddersen Draws card.");
        break;
      }
      default: {
        editor.showStatus("No more events in the list...");
      }
    }
    count++;
  }
}
