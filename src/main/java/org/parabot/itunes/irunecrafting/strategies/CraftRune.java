package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.core.Core;
import org.parabot.itunes.irunecrafting.data.Methods;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;

import static org.parabot.itunes.irunecrafting.data.Constants.RUNE_ESSENCE_ID;
import static org.rev317.min.api.methods.Players.getMyPlayer;

public class CraftRune implements Strategy {
    @Override
    public boolean activate() {
        return SceneObjects.getClosest(Core.getSettings().getCurrentAltar().getAltarID()) != null && Inventory.contains(Core.getSettings().getCurrentAltar().getTalismanID()) && Inventory.contains(RUNE_ESSENCE_ID) && Game.isLoggedIn();
    }

    @Override
    public void execute() {
        Logger.addMessage("iRuneCrafting: crafting runes", true);
        Core.getSettings().setCurrentStatus("Crafting runes");

        int runesToCraft = Inventory.getCount(RUNE_ESSENCE_ID);

        Methods.antiAFK();

        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return getMyPlayer().getAnimation() == -1;
            }
        }, 3000);
        SceneObjects.getClosest(Core.getSettings().getCurrentAltar().getAltarID()).interact(SceneObjects.Option.CRAFT_RUNE);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return !Inventory.contains(RUNE_ESSENCE_ID) && Inventory.contains(Core.getSettings().getCurrentAltar().getRuneID()) && getMyPlayer().getAnimation() == -1;
            }
        }, 4000);


        if (!Inventory.contains(RUNE_ESSENCE_ID) && !Inventory.contains(RUNE_ESSENCE_ID)) {
            Core.getSettings().setRunesCrafted(Core.getSettings().getRunesCrafted() + runesToCraft);
        }

        if (Core.getSettings().getMode().equals("Bank runes") && Inventory.contains(Core.getSettings().getCurrentAltar().getRuneID())) {
            Core.getSettings().setNeedsBanking(true);
        }

    }
}
