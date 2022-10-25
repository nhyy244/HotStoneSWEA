package hotstone.framework;

public interface EffectStrategy {

     void usePower(Player who,MutableGame game);
     void applyCardEffects(MutableGame game,Player who,Card card);
}
