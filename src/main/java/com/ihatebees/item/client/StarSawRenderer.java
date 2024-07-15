package com.ihatebees.item.client;

import com.ihatebees.item.custom.DarkScytheSwordItem;
import com.ihatebees.item.custom.StarSawSwordItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class StarSawRenderer extends GeoItemRenderer<StarSawSwordItem> {
    public StarSawRenderer() {
        super(new StarSawModel()); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}
