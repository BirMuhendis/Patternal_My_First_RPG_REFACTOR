package com.states;

import com.rpggame.core.GameManager;
import java.util.HashMap;
import java.util.Map;

public class LocationBuilder {
    private String description;
    private Map<String, LocationState> options = new HashMap<>();
    private Map<String, Runnable> actions = new HashMap<>();

    public LocationBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocationBuilder addOption(String optionText, LocationState nextLocation) {
        options.put(optionText, nextLocation);
        return this;
    }

    public LocationBuilder addAction(String optionText, Runnable action) {
        actions.put(optionText, action);
        return this;
    }

    public LocationState build() {
        return new LocationState() {
            @Override
            public void onEnter(GameManager game) {
                
                game.printLine("\n-------------------------");
                game.printLine(description);
                game.printLine("-------------------------");

                int index = 1;
                String[] keys = new String[options.size() + actions.size()];
                int k = 0;
                
                for (String key : options.keySet()) {
                    game.printLine(index + ". " + key);
                    keys[k++] = key;
                    index++;
                }
                for (String key : actions.keySet()) {
                    game.printLine(index + ". " + key);
                    keys[k++] = key;
                    index++;
                }

                int choice = game.getSafeIntInput("Seciminiz: ", 1, keys.length);
                String selectedKey = keys[choice - 1];

                if (options.containsKey(selectedKey)) {
                    game.changeLocation(options.get(selectedKey));
                } else if (actions.containsKey(selectedKey)) {
                    actions.get(selectedKey).run();
                    this.onEnter(game); 
                }
            }
        };
    }
}