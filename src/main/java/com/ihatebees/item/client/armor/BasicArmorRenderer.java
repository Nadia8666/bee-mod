package com.ihatebees.item.client.armor;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.armor.BasicArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class BasicArmorRenderer extends GeoArmorRenderer<BasicArmorItem> {
    public BasicArmorRenderer() {
        super(new DefaultedItemGeoModel<>(new Identifier(BeeMod.MODID, "basicarmor"))); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}
