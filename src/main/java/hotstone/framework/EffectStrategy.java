package hotstone.framework;

public interface EffectStrategy {

     void usePower(Player who,Game game);
     void applyCardEffects(Game game,Player who,Card card);

}
