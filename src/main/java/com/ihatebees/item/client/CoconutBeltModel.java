package com.ihatebees.item.client;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.CoconutBeltArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CoconutBeltModel extends GeoModel<CoconutBeltArmorItem> {
    private final Identifier model = new Identifier(BeeMod.MODID, "geo/item/coconutbelt.geo.json");
    private final Identifier texture = new Identifier(BeeMod.MODID, "textures/item/coconutbelt.png");
    private final Identifier animations = new Identifier(BeeMod.MODID, "animations/item/coconutbelt.animation.json");


    public Identifier getModelLocation(CoconutBeltArmorItem object) {
        return this.model;
    }


    public Identifier getTextureLocation(CoconutBeltArmorItem object) {
        return this.texture;
    }


    public Identifier getAnimationFileLocation(CoconutBeltArmorItem object) {
        return this.animations;
    }

    @Override
    public Identifier getModelResource(CoconutBeltArmorItem animatable) {
        return this.model;
    }

    @Override
    public Identifier getTextureResource(CoconutBeltArmorItem animatable) {
        return this.texture;
    }

    @Override
    public Identifier getAnimationResource(CoconutBeltArmorItem animatable) {
        return this.animations;
    }
}