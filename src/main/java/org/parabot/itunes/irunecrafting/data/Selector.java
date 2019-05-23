package org.parabot.itunes.irunecrafting.data;


public enum Selector {
    AIR(1439, 2478, 1, 15, 557),
    EARTH(1441, 2481, 15, 30, 558),
    WATER(1445, 2480, 30, 60, 556),
    MIND(1449, 2479, 60, 75, 559),
    BODY(1447, 2483, 75, 121, 560);

    private final int talismanId;
    private final int altarId;
    private final int minLevel;
    private final int maxLevel;
    private final int runeId;

    Selector(int talismanId, int altarId, int minLevel, int maxLevel, int runeId) {
        this.talismanId = talismanId;
        this.altarId = altarId;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.runeId = runeId;
    }

    public static Selector correspondingAltar(int currentLevel, String runes) {
        //Output
        Selector selector = AIR;

        if (runes.equals("progressive")) {
            for (Selector s : Selector.values()) {
                if (currentLevel >= s.minLevel && currentLevel < s.maxLevel) {
                    selector = s;
                }
            }
        } else {
            for (Selector s : Selector.values()) {
                if (runes.toUpperCase().equals(s.toString())) {
                    selector = s;
                }
            }
        }

        return selector;

    }

    public int getAltarID() {
        return altarId;
    }

    public int getTalismanID() {
        return talismanId;
    }

    public int getRuneID() {
        return runeId;
    }
}


