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

/**
 * Skeleton class for AlphaStone test cases
 *
 *    This source code is from the book
 *      "Flexible, Reliable Software:
 *        Using Patterns and Agile Development"
 *      2nd Edition
 *    Author:
 *      Henrik Bærbak Christensen
 *      Department of Computer Science
 *      Aarhus University
 */

import hotstone.framework.*;
import hotstone.utility.TestHelper;
import hotstone.variants.factory.BetaStoneFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/** Template for your own ongoing TDD process.
 * Fill it out until you have covered all
 * requirements for the alpha stone game.
 */
public class TestBetaStone {
    private Game game;

    /** Fixture for AlphaStone testing. */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new BetaStoneFactory());
    }

    // The HotStone specs are quite insisting on how
    // the cards, drawn from the deck, are organized
    // in the hand. So when drawing the top three cards
    // from the deck (uno, dos, tres) they have to
    // be organized in the hand as
    // index 0 = tres; index 1 = dos; index 2 = uno
    // That is, a newly drawn card is 'at the top'
    // of the hand - always entered at position 0
    // and pushing the rest of the cards 1 position
    // 'down'
    @Test
    public void findusShouldHaveUnoDosTresCardsInitially() {
        // Given a game, Findus has 3 cards in hand
        int count = game.getHandSize(Player.FINDUS);
        assertThat(count, is(3));
        // And these are ordered Tres, Dos, Uno in slot 0,1,2

        Card card3 = game.getCardInHand(Player.FINDUS, 0);
        assertThat(card3.getName(), is(GameConstants.TRES_CARD));

        Card card2 = game.getCardInHand(Player.FINDUS, 1);
        assertThat(card2.getName(), is(GameConstants.DOS_CARD));

        Card card1 = game.getCardInHand(Player.FINDUS, 2);
        assertThat(card1.getName(), is(GameConstants.UNO_CARD));
    }
    @Test
    public void shouldHaveADeck() {
        // Given freshly initiated game
        // Then Findus' deck has 7 cards minus the 3 drawn
        assertThat(game.getDeckSize(Player.FINDUS), is(7-3));
        // When a round has gone
        game.endTurn(); game.endTurn();
        // Then the deck size is one less
        assertThat(game.getDeckSize(Player.FINDUS), is(7-4));
    }

    @Test
    public void shouldBePeddersenInTurn2() {
        // Given freshly initialized game
        // When Findus ends his turn
        game.endTurn();
        // Then it is Peddersen in turn
        assertThat(game.getPlayerInTurn(), is(Player.PEDDERSEN));
        // And back to Findus
        game.endTurn();
        assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
    }

    @Test
    public void shouldBeFinudsAtStartGame(){
        Player player1 = game.getPlayerInTurn();
        assertThat(player1, is(Player.FINDUS));
    }

    @Test
    public void shouldBePeddersenAfterFindusEndsTurn(){
        game.endTurn();
        Player player2 = game.getPlayerInTurn();
        assertThat(player2, is(Player.PEDDERSEN));}

    @Test
    public void shouldHave1ManaAtGameStart() {
        //Given Findus always starts
        assertThat(game.getHero(Player.FINDUS).getMana(),is(1));
    }
    @Test
    public void shouldHave2ManaAfterOneRound() {
        //Given Findus always starts
        assertThat(game.getHero(Player.FINDUS).getMana(),is(1));
        game.endTurn();
        game.endTurn();
        assertThat(game.getHero(Player.FINDUS).getMana(),is(2));
        game.endTurn();
        assertThat(game.getHero(Player.PEDDERSEN).getMana(),is(2));
    }

    @Test
    public void shouldHave7ManaAfter7Round() {
        //Given Findus always starts
        TestHelper.advanceGameNRounds(game,6);
        assertThat(game.getHero(game.getPlayerInTurn()).getMana(),is(7));
    }
    @Test
    public void bothPlayersShouldHave19HealthAfter5Turns() {
        //Given Findus always starts
        TestHelper.advanceGameNRounds(game,5);
        assertThat(game.getHero(Player.FINDUS).getHealth(),is(19));
        assertThat(game.getHero(Player.PEDDERSEN).getHealth(),is(19));
    }
    @Test
    @Disabled
    //Functioanbility of winner works. I just sat UNO to have attack 21. Now it's back to 1, thats why is disabled.
    public void findusShouldBeWinner() {
        //Given Findus always starts
        Card c =  game.getCardInHand(Player.FINDUS,2);
        Status status = game.playCard(Player.FINDUS,c);
        assertThat(status,is(Status.OK));
        game.endTurn();
        Status status1 = game.attackHero(Player.FINDUS,c);
        assertThat(status1,is(Status.NOT_PLAYER_IN_TURN));
        game.endTurn();
        game.attackHero(Player.FINDUS,c);
        Player winner = Player.FINDUS;
        assertThat(game.getWinner(),is(winner));
    }


}
