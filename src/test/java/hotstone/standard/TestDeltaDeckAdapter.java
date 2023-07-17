package hotstone.standard;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.variants.custom.DeltaAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDeltaDeckAdapter {

    private DeltaAdapter deltaAdapter;

    @BeforeEach
    public void setUp(){
       deltaAdapter = new DeltaAdapter();
    }

    @Test
    public void findusShouldHaveCustomCards(){
        HashMap<Player, List<MutableCard>> customDeck = new HashMap<>();
        deltaAdapter.generateDeck(Player.FINDUS,customDeck);
        for(MutableCard c : customDeck.get(Player.FINDUS)){
            System.out.println(c.getName());
        }
    }
}
