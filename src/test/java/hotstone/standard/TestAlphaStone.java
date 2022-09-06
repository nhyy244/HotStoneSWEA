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
import hotstone.variants.FindusWinsAt4RoundsStrategy;
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
    game = new StandardHotStoneGame(new FindusWinsAt4RoundsStrategy());
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

    @Test
    public void shouldDeclareFindusWinnerAfter4Rounds() {
        // During first 4 rounds there is no winner
        for (int i = 0; i < 4; i++) {
            assertThat(game.getWinner(), is(nullValue()));
            game.endTurn(); // over to Peddersen (turn 1, 3, 5, 7)
            assertThat(game.getWinner(), is(nullValue()));
            game.endTurn(); // over to Findus (turn 2, 4, 6, and then)
        }
        // Then Findus is declared winner
        assertThat(game.getWinner(), is(Player.FINDUS));
    }
    @Test
    public void shouldNotActWhenNotInTurn() {
        // Given Peddersen is in turn
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        game.endTurn();

        // When Findus tries to play from hand (with low mana cost)
        Card aCard = game.getCardInHand(Player.FINDUS, 2);
        Status status = game.playCard(Player.FINDUS, aCard);
        // Then it is NOT PLAYER IN TURN
        assertThat(status, is(Status.NOT_PLAYER_IN_TURN));

        // When Findus tries to attack from field
        Card dos = game.getCardInField(Player.PEDDERSEN, 0);
        Card tres = game.getCardInField(Player.FINDUS, 0);
        // Then it is NOT PLAYER IN TURN
        assertThat(game.attackCard(Player.FINDUS, tres, dos),
                is(Status.NOT_PLAYER_IN_TURN));

        // When Findus tries to attack Hero
        // Then it is NOT PLAYER IN TURN
        assertThat(game.attackHero(Player.FINDUS, tres),
                is(Status.NOT_PLAYER_IN_TURN));
    }

    @Test
    public void shouldNotActOnOpponentsBehalf() {
        // Given Findus is in turn and has full mana
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        game.endTurn();
        game.endTurn();

        // When Findus tries to field a card owned by Peddersen
        Card aCard = game.getCardInHand(Player.PEDDERSEN, 3);
        Status status = game.playCard(Player.FINDUS, aCard);
        // Then it is not allowed as NOT OWNER
        assertThat(status, is(Status.NOT_OWNER));

        // When Findus tries to attack with Peddersens card
        Card dos = game.getCardInField(Player.PEDDERSEN, 0);
        Card tres = game.getCardInField(Player.FINDUS, 0);
        status = game.attackCard(Player.FINDUS, dos, tres);
        // Then it is NOT OWNER
        assertThat(status, is(Status.NOT_OWNER));

        // When Findus tries to attack Hero with Peddersens card
        status = game.attackHero(Player.FINDUS, dos);
        // Then it is NOT OWNER
        assertThat(status, is(Status.NOT_OWNER));

        // And of course if we reverse the situation, the same holds true
        game.endTurn();

        // When playing opponents card
        aCard = game.getCardInHand(Player.FINDUS, 3);
        status = game.playCard(Player.PEDDERSEN, aCard);
        // Then it is not allowed as NOT OWNER
        assertThat(status, is(Status.NOT_OWNER));

        // When attacking with opponents card
        status = game.attackCard(Player.PEDDERSEN, tres, dos);
        // Then it is NOT OWNER
        assertThat(status, is(Status.NOT_OWNER));
    }
    @Test
    public void shouldNotAllowAttackingWithSleepingCard() {
        // Given initial Game, with Uno fielded
        Card uno = game.getCardInHand(Player.FINDUS, 2);
        game.playCard(Player.FINDUS, uno);
        // When Findus tries to attack hero with it
        Status status = game.attackHero(Player.FINDUS, uno);
        // Then it is not allowed due to sleeping card
        assertThat(status, is(Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION));
    }
    @Test
    public void shouldAllowAttackingHero() {
        // Given a game with Tres available for Findus
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        // When Findus attacks the Hero of Peddersen
        Card tres = game.getCardInField(Player.FINDUS, 0);
        Status status = game.attackHero(Player.FINDUS, tres);
        // Then it is allowed
        assertThat(status, is(Status.OK));
        // And Peddersen's opponent has health reduced correctly
        // which is initial health minus attack value of Tres
        Hero Peddersen = game.getHero(Player.PEDDERSEN);
        assertThat(Peddersen.getHealth(),
                is(GameConstants.HERO_MAX_HEALTH - tres.getAttack()));

        // And Tres is not active any more
        assertThat(tres.isActive(), is(false));
    }
    @Test
    public void shouldNotAttackYourOwnMinions() {
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        // When Findus fields Uno
        Card c = game.getCardInHand(Player.FINDUS, 2);
        game.playCard(Player.FINDUS, c);

        // When Findus attacks own Uno with Tres
        Card tres = game.getCardInField(Player.FINDUS, 1);
        Status status = game.attackCard(Player.FINDUS, tres, c);
        assertThat(status, is(Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION));
    }
    @Test
    public void shouldRemoveCardForZeroHealth() {
        // Late found bug - health == 0 is also removal cause!
        // Given a game in which both field Uno
        Card unoFindus = game.getCardInHand(Player.FINDUS, 2);
        game.playCard(Player.FINDUS, unoFindus);
        game.endTurn();
        Card unoPeddersen = game.getCardInHand(Player.PEDDERSEN, 3);
        game.playCard(Player.PEDDERSEN, unoPeddersen);
        game.endTurn();

        // When Findus attacks uno on uno
        Status status = game.attackCard(Player.FINDUS, unoFindus, unoPeddersen);
        // Then both cards are removed
        assertThat(game.getFieldSize(Player.FINDUS), is(0));
        assertThat(game.getFieldSize(Player.PEDDERSEN), is(0));
    }
    @Test
    public void shouldRemoveAttackerAlsoIfHealthBelowZero() {
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        // Given it is Peddersens's turn
        game.endTurn();
        // When he attacks Tres with Dos
        Card tres = game.getCardInField(Player.FINDUS, 0);
        Card dos = game.getCardInField(Player.PEDDERSEN, 0);
        assertThat(game.attackCard(Player.PEDDERSEN, dos, tres),
                is(Status.OK));
        // Then Peddersen's card is removed from the field
        assertThat(game.getFieldSize(Player.PEDDERSEN), is(0));
    }
    @Test
    public void shouldNotAttackWithNonActiveMinion() {
        // Given Findus has minion Tres and Peddersen minion Dos
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);

        // When Findus fields Uno (entering index 0 on field)
        Card uno = game.getCardInHand(Player.FINDUS,2);
        assertThat(game.playCard(Player.FINDUS, uno), is(Status.OK));
        assertThat(game.getCardInField(Player.FINDUS, 0).getName(),
                is(GameConstants.UNO_CARD));
        assertThat(game.getCardInField(Player.FINDUS, 0).isActive(), is(false));

        // And immediately tries to attack Peddersen's at index 0
        Card PeddersenDos = game.getCardInField(Player.PEDDERSEN, 0);
        Status status = game.attackCard(Player.FINDUS, uno, PeddersenDos);
        assertThat(status, is(Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION));

        // Then Tres is allowed to attack (the card is active).
        Card FindusTres = game.getCardInField(Player.FINDUS, 1);
        status = game.attackCard(Player.FINDUS, FindusTres, PeddersenDos);
        assertThat(status, is(Status.OK));
    }
    @Test
    public void shouldAttackWithTresOnDos() {
        // Given Findus has minion Tres and Peddersen minion Dos
        TestHelper.fieldTresForFindusAndDosForPeddersen(game);
        // When Findus attacks Dos
        Card FindusTres = game.getCardInField(Player.FINDUS, 0);
        Card PeddersenDos = game.getCardInField(Player.PEDDERSEN, 0);
        Status status = game.attackCard(Player.FINDUS, FindusTres, PeddersenDos);
        // Then the attack succeeds
        assertThat(status, is(Status.OK));
        // As Findus's Tres has attack 3 and Dos only has 2, Tres's health is now 1
        Card tres = game.getCardInField(Player.FINDUS, 0);
        assertThat(tres.getHealth(), is(1));
        // And Tres is no longer active
        assertThat(tres.isActive(), is(false));

        // And Peddersen no longer has that minion fielded
        assertThat(game.getFieldSize(Player.PEDDERSEN), is(0));
    }
}
