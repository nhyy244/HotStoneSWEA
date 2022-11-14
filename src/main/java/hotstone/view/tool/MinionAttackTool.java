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

import hotstone.framework.*;
import hotstone.view.GfxConstants;
import hotstone.view.core.HotStoneDrawing;
import hotstone.view.figure.*;
import minidraw.framework.*;
import minidraw.standard.NullTool;

import java.awt.*;
import java.awt.event.MouseEvent;

/** Almost complete implementation of CardPlayTool: a MiniDraw tool
 * to play a card.
 *
 * Much of the behaviour in this tool revolves around being able to
 * move the card figure BACK in case the game tells us that the
 * card could NOT be played (status != Status.OK).
 */

public class MinionAttackTool extends NullTool {
    private DrawingEditor editor;
    private Game game;
    private HotStoneActorFigure draggedActor;
    private int lastX;
    private int lastY;
    private int orgX;
    private int orgY;
    private Player whoAmIPlaying;
    private Drawing model;

    public MinionAttackTool(DrawingEditor editor, Game game, Player whoAmIPlaying) {
        this.editor = editor;
        this.game = game;
        this.whoAmIPlaying = whoAmIPlaying;
        model = editor.drawing();
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        Drawing model = editor.drawing();
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        draggedActor = (HotStoneActorFigure) figureAtPosition;
        model.zOrder(draggedActor, ZOrder.TO_TOP);
        lastX = x;
        lastY = y;
        orgX = x;
        orgY = y;
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        // compute relative movement
        draggedActor.moveBy(x - lastX, y - lastY);
        // update last position
        lastX = x;
        lastY = y;
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        boolean isDraggingAnActor = draggedActor != null;
        boolean isHittingOField = y < GfxConstants.OPPONENT_FIELD_Y_POSITION;

        if (isDraggingAnActor) {
            Card attackingCard = draggedActor.getAssociatedCard();
            draggedActor.moveBy(orgX - x, orgY - y);
            Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
            if (figureAtPosition instanceof HotStoneFigure) {
                HotStoneFigure defendingActor = (HotStoneFigure) figureAtPosition;
                editor.showStatus(defendingActor.toString());

                if (defendingActor.getType() == HotStoneFigureType.MINION_FIGURE) {
                    HotStoneActorFigure defendingActorCard = (HotStoneActorFigure) defendingActor;
                    Card defendingCard = defendingActorCard.getAssociatedCard();
                    Status status = game.attackCard(whoAmIPlaying, attackingCard, defendingCard);
                    editor.showStatus("Attack Card on Minion. Result =" + status);
                }
                if (defendingActor.getType() == HotStoneFigureType.HERO_FIGURE) {
                    Status status = game.attackHero(whoAmIPlaying, attackingCard);

                    editor.showStatus("Attack Card on Hero. Result =" + status);
                }
            }
        } else {
            draggedActor.moveBy(orgX - x, orgY - y);
            draggedActor = null;
        }
    }
}

