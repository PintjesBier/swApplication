package org.parabot.itunes.irunecrafting.core;


import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.GUI.GUI;
import org.parabot.itunes.irunecrafting.data.Selector;
import org.parabot.itunes.irunecrafting.strategies.*;
import org.rev317.min.api.methods.Skill;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Tristan on 14/03/2018.
 */

@ScriptManifest(author = "iTunes",
        category = Category.RUNECRAFTING,
        description = "SW application.",
        name = "iRuneCrafting",
        servers = { "Dreamscape" },
        version = 1.0)

public class Core extends Script implements Paintable
{

    //VARIABLES
    public static boolean dataGathered = false;
    private final ArrayList<Strategy> strategies = new ArrayList<>();
    public static String currentStatus = "Starting";
    public static String runes;
    public static String mode;
    public static boolean needsBanking;
    public static int runesCrafted;
    public static int startingLevel;
    public static Selector currentAltar;

    //TIMER
    private static org.parabot.environment.api.utils.Timer Timer = new Timer();

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

        //LOAD GUI
        GUI gui = new GUI();

        while(gui.isVisible()) {
            Time.sleep(250);
        }

        return true;
    }

    @Override
    public void onFinish() {
        super.onFinish();
    }

    //START: Code generated using Enfilade's Easel
    private final Color color1 = new Color(7, 7, 184, 178);
    private final Color color2 = new Color(255, 255, 255, 178);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("Arial", 1, 12);

    private String FormatNumber(double number) {
        if (number >= 1000 && number < 1000000) {
            return new DecimalFormat("#,###.0").format(number / 1000) + "K";
        } else if (number >= 1000000 && number < 1000000000) {
            return new DecimalFormat("#,###.0").format(number / 1000000) + "M";
        } else if (number >= 1000000000) {
            return new DecimalFormat("#,###.00").format(number / 1000000000) + "B";
        }

        return "" + number;
    }

    @Override
    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;

        //PAINT
        g.setColor(color1);
        g.fillRect(277, 195, 235, 139);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRect(277, 195, 235, 139);
        g.setFont(font1);
        g.drawString("Status: ", 285, 210);
        g.drawString(Core.currentStatus, 380, 210);
        g.drawString("Runes crafted: ", 285, 225);
        g.drawString(String.valueOf(runesCrafted), 380, 225);
        g.drawString("Levels gained: ", 285, 240);
        g.drawString((Skill.RUNECRAFTING.getRealLevel() - startingLevel) + " (" + Skill.RUNECRAFTING.getRealLevel() + ")", 380, 240);
        g.drawString("Time elapsed: ", 285, 255);
        g.drawString(Timer.toString(), 380, 255);

    }
}

