package com.ihatebees.item.client.tool;

import com.ihatebees.item.custom.DarkScytheSwordItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DarkScytheRenderer extends GeoItemRenderer<DarkScytheSwordItem> {
    public DarkScytheRenderer() {
        super(new DarkScytheModel()); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}
