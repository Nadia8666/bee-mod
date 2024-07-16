package com.ihatebees.item.client.armor;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.TestHatArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class TestHatRenderer extends GeoArmorRenderer<TestHatArmorItem> {
    public TestHatRenderer() {
        super(new DefaultedItemGeoModel<>(new Identifier(BeeMod.MODID, "testhat"))); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}
