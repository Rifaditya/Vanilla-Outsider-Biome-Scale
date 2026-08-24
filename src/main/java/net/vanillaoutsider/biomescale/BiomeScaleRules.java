package net.vanillaoutsider.biomescale;

// Verified against: GameRules.java (Snapshot 10 / 26.1.2)

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.world.level.Level;
import net.minecraft.resources.Identifier;

public class BiomeScaleRules {
    public static final GameRuleCategory CATEGORY = DynamicGameRuleManager.registerCategory(
            Identifier.fromNamespaceAndPath("biome_scale", "biome_scale")
    );

    public static GameRule<Integer> GLOBAL_BIOME_SCALE_MODIFIER;
    
    public static double cachedScaleModifier = 2.0;

    public static double getScaleModifier(Level level) {
        if (GLOBAL_BIOME_SCALE_MODIFIER == null) return 2.0;
        int val = DynamicGameRuleManager.getInt(level, GLOBAL_BIOME_SCALE_MODIFIER);
        if (val <= 0) return 0.01; // prevent zero division logic later
        return val / 100.0;
    }

    public static void init() {
        GLOBAL_BIOME_SCALE_MODIFIER = DynamicGameRuleManager.integerRule("biome_scale:scale_modifier", CATEGORY, 200)
            .register();

        ServerTickEvents.START_SERVER_TICK.register(server -> {
            cachedScaleModifier = getScaleModifier(server.overworld());
        });
    }
}
