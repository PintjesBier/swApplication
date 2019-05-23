package org.parabot.itunes.irunecrafting.data;


    public enum selector { air(1439, 2478, 1, 15, 557),
        earth(1441, 2481, 15, 30, 558),
        water(1445, 2480, 30, 60, 556),
        mind(1449, 2479, 60, 75, 559),
        body(1447, 2483, 75, 121, 560);

        private final int talisman_id;
        private final int altar_id;
        private final int minLevel;
        private final int maxLevel;
        private final int rune_id;

        selector(int talisman_id, int altar_id, int minLevel, int maxLevel, int rune_id)
        {
            this.talisman_id = talisman_id;
            this.altar_id = altar_id;
            this.minLevel = minLevel;
            this.maxLevel = maxLevel;
            this.rune_id = rune_id;
        }

        public static selector correspondingAltar(int currentLevel, String mode)
        {
            //Output
            selector selector = air;

            if (mode.equals("progressive"))
            {
                for (org.parabot.itunes.irunecrafting.data.selector s : org.parabot.itunes.irunecrafting.data.selector.values())
                {
                    if (currentLevel >= s.minLevel && currentLevel < s.maxLevel)
                    {
                        selector = s;
                    }
                }
            }
            else
            {
                for (org.parabot.itunes.irunecrafting.data.selector s : org.parabot.itunes.irunecrafting.data.selector.values())
                {
                    if (mode.equals(s.toString()))
                    {
                        selector = s;
                    }
                }
            }

            return selector;

        }

        public int getAltarID()
        {
            return altar_id;
        }

        public int getTalismanID()
        {
            return talisman_id;
        }

        public int getRuneID() {return rune_id;}
    }


