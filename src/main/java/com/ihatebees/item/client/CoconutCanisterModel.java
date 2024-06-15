package com.ihatebees.item.client;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.CoconutCanisterArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CoconutCanisterModel extends GeoModel<CoconutCanisterArmorItem> {
    private final Identifier model = new Identifier(BeeMod.MODID, "geo/item/coconutcanister.geo.json");
    private final Identifier texture = new Identifier(BeeMod.MODID, "textures/item/coconutcanister.png");
    private final Identifier animations = new Identifier(BeeMod.MODID, "animations/item/coconutcanister.animation.json");


    public Identifier getModelLocation(CoconutCanisterArmorItem object) {
        return this.model;
    }


    public Identifier getTextureLocation(CoconutCanisterArmorItem object) {
        return this.texture;
    }


    public Identifier getAnimationFileLocation(CoconutCanisterArmorItem object) {
        return this.animations;
    }

    @Override
    public Identifier getModelResource(CoconutCanisterArmorItem animatable) {
        return this.model;
    }

    @Override
    public Identifier getTextureResource(CoconutCanisterArmorItem animatable) {
        return this.texture;
    }

    @Override
    public Identifier getAnimationResource(CoconutCanisterArmorItem animatable) {
        return this.animations;
    }
}