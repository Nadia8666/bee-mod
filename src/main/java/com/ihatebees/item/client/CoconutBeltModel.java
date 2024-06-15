package com.ihatebees.item.client;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.CoconutBeltTrinketItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CoconutBeltModel extends GeoModel<CoconutBeltTrinketItem> {
    private final Identifier model = new Identifier(BeeMod.MODID, "geo/item/coconutbelt.geo.json");
    private final Identifier texture = new Identifier(BeeMod.MODID, "textures/item/coconutbelt.png");
    private final Identifier animations = new Identifier(BeeMod.MODID, "animations/item/coconutbelt.animation.json");


    public Identifier getModelLocation(CoconutBeltTrinketItem object) {
        return this.model;
    }


    public Identifier getTextureLocation(CoconutBeltTrinketItem object) {
        return this.texture;
    }


    public Identifier getAnimationFileLocation(CoconutBeltTrinketItem object) {
        return this.animations;
    }

    @Override
    public Identifier getModelResource(CoconutBeltTrinketItem animatable) {
        return this.model;
    }

    @Override
    public Identifier getTextureResource(CoconutBeltTrinketItem animatable) {
        return this.texture;
    }

    @Override
    public Identifier getAnimationResource(CoconutBeltTrinketItem animatable) {
        return this.animations;
    }
}