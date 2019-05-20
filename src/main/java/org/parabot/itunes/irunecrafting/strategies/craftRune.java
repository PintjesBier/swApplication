package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.core.Core;
import org.parabot.itunes.irunecrafting.data.Methods;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;

import static org.parabot.itunes.irunecrafting.data.Constants.runeEssence_id;
import static org.rev317.min.api.methods.Players.getMyPlayer;

public class craftRune implements Strategy {
    @Override
    public boolean activate() {
        return SceneObjects.getClosest(Core.currentAltar.getAltarID()) != null && Inventory.contains(Core.currentAltar.getTalismanID()) && Inventory.contains(runeEssence_id) && Game.isLoggedIn();
    }

    @Override
    public void execute()
    {
        Logger.addMessage("iRuneCrafting: crafting runes", true);
        Core.CurrentStatus = "Crafting runes";

        int runesToCraft = Inventory.getCount(runeEssence_id);

        Methods.ANTIAFK();

        Time.sleep(() -> getMyPlayer().getAnimation() == -1, 3000);
/*        Time.sleep(350, 450);*/
        SceneObjects.getClosest(Core.currentAltar.getAltarID()).interact(SceneObjects.Option.CRAFT_RUNE);

        Time.sleep(() -> !Inventory.contains(runeEssence_id) && Inventory.contains(Core.currentAltar.getRuneID()) && getMyPlayer().getAnimation() == -1,4000);
        if (!Inventory.contains(runeEssence_id) && !Inventory.contains(runeEssence_id))
        {
            Core.runesCrafted = Core.runesCrafted + runesToCraft;
        }

        if (Core.mode.equals("Bank runes") && Inventory.contains(Core.currentAltar.getRuneID()))
        {
            Core.needsBanking = true;
        }

    }
}
