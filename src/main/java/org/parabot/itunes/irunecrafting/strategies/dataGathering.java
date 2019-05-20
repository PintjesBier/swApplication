package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.core.ui.Logger;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.core.Core;
import org.rev317.min.api.methods.Skill;

/**
 * Created by Tristan on 21/03/2018.
 */
public class dataGathering implements Strategy
{
    @Override
    public boolean activate() {
        return !Core.dataGathered;
    }

    @Override
    public void execute()
    {
        Logger.addMessage("iRuneCrafting: gathering data", true);
        Core.CurrentStatus = "Gathering data";

        Core.currentAltar = org.parabot.itunes.irunecrafting.data.selector.correspondingAltar(Skill.RUNECRAFTING.getRealLevel(), Core.runes);

        Core.startingLevel = Skill.RUNECRAFTING.getRealLevel();

        Core.dataGathered = true;
        Logger.addMessage("iRuneCrafting: Data gathered", true);
        Core.CurrentStatus = "Data gathered";
    }
}
