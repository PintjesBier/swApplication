package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.core.Core;
import org.parabot.itunes.irunecrafting.data.Constants;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Tile;

import static org.rev317.min.api.methods.Players.getMyPlayer;

public class bankRunes implements Strategy {
    private Tile teleportTile = new Tile(3254, 3088, 0);

    @Override
    public boolean activate()
    {
        return Core.mode.equals("Bank runes") && Core.needsBanking && Inventory.contains(Core.currentAltar.getRuneID()) && Game.isLoggedIn();
    }

    @Override
    public void execute()
    {
        Logger.addMessage("iRuneCrafting: teleporting to bank", true);
        Core.CurrentStatus = "Teleporting to bank";

        Menu.clickButton(20049);
        Time.sleep(() -> Interfaces.getBackDialogId() == Constants.teleportBackDialog, 2000);

        Menu.clickButton(2497);
        Time.sleep(() -> getMyPlayer().getLocation() == teleportTile && getMyPlayer().getAnimation() == -1, 5000);
        Time.sleep(250);

        Logger.addMessage("iRuneCrafting: banking runes", true);
        Core.CurrentStatus = "Banking runes";

        Bank.open();
        Time.sleep(Bank::isOpen, 3000);

        Bank.depositAllExcept(Core.currentAltar.getTalismanID(), Constants.runeEssence_id);
        Time.sleep(() -> !Inventory.contains(Core.currentAltar.getRuneID()), 5000);

        Bank.close();
        Core.needsBanking = false;
        Time.sleep(() -> Interfaces.isOpen(-1), 2000);
    }
}
