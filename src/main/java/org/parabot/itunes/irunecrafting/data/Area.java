package org.parabot.itunes.irunecrafting.data;

import org.rev317.min.api.wrappers.Tile;

import java.awt.*;


public class Area
{
    private Polygon p;
    public Area(Tile... tiles)
    {
        this.p = new Polygon();
        for (Tile tile : tiles) {
            p.addPoint(tile.getX(), tile.getY());
        }
    }
    public boolean contains(Tile tile) {
        return this.contains(tile.getX(), tile.getY());
    }

    private boolean contains(int x, int y) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = p.npoints - 1; i < p.npoints; j = i++) {
            if ((p.ypoints[i] > y - 1) != (p.ypoints[j] > y - 1)
                    && (x <= (p.xpoints[j] - p.xpoints[i]) * (y - p.ypoints[i])
                    / (p.ypoints[j] - p.ypoints[i]) + p.xpoints[i])) {
                result = !result;
            }
        }
        return result;
    }
}