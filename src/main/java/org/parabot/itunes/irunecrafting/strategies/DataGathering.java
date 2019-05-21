package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.core.ui.Logger;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.core.Core;
import org.parabot.itunes.irunecrafting.data.Selector;
import org.rev317.min.api.methods.Skill;

/**
 * Created by Tristan on 21/03/2018.
 */
public class DataGathering implements Strategy
{
    @Override
    public boolean activate() {
        return !Core.dataGathered;
    }

    @Override
    public void execute()
    {
        Logger.addMessage("iRuneCrafting: gathering data", true);
        Core.currentStatus = "Gathering data";

        Core.currentAltar = Selector.correspondingAltar(Skill.RUNECRAFTING.getRealLevel(), Core.runes);

        Core.startingLevel = Skill.RUNECRAFTING.getRealLevel();

        Core.dataGathered = true;
        Logger.addMessage("iRuneCrafting: Data gathered", true);
        Core.currentStatus = "Data gathered";
    }
}
