package com.ihatebees.item.client;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.CoconutBeltArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class CoconutBeltRenderer extends GeoArmorRenderer<CoconutBeltArmorItem> {
    public CoconutBeltRenderer() {
        super(new DefaultedItemGeoModel<>(new Identifier(BeeMod.MODID, "coconutbelt"))); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}
