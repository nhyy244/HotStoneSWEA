package hotstone.TestStubs;

import hotstone.framework.*;
import hotstone.standard.HeroImpl;
import hotstone.variants.factory.HotStoneFactory;

public class GameDoubleForZetaStone implements Game {
    private Hero findusHero;
    private Hero peddersenHero;

    private WinnerStrategy winnerStrategy;
    private HotStoneFactory hotStoneFactory;
    private int turnNumber;
    public GameDoubleForZetaStone(HotStoneFactory hotStoneFactory){
        this.hotStoneFactory=hotStoneFactory;
        this.findusHero = new HeroImpl("French",Player.FINDUS,"Redwine");
        this.peddersenHero = new HeroImpl("Italian",Player.FINDUS,"Pasta");
        this.winnerStrategy = hotStoneFactory.createWinnerStrategy();
    }
    @Override
    public Player getPlayerInTurn() {
        return null;
    }

    @Override
    public Hero getHero(Player who) {
        return (who==Player.FINDUS)?findusHero:peddersenHero;
    }

    @Override
    public Player getWinner() {
        return winnerStrategy.getWinner(this);
    }

    @Override
    public int getTurnNumber() {
        return turnNumber;
    }

    @Override
    public int getDeckSize(Player who) {
        return 0;
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
        return 0;
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
        return 0;
    }

    @Override
    public void endTurn() {
        turnNumber++;
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
        return null;
    }
}
