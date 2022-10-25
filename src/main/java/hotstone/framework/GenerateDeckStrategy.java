package hotstone.framework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GenerateDeckStrategy {

    void generateDeck(Player who, HashMap<Player, List<MutableCard>> deck);
}
