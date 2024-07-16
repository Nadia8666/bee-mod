package com.ihatebees.item.client.armor;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.armor.CoconutBeltTrinketItem;
import com.ihatebees.item.custom.armor.HoneyBeltTrinketItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class HoneyBeltModel extends GeoModel<HoneyBeltTrinketItem> {
    private final Identifier model = new Identifier(BeeMod.MODID, "geo/item/honeybelt.geo.json");
    private final Identifier texture = new Identifier(BeeMod.MODID, "textures/item/honeybelt.png");
    private final Identifier animations = new Identifier(BeeMod.MODID, "animations/item/honeybelt.animation.json");


    public Identifier getModelLocation(HoneyBeltTrinketItem object) {
        return this.model;
    }


    public Identifier getTextureLocation(HoneyBeltTrinketItem object) {
        return this.texture;
    }


    public Identifier getAnimationFileLocation(HoneyBeltTrinketItem object) {
        return this.animations;
    }

    @Override
    public Identifier getModelResource(HoneyBeltTrinketItem animatable) {
        return this.model;
    }

    @Override
    public Identifier getTextureResource(HoneyBeltTrinketItem animatable) {
        return this.texture;
    }

    @Override
    public Identifier getAnimationResource(HoneyBeltTrinketItem animatable) {
        return this.animations;
    }
}