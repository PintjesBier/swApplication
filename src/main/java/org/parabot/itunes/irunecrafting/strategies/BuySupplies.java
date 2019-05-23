package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.core.Core;
import org.parabot.itunes.irunecrafting.data.Constants;
import org.parabot.itunes.irunecrafting.data.Methods;
import org.parabot.itunes.irunecrafting.data.selector;
import org.rev317.min.api.methods.*;

public class BuySupplies implements Strategy {
    @Override
    public boolean activate()
    {
        return Npcs.getClosest(Constants.AUBURY_ID) != null && !Inventory.isFull() && Game.isLoggedIn();
    }

    @Override
    public void execute()
    {
        Logger.addMessage("iRuneCrafting: buying supplies", true);
        Core.getSettings().setCurrentStatus("Buying supplies");

        Methods.antiAFK();

        Core.getSettings().setCurrentAltar(selector.correspondingAltar(Skill.RUNECRAFTING.getRealLevel(), Core.getSettings().getRunes()));

        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return Players.getMyPlayer().getAnimation() == -1;
            }
        }, 3000);
        Npcs.getClosest(Constants.AUBURY_ID).interact(Npcs.Option.TRADE);
        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return Interfaces.isOpen(Constants.SHOP_INTERFACE_ID);
            }
        }, 2000);

        if (!Inventory.contains(Core.getSettings().getCurrentAltar().getTalismanID()))
        {
            Menu.sendAction(78, Core.getSettings().getCurrentAltar().getTalismanID() - 1, Methods.findShopSlot(Core.getSettings().getCurrentAltar().getTalismanID()),3900);
        }

        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return Inventory.contains(Core.getSettings().getCurrentAltar().getTalismanID());
            }
        }, 2000);

        if (Interfaces.getOpenInterfaceId() == Constants.SHOP_INTERFACE_ID) {
            while (!Inventory.isFull()) {
                Menu.sendAction(431, 1436, 0, 3900);
                Time.sleep(10, 22);
            }
        }


        Menu.sendAction(200,2159,0,3902);
        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return !Interfaces.isOpen(Constants.SHOP_INTERFACE_ID);
            }
        }, 2000);
    }
}
