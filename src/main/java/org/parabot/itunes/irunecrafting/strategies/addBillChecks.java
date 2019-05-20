package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.data.Constants;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Items;

public class addBillChecks implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.contains(Constants.billCheck_id);
    }

    @Override
    public void execute() {

        Inventory.getItem(Constants.billCheck_id).interact(Items.Option.FOURTH);
        Time.sleep(() -> !Inventory.contains(Constants.billCheck_id), 2000);
    }
}
