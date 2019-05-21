package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.core.Core;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Items;
import org.rev317.min.api.methods.SceneObjects;

import static org.rev317.min.api.methods.Players.getMyPlayer;

public class TeleportToAltar implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.isFull() && Inventory.contains(Core.currentAltar.getTalismanID()) && SceneObjects.getClosest(Core.currentAltar.getAltarID()) == null && Game.isLoggedIn();
    }

    @Override
    public void execute()
    {
        Logger.addMessage("iRuneCrafting: teleporting to altar", true);
        Core.currentStatus = "Teleporting to altar";

        Inventory.getItem(Core.currentAltar.getTalismanID()).interact(Items.Option.FOURTH);

        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return SceneObjects.getClosest(Core.currentAltar.getAltarID()) != null && getMyPlayer().getAnimation() == -1;
            }
        }, 5000);
    }
}
