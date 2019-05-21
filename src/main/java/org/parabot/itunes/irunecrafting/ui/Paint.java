package org.parabot.itunes.irunecrafting.ui;

import org.parabot.environment.api.utils.Timer;
import org.parabot.itunes.irunecrafting.core.Core;
import org.rev317.min.api.methods.Skill;

import java.awt.*;

public class Paint
{
    //TIMER
    private static org.parabot.environment.api.utils.Timer timer = new Timer();

    //VARIABLES
    private static int startingLevel = 0;

    //START: Code generated using Enfilade's Easel
    private final Color color1 = new Color(7, 7, 184, 178);
    private final Color color2 = new Color(255, 255, 255, 178);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("Arial", 1, 12);


    public void onRepaint(Graphics g1)
    {
        Graphics2D g = (Graphics2D) g1;
        g.setColor(color1);
        g.fillRect(277, 195, 235, 139);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRect(277, 195, 235, 139);
        g.setFont(font1);
        g.drawString("Status: ", 285, 210);
        g.drawString(Core.getSettings().getCurrentStatus(), 380, 210);
        g.drawString("Runes crafted: ", 285, 225);
        g.drawString(String.valueOf(Core.getSettings().getRunesCrafted()), 380, 225);
        g.drawString("Levels gained: ", 285, 240);
        g.drawString((Skill.RUNECRAFTING.getRealLevel() - startingLevel) + " (" + Skill.RUNECRAFTING.getRealLevel() + ")", 380, 240);
        g.drawString("Time elapsed: ", 285, 255);
        g.drawString(timer.toString(), 380, 255);
    }

    public void setStartingLevel(int startingLevel)
    {
        startingLevel = startingLevel;
    }
}
