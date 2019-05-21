package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.core.Core;
import org.parabot.itunes.irunecrafting.data.Constants;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Item;

public class DropRunes implements Strategy {
    @Override
    public boolean activate() {
        return Core.mode.equals("Drop runes") && Inventory.contains(Core.currentAltar.getRuneID()) && Game.isLoggedIn();
    }

    @Override
    public void execute()
    {
        Logger.addMessage("iRuneCrafting: dropping runes", true);
        Core.currentStatus = "Dropping runes";

        for (Item i : Inventory.getItems())
        {
            if (i.getId() == Core.currentAltar.getRuneID())
            {
                i.interact(Items.Option.DROP);
                Time.sleep(new SleepCondition()
                {
                    @Override
                    public boolean isValid()
                    {
                        return Interfaces.getBackDialogId() == Constants.DROP_BACK_DIALOG_ID;
                    }
                }, 2000);
                Menu.clickButton(14175);
                Time.sleep(130,250);
            }
        }
    }
}
