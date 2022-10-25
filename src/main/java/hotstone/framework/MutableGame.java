package hotstone.framework;

public interface MutableGame extends Game{
    // === Private Interface for internal mutable state
    void setHeroActiveState(Player who, boolean isActive);
    void setCardActiveState(Card card, boolean isActive);
    void deltaCardHealth(Card card, int value);
    void deltaHeroMana(Player who, int manaValue);
    void deltaHeroHealth(Player who, int value);
    void drawCardFromDeck(Player who);
    void addCardToField(Player who, Card card);
    void deltaFieldCardHealth(Player who, int fieldIndex, int delta);
    // PRECONDITION: The health will not drop below 0, if it does use removeCardFromField!
    void deltaFieldCardAttack(Player who, int fieldIndex, int delta);
    void removeCardFromField(Player who, Card card);
}
