package com.ihatebees.item.client.tool;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.DarkScytheSwordItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class DarkScytheModel extends GeoModel<DarkScytheSwordItem> {
    private final Identifier model = new Identifier(BeeMod.MODID, "geo/item/darkscythe.geo.json");
    private final Identifier texture = new Identifier(BeeMod.MODID, "textures/item/darkscythe.png");
    private final Identifier animations = new Identifier(BeeMod.MODID, "animations/item/darkscythe.animation.json");


    public Identifier getModelLocation(DarkScytheSwordItem object) {
        return this.model;
    }


    public Identifier getTextureLocation(DarkScytheSwordItem object) {
        return this.texture;
    }


    public Identifier getAnimationFileLocation(DarkScytheSwordItem object) {
        return this.animations;
    }

    @Override
    public Identifier getModelResource(DarkScytheSwordItem animatable) {
        return this.model;
    }

    @Override
    public Identifier getTextureResource(DarkScytheSwordItem animatable) {
        return this.texture;
    }

    @Override
    public Identifier getAnimationResource(DarkScytheSwordItem animatable) {
        return this.animations;
    }
}