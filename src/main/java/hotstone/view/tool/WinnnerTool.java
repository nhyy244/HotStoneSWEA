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

package hotstone.view.tool;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.view.core.HotStoneDrawing;
import hotstone.view.figure.HeroFigure;
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** MiniDraw Tool to 'end the turn' */
public class WinnnerTool extends NullTool {
    protected final HotStoneDrawing model;
    private DrawingEditor editor;
    private Game game;
    private Player whoAmIPlaying;

    public WinnnerTool(DrawingEditor editor, Game game) {
        // Note - we need access to the specialized 'hotseat'
        // behaviour, to swap UI in the model, alas a hard
        // coupling, made by casting...
        model = (HotStoneDrawing) editor.drawing();
        this.game = game;
        this.editor = editor;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        // Find the hero
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        if (figureAtPosition instanceof HotStoneFigure) {
            HotStoneFigure hsf = (HotStoneFigure) figureAtPosition;
            if (hsf.getType() == HotStoneFigureType.WIN_BUTTON) {
                Player p = game.getWinner();
                if(p == Player.FINDUS){
                    editor.showStatus("Winner is FINDUS" );
                }
                editor.showStatus("Winner is PEDDERSEN");
            }
        }
    }
}
