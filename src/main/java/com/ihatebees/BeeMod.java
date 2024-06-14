package com.ihatebees;

import com.ihatebees.item.ModItemGroups;
import com.ihatebees.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeeMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("beemod");
	public static final String MODID = "beemod";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Registering Bee Mod Things");

		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();

		LOGGER.info("Bee Mod Registration Complete");
	}
}