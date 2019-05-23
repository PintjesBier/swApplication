package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.core.ui.Logger;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.core.Core;
import org.parabot.itunes.irunecrafting.data.selector;
import org.rev317.min.api.methods.Skill;

/**
 * Created by Tristan on 21/03/2018.
 */
public class DataGathering implements Strategy
{
    @Override
    public boolean activate() {
        return !Core.getSettings().dataIsGathered();
    }

    @Override
    public void execute()
    {
        Logger.addMessage("iRuneCrafting: gathering data", true);
        Core.getSettings().setCurrentStatus("Gathering data");

        Core.getSettings().setCurrentAltar(selector.correspondingAltar(Skill.RUNECRAFTING.getRealLevel(), Core.getSettings().getRunes()));

        Core.getSettings().setDataGathered(true);
        Logger.addMessage("iRuneCrafting: Data gathered", true);
        Core.getSettings().setCurrentStatus("Data gathered");
    }
}
