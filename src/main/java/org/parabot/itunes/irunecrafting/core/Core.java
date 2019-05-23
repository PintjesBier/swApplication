package org.parabot.itunes.irunecrafting.core;


import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.data.Settings;
import org.parabot.itunes.irunecrafting.strategies.*;
import org.parabot.itunes.irunecrafting.ui.Gui;
import org.parabot.itunes.irunecrafting.ui.Paint;
import org.rev317.min.api.methods.Skill;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tristan on 14/03/2018.
 */

@ScriptManifest(author = "iTunes",
        category = Category.RUNECRAFTING,
        description = "SW application.",
        name = "iRuneCrafting",
        servers = {"Dreamscape"},
        version = 1.0)

public class Core extends Script implements Paintable {

    //VARIABLES
    private static Settings settings;
    private final ArrayList<Strategy> strategies;
    private Paint paint;

    public Core() {
        paint = new Paint();
        strategies = new ArrayList<>();
    }

    @Override
    public boolean onExecute() {
        strategies.add(new DataGathering());
        strategies.add(new AddBillChecks());
        strategies.add(new BankRunes());
        strategies.add(new DropRunes());
        strategies.add(new TeleportToAubury());
        strategies.add(new BuySupplies());
        strategies.add(new TeleportToAltar());
        strategies.add(new CraftRune());

        provide(strategies);

        //LOAD ui
        Gui gui = new Gui();

        while (gui.isVisible()) {
            Time.sleep(250);
        }

        Core.getSettings().setCurrentStatus("Waiting...");
        paint.setStartingLevel(Skill.RUNECRAFTING.getRealLevel());

        return true;
    }

    @Override
    public void onFinish() {
        super.onFinish();
    }

    @Override
    public void paint(Graphics graphics) {
        paint.onRepaint(graphics);
    }

    public static Settings getSettings() {
        return settings;
    }
}

