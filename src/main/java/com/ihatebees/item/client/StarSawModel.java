package com.ihatebees.item.client;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.DarkScytheSwordItem;
import com.ihatebees.item.custom.StarSawSwordItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class StarSawModel extends GeoModel<StarSawSwordItem> {
    private final Identifier model = new Identifier(BeeMod.MODID, "geo/item/starsaw.geo.json");
    private final Identifier texture = new Identifier(BeeMod.MODID, "textures/item/starsawmodel.png");
    private final Identifier animations = new Identifier(BeeMod.MODID, "animations/item/starsaw.animation.json");


    public Identifier getModelLocation(StarSawSwordItem object) {
        return this.model;
    }


    public Identifier getTextureLocation(StarSawSwordItem object) {
        return this.texture;
    }


    public Identifier getAnimationFileLocation(StarSawSwordItem object) {
        return this.animations;
    }

    @Override
    public Identifier getModelResource(StarSawSwordItem animatable) {
        return this.model;
    }

    @Override
    public Identifier getTextureResource(StarSawSwordItem animatable) {
        return this.texture;
    }

    @Override
    public Identifier getAnimationResource(StarSawSwordItem animatable) {
        return this.animations;
    }
}