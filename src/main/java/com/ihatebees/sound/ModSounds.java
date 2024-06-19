package com.ihatebees.sound;

import com.ihatebees.BeeMod;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent ITEM_SAW_SOUND = registerSoundEvent("item_saw_sound");
    public static final SoundEvent ITEM_GUMMYSTAR = registerSoundEvent("item_gummystar");
    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(BeeMod.MODID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        BeeMod.LOGGER.info("this game sucks");
    }

}