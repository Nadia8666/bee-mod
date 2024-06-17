package com.ihatebees.item.client;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.CoconutCanisterArmorItem;
import com.ihatebees.item.custom.DarkScytheSwordItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DarkScytheRenderer extends GeoItemRenderer<DarkScytheSwordItem> {
    public DarkScytheRenderer() {
        super(new DarkScytheModel()); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}
