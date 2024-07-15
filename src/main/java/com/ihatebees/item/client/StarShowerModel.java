package com.ihatebees.item.client;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.StarSawSwordItem;
import com.ihatebees.item.custom.StarShowerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class StarShowerModel extends GeoModel<StarShowerItem> {
    private final Identifier model = new Identifier(BeeMod.MODID, "geo/item/starshower.geo.json");
    private final Identifier texture = new Identifier(BeeMod.MODID, "textures/item/starshowermodel.png");
    private final Identifier animations = new Identifier(BeeMod.MODID, "animations/item/starsaw.animation.json");


    public Identifier getModelLocation(StarShowerItem object) {
        return this.model;
    }


    public Identifier getTextureLocation(StarShowerItem object) {
        return this.texture;
    }


    public Identifier getAnimationFileLocation(StarShowerItem object) {
        return this.animations;
    }

    @Override
    public Identifier getModelResource(StarShowerItem animatable) {
        return this.model;
    }

    @Override
    public Identifier getTextureResource(StarShowerItem animatable) {
        return this.texture;
    }

    @Override
    public Identifier getAnimationResource(StarShowerItem animatable) {
        return this.animations;
    }
}