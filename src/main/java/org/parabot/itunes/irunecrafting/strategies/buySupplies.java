package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.core.Core;
import org.parabot.itunes.irunecrafting.data.Constants;
import org.parabot.itunes.irunecrafting.data.Methods;
import org.rev317.min.api.methods.*;

public class buySupplies implements Strategy {
    @Override
    public boolean activate()
    {
        return Npcs.getClosest(Constants.aubury_id) != null && !Inventory.isFull() && Game.isLoggedIn();
    }

    @Override
    public void execute()
    {
        Logger.addMessage("iRuneCrafting: buying supplies", true);
        Core.CurrentStatus = "Buying supplies";

        Methods.ANTIAFK();

        Core.currentAltar = org.parabot.itunes.irunecrafting.data.selector.correspondingAltar(Skill.RUNECRAFTING.getRealLevel(), Core.runes);

        Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 3000);
        Npcs.getClosest(Constants.aubury_id).interact(Npcs.Option.TRADE);
        Time.sleep(() -> Interfaces.isOpen(Constants.shop_interface_id), 2000);

        if (!Inventory.contains(Core.currentAltar.getTalismanID()))
        {
            Menu.sendAction(78, Core.currentAltar.getTalismanID() - 1, Methods.findShopSlot(Core.currentAltar.getTalismanID()),3900);
        }

        Time.sleep(() -> Inventory.contains(Core.currentAltar.getTalismanID()), 2000);

        if (Interfaces.getOpenInterfaceId() == Constants.shop_interface_id) {
            while (!Inventory.isFull()) {
                Menu.sendAction(431, 1436, 0, 3900);
                Time.sleep(10, 22);
            }
        }


        Menu.sendAction(200,2159,0,3902);
        Time.sleep(() -> !Interfaces.isOpen(Constants.shop_interface_id), 2000);
    }
}
