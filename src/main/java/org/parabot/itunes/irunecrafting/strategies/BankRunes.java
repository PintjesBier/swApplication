package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.core.Core;
import org.parabot.itunes.irunecrafting.data.Constants;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Tile;

import static org.rev317.min.api.methods.Players.getMyPlayer;

public class BankRunes implements Strategy {
    private Tile teleportTile = new Tile(3254, 3088, 0);

    @Override
    public boolean activate()
    {
        return Core.getSettings().getMode().equals("Bank runes") && Core.getSettings().needsBanking() && Inventory.contains(Core.getSettings().getCurrentAltar().getRuneID()) && Game.isLoggedIn();
    }

    @Override
    public void execute()
    {
        Logger.addMessage("iRuneCrafting: teleporting to bank", true);
        Core.getSettings().setCurrentStatus("Teleporting to bank");

        Menu.clickButton(20049);
        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return Interfaces.getBackDialogId() == Constants.TELEPORT_BACK_DIALOG_ID;
            }
        }, 2000);

        Menu.clickButton(2497);
        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return getMyPlayer().getLocation() == teleportTile && getMyPlayer().getAnimation() == -1;
            }
        }, 5000);
        Time.sleep(250);

        Logger.addMessage("iRuneCrafting: banking runes", true);
        Core.getSettings().setCurrentStatus("Banking runes");

        Bank.open();
        Time.sleep(Bank::isOpen, 3000);

        Bank.depositAllExcept(Core.getSettings().getCurrentAltar().getTalismanID(), Constants.RUNE_ESSENCE_ID);
        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return !Inventory.contains(Core.getSettings().getCurrentAltar().getRuneID());
            }
        }, 5000);


        Bank.close();
        Core.getSettings().setNeedsBanking(false);
        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return Interfaces.isOpen(-1);
            }
        }, 2000);
    }
}
