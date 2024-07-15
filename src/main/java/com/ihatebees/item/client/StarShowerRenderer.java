package com.ihatebees.item.client;

import com.ihatebees.item.custom.StarSawSwordItem;
import com.ihatebees.item.custom.StarShowerItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class StarShowerRenderer extends GeoItemRenderer<StarShowerItem> {
    public StarShowerRenderer() {
        super(new StarShowerModel()); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}
