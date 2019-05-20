package org.parabot.itunes.irunecrafting.data;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Random;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.itunes.irunecrafting.core.Core;
import org.rev317.min.Loader;

public class Methods
{

    //TELEPORTS


    //FUNCTIONAL
    public static String stripNonDigits(final CharSequence input)
    {
        final StringBuilder sb = new StringBuilder(input.length());

        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            if (c > 47 && c < 58) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void ANTIAFK() {
        int[] keys = new int[]{38, 40, 37, 39};
        if (Random.between(0, 4) == 2) {
            Logger.addMessage("iArcade: Performing antiAFK", true);
            Core.CurrentStatus = "Anti AFK";
            int keyCode = keys[Random.between(0, keys.length)];
            Keyboard.getInstance().pressKey(keyCode);
            Time.sleep(Random.between(800, 1500));
            Keyboard.getInstance().releaseKey(keyCode);
            Core.CurrentStatus = "Waiting...";
        }
    }

    //Thanks to emmastone for this piece of code!
    public static int findShopSlot(int itemId) {
        int[] shopItemsIds = Loader.getClient().getInterfaceCache()[3900].getItems();
        for (int i = 0; i < shopItemsIds.length; i++) {
            if (shopItemsIds[i] == itemId) {
                return i;
            }
        }

        return -1;
    }

}