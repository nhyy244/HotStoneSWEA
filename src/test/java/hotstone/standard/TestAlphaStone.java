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
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/** Template for your own ongoing TDD process.
 * Fill it out until you have covered all
 * requirements for the alpha stone game.
 */
public class TestAlphaStone {
  private Game game;

  /** Fixture for AlphaStone testing. */
  @BeforeEach
  public void setUp() {
    game = new StandardHotStoneGame();
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
    assertThat(player2, is(Player.PEDDERSEN));
  }
 @Test
 public void cardDosShouldHaveAttributes222(){
    Card dos = game.getCardInHand(Player.FINDUS,1);
    int dosMana = dos.getManaCost();
    int dosHealth = dos.getHealth();
    int dosAttack = dos.getAttack();
    assertThat(dosMana, is(2));
    assertThat(dosHealth,is(2));
    assertThat(dosAttack,is(2));
 }

    @Test
    public void shouldAllowFindusToPlayUno() {
        // Given initialized game
        // When playing card #2 (Uno) from hand to field
        Card card2 = game.getCardInHand(Player.FINDUS, 2);
        Status status = game.playCard(Player.FINDUS, card2);
        // Then it is allowed
        assertThat(status, is(Status.OK));

        // And Findus' hand is now size 2
        int count = game.getHandSize(Player.FINDUS);
        assertThat(count, is(2));

        // And Uno is present on field at index 0
        Card uno = game.getCardInField(Player.FINDUS, 0);
        assertThat(uno.getName(), is(GameConstants.UNO_CARD));

        // And Uno is sleeping / not active
        assertThat(uno.isActive(), is(false));
    }
    @Test
    public void shouldHaveSeparateDataForFindusAndPeddersen() {
        // Given we have executed the play of Uno for Findus
        shouldAllowFindusToPlayUno();
        // Then Peddersen still have a full hand of 3 cards
        assertThat(game.getHandSize(Player.PEDDERSEN), is(3));
        // And Peddersens field is till empty
        assertThat(game.getFieldSize(Player.PEDDERSEN), is(0));

        // And Peddersen's card 2 in hand is the uno card
        assertThat(game.getCardInHand(Player.PEDDERSEN, 2).getName(),
                is(GameConstants.UNO_CARD));
    }
    @Test
    public void shouldDrawCardFromDeckWhenTurnBegins() {
        // Given Findus does nothing in his turn
        game.endTurn();
        // Then it is Peddersens' turn AND he has one additional
        // card in his hand
        assertThat(game.getHandSize(Player.PEDDERSEN), is(4));
        // And that card is Cuatro at index 0
        assertThat(game.getCardInHand(Player.PEDDERSEN, 0).getName(),
                is(GameConstants.CUATRO_CARD));
    }
    @Test
    public void shouldSupportIteration() {
        int count = 0;
        for (Card c : game.getHand(Player.PEDDERSEN)) {
            count++;
        }
        assertThat(count, is(3));

        count = 0;
        for (Card c : game.getField(Player.FINDUS)) {
            count++;
        }
        assertThat(count, is(0));
    }
    @Test
    public void shouldNotFieldMoreThan3ManaOfCardsTest1() {
        // Given an initial game
        // Then Findus' hero has 3 mana to spend (according to Alpha Spec)
        Hero myHero = game.getHero(Player.FINDUS);
        assertThat(myHero, is(notNullValue()));
        assertThat(myHero.getMana(), is(3));

        // Then Findus can field Tres (mana cost 3)
        Card card0 = game.getCardInHand(Player.FINDUS, 0);
        assertThat(game.playCard(Player.FINDUS, card0), is(Status.OK));

        // And hero's mana count is now zero
        assertThat(myHero.getMana(), is(0));

        // When I try to play another minion
        card0 = game.getCardInHand(Player.FINDUS, 0);
        Status status = game.playCard(Player.FINDUS, card0);
        // Then I am told there is not enough mana
        assertThat(status, is(Status.NOT_ENOUGH_MANA));
        // And the field contains a single minion
        assertThat(game.getFieldSize(Player.FINDUS), is(1));
    }
    @Test
    public void shouldNotFieldMoreThan3ManaOfCardsTest2() {
        // Similar to above test, but fielding two cards of sum 3 mana
        Hero myHero = game.getHero(Player.FINDUS);
        // Given I play Uno, Then it is OK
        Card uno = game.getCardInHand(Player.FINDUS, 2);
        assertThat(game.playCard(Player.FINDUS, uno), is(Status.OK));
        // And I have 2 mana left
        assertThat(myHero.getMana(), is(2));
        // Then I can play Dos also
        Card dos = game.getCardInHand(Player.FINDUS, 1);
        assertThat(game.playCard(Player.FINDUS, dos), is(Status.OK));
        // And I have 0 mana left
        assertThat(myHero.getMana(), is(0));
        // And my field contains Dos at index 0
        assertThat(game.getCardInField(Player.FINDUS,0).getName(),
                is(GameConstants.DOS_CARD));
        // And Uno at index 1
        assertThat(game.getCardInField(Player.FINDUS,1).getName(),
                is(GameConstants.UNO_CARD));

        // And Peddersen is unaffected
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(3));
    }

    @Test
    public void shouldRefillManaWhenTurnBegins() {
        // Given Findus plays Tres
        Card tres = game.getCardInHand(Player.FINDUS, 0);
        assertThat(game.playCard(Player.FINDUS, tres), is(Status.OK));
        // And turn goes to opponent
        game.endTurn();
        // Then mana stays at 0
        assertThat(game.getHero(Player.FINDUS).getMana(), is(0));
        // When turn gets back to Findus
        game.endTurn();
        // Then mana is back to 3 (Alpha spec)
        assertThat(game.getHero(Player.FINDUS).getMana(), is(3));
    }

    @Test
    public void shouldRetrieveHeros() {
        // Given a game
        // Then Findus' hero - knows that it is owned by Findus
        assertThat(game.getHero(Player.FINDUS).getOwner(),
                is(Player.FINDUS));
        // And same for Pedersen
        assertThat(game.getHero(Player.PEDDERSEN).getOwner(),
                is(Player.PEDDERSEN));
    }

    @Test
    public void shouldAllowHeroToExecutePower() {
        // When Findus use his power
        Status status = game.usePower(Player.FINDUS);
        // Then it is OK
        assertThat(status, is(Status.OK));
        // And Hero's mana is 2 down
        assertThat(game.getHero(Player.FINDUS).getMana(), is(1));
        // Unfortunately no side effect so how to test ? A spy, but we
        // are not there yet.
    }

 @Test
 public void usePowerShouldNoBeOkBecauseOfLowMana(){
      Player p = game.getPlayerInTurn();
      HeroImpl h = (HeroImpl)game.getHero(p);
      h.setMana(1);
      Status notOk= game.usePower(p);
      assertThat(notOk, is(Status.NOT_ENOUGH_MANA));
    }

    @Test
    public void usePowerShouldNoBeOkBecauseOfNotPlayerInTurn() {
        Player p = game.getPlayerInTurn();
        game.endTurn();
        Status notOk = game.usePower(p);
        assertThat(notOk, is(Status.NOT_PLAYER_IN_TURN));
    }
    @Test
    public void shouldNotAllowPowerUseIfTooLittleMana() {
        // Given Findus has already spent mana
        Card dos = game.getCardInHand(Player.FINDUS, 1);
        assertThat(game.playCard(Player.FINDUS, dos), is(Status.OK));

        // When Findus tries to use hero power (mana 2)
        Status status = game.usePower(Player.FINDUS);
        // Then not enough mana is returned
        assertThat(status, is(Status.NOT_ENOUGH_MANA));
    }
    @Test
    public void shouldNotUseOpponentsPower() {
        // Given Findus is in turn
        // When he tries to use Peddersens power
        Status status = game.usePower(Player.PEDDERSEN);
        // Then it is not allowed as another player is in turn
        assertThat(status, is(Status.NOT_PLAYER_IN_TURN));
    }
}
