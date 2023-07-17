package hotstone.Decorator;
import hotstone.framework.*;
import hotstone.observer.GameObserver;

public class DecoratorHotStone implements Game {

    private MutableGame mutableGame;

    public DecoratorHotStone(MutableGame mutableGame){
        this.mutableGame=mutableGame;
    }
    @Override
    public Player getPlayerInTurn() {
        return mutableGame.getPlayerInTurn();
    }

    @Override
    public Hero getHero(Player who) {
        return mutableGame.getHero(who);
    }

    @Override
    public Player getWinner() {
        Player winner = mutableGame.getWinner();
        if (winner!= null){
            System.out.println(winner + " won the game!");
        }
        return winner;
    }

    @Override
    public int getTurnNumber() {
        return mutableGame.getTurnNumber();
    }

    @Override
    public int getDeckSize(Player who) {
        return mutableGame.getDeckSize(who);
    }

    @Override
    public Card getCardInHand(Player who, int indexInHand) {
        return mutableGame.getCardInHand(who,indexInHand);
    }

    @Override
    public Iterable<? extends Card> getHand(Player who) {
        return mutableGame.getHand(who);
    }

    @Override
    public int getHandSize(Player who) {
        return mutableGame.getHandSize(who);
    }

    @Override
    public Card getCardInField(Player who, int indexInField) {
        return mutableGame.getCardInField(who,indexInField);
    }

    @Override
    public Iterable<? extends Card> getField(Player who) {
        return mutableGame.getField(who);
    }

    @Override
    public int getFieldSize(Player who) {
        return mutableGame.getFieldSize(who);
    }

    @Override
    public void endTurn() {
        System.out.println(getPlayerInTurn() + " ended turn.");
        mutableGame.endTurn();
    }

    @Override
    public Status playCard(Player who, Card card) {
        Status s = mutableGame.playCard(who,card);
        if(s.equals(Status.OK)){
            System.out.println(who + " played " + card.getName() +" .");
        }
        return s;
    }

    @Override
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        Status s = mutableGame.attackCard(playerAttacking,attackingCard,defendingCard);
        if(s.equals(Status.OK)){
            System.out.printf("%s attacked %s with %s \n",playerAttacking,defendingCard.getName(),attackingCard.getName());
        }
        return s;
    }

    @Override
    public Status attackHero(Player playerAttacking, Card attackingCard) {
        Status s = mutableGame.attackHero(playerAttacking,attackingCard);
        String opponentHeroName = getHero(Utility.computeOpponent(playerAttacking)).getType();
        if(s.equals(Status.OK)){
            System.out.printf("%s attacked %s with %s\n",playerAttacking,opponentHeroName,attackingCard.getName());
        }
        return s;
    }

    @Override
    public Status usePower(Player who) {
        Status s = mutableGame.usePower(who);
        if(s.equals(Status.OK)){
            System.out.printf("%s used %s power\n",who,getHero(who).getType());
        }
        return s;
    }

    @Override
    public void addObserver(GameObserver observer) {
        mutableGame.addObserver(observer);
    }
}
