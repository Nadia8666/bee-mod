package com.ihatebees.item.client;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.CoconutBeltArmorItem;
import com.ihatebees.item.custom.CoconutCanisterArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class CoconutCanisterRenderer extends GeoArmorRenderer<CoconutCanisterArmorItem> {
    public CoconutCanisterRenderer() {
        super(new DefaultedItemGeoModel<>(new Identifier(BeeMod.MODID, "coconutcanister"))); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}
