package hotstone.broker.doubles;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.observer.GameObserver;

import java.awt.image.ImageObserver;

public class NullObserver implements GameObserver {
    @Override
    public void onCardPlay(Player who, Card card) {

    }

    @Override
    public void onTurnChangeTo(Player playerBecomingActive) {

    }

    @Override
    public void onAttackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {

    }

    @Override
    public void onAttackHero(Player playerAttacking, Card attackingCard) {

    }

    @Override
    public void onUsePower(Player who) {

    }

    @Override
    public void onCardDraw(Player who, Card drawnCard) {

    }

    @Override
    public void onCardUpdate(Card card) {

    }

    @Override
    public void onCardRemove(Player who, Card card) {

    }

    @Override
    public void onHeroUpdate(Player who) {

    }

    @Override
    public void onGameWon(Player playerWinning) {

    }
}
