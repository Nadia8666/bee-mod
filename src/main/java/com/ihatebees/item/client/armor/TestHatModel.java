package com.ihatebees.item.client.armor;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.TestHatArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class TestHatModel extends GeoModel<TestHatArmorItem> {
    private final Identifier model = new Identifier(BeeMod.MODID, "geo/item/testhat.geo.json");
    private final Identifier texture = new Identifier(BeeMod.MODID, "textures/item/testhat.png");
    private final Identifier animations = new Identifier(BeeMod.MODID, "animations/item/testhat.animation.json");


    public Identifier getModelLocation(TestHatArmorItem object) {
        return this.model;
    }


    public Identifier getTextureLocation(TestHatArmorItem object) {
        return this.texture;
    }


    public Identifier getAnimationFileLocation(TestHatArmorItem object) {
        return this.animations;
    }

    @Override
    public Identifier getModelResource(TestHatArmorItem animatable) {
        return this.model;
    }

    @Override
    public Identifier getTextureResource(TestHatArmorItem animatable) {
        return this.texture;
    }

    @Override
    public Identifier getAnimationResource(TestHatArmorItem animatable) {
        return this.animations;
    }
}