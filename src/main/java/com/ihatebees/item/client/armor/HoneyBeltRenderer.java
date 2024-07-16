package com.ihatebees.item.client.armor;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.armor.CoconutBeltTrinketItem;
import com.ihatebees.item.custom.armor.HoneyBeltTrinketItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class HoneyBeltRenderer extends GeoItemRenderer<HoneyBeltTrinketItem> {
    public HoneyBeltRenderer() {
        super(new DefaultedItemGeoModel<>(new Identifier(BeeMod.MODID, "honeybelt"))); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}
