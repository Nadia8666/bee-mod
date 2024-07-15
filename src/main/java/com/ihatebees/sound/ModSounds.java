package com.ihatebees.sound;

import com.ihatebees.BeeMod;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent ITEM_SAW_SOUND = registerSoundEvent("item_saw_sound");
    public static final SoundEvent ITEM_GUMMYSTAR = registerSoundEvent("item_gummystar");
    public static final SoundEvent ITEM_POPSTAR = registerSoundEvent("item_popstar");
    public static final SoundEvent ITEM_SCORCHINGSTAR = registerSoundEvent("item_scorchingstar");
    public static final SoundEvent COMBO_START = registerSoundEvent("combo_start");
    public static final SoundEvent COMBO_INTRO = registerSoundEvent("combo_intro");
    public static final SoundEvent COMBO_END = registerSoundEvent("combo_end");
    public static final SoundEvent GUMMY_START = registerSoundEvent("gummy_start");
    public static final SoundEvent GUMMY_HIT = registerSoundEvent("gummy_hit");
    public static final SoundEvent GUMMY_END = registerSoundEvent("gummy_end");
    public static final SoundEvent EVENT_FLAME = registerSoundEvent("event_flame");
    public static final SoundEvent EVENT_INFERNO = registerSoundEvent("event_inferno");
    public static final SoundEvent EVENT_SHOWER = registerSoundEvent("event_shower");
    public static final SoundEvent EVENT_ITEM = registerSoundEvent("event_item");
    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(BeeMod.MODID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        BeeMod.LOGGER.info("this game sucks");
    }

}