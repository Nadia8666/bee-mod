package com.ihatebees.item.custom;

import net.minecraft.item.GoatHornItem;
import net.minecraft.item.Instrument;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

import java.util.UUID;

public class PassiveItem extends GoatHornItem {
    public PassiveItem(Settings settings, TagKey<Instrument> instrumentTag) {
        super(settings, instrumentTag);



    }
}

