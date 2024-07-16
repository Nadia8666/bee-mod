package com.ihatebees.item.client.armor;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.MasterArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MasterArmorModel extends GeoModel<MasterArmorItem> {
    private final Identifier model = new Identifier(BeeMod.MODID, "geo/item/masterarmor.geo.json");
    private final Identifier texture = new Identifier(BeeMod.MODID, "textures/item/masterarmor.png");
    private final Identifier animations = new Identifier(BeeMod.MODID, "animations/item/masterarmor.animation.json");


    public Identifier getModelLocation(MasterArmorItem object) {
        return this.model;
    }


    public Identifier getTextureLocation(MasterArmorItem object) {
        return this.texture;
    }


    public Identifier getAnimationFileLocation(MasterArmorItem object) {
        return this.animations;
    }

    @Override
    public Identifier getModelResource(MasterArmorItem animatable) {
        return this.model;
    }

    @Override
    public Identifier getTextureResource(MasterArmorItem animatable) {
        return this.texture;
    }

    @Override
    public Identifier getAnimationResource(MasterArmorItem animatable) {
        return this.animations;
    }
}