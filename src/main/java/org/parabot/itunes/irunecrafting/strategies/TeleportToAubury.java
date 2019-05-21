package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.core.Core;
import org.parabot.itunes.irunecrafting.data.Constants;
import org.rev317.min.api.methods.*;

import static org.rev317.min.api.methods.Players.getMyPlayer;

public class TeleportToAubury implements Strategy {
    @Override
    public boolean activate() {
        return Npcs.getClosest(Constants.AUBURY_ID) == null && !Inventory.isFull() && Game.isLoggedIn();
    }

    @Override
    public void execute()
    {
        Logger.addMessage("iRuneCrafting: teleporting to Aubury", true);
        Core.currentStatus = "Teleporting to Aubury";

        Menu.clickButton(20053);
        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return Interfaces.getBackDialogId() == Constants.TELEPORT_BACK_DIALOG_ID;
            }
        }, 2000);

        Menu.clickButton(2498);
        Time.sleep(1000, 1200);

        Menu.clickButton(2497);
        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return Npcs.getClosest(Constants.AUBURY_ID) != null && getMyPlayer().getAnimation() == -1;
            }
        }, 5000);
    }
}
