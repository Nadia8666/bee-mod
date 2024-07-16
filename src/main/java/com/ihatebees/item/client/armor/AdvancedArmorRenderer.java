package com.ihatebees.item.client.armor;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.armor.AdvancedArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class AdvancedArmorRenderer extends GeoArmorRenderer<AdvancedArmorItem> {
    public AdvancedArmorRenderer() {
        super(new DefaultedItemGeoModel<>(new Identifier(BeeMod.MODID, "advancedarmor"))); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}
