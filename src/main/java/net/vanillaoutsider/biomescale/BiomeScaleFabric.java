package net.vanillaoutsider.biomescale;

// Verified against: ModInitializer.java (Fabric API)

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BiomeScaleFabric implements ModInitializer {
    public static final String MOD_ID = "vanilla-outsider-biome-scale";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        BiomeScaleRules.init();
        LOGGER.info("Vanilla Outsider: Biome Scale Initialized");
    }
}
