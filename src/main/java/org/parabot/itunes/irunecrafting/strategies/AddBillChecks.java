package org.parabot.itunes.irunecrafting.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.itunes.irunecrafting.data.Constants;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Items;

public class AddBillChecks implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.contains(Constants.BILL_CHECK_ID);
    }

    @Override
    public void execute() {

        Inventory.getItem(Constants.BILL_CHECK_ID).interact(Items.Option.FOURTH);
        Time.sleep(new SleepCondition()
        {
            @Override
            public boolean isValid()
            {
                return !Inventory.contains(Constants.BILL_CHECK_ID);
            }
        }, 2000);
    }
}
