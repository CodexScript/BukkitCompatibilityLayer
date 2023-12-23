package github.codexscript.bukkitcompatibilitylayer;

import github.codexscript.bukkitcompatibilitylayer.command.GetposCommand;
import github.codexscript.bukkitcompatibilitylayer.command.ModListCommand;
import github.codexscript.bukkitcompatibilitylayer.command.ScareCommand;
import github.codexscript.bukkitcompatibilitylayer.events.UseBlockListener;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BukkitCompatibilityLayer implements ModInitializer {
    public static final String MOD_NAME = "BukkitCompatibilityLayer";
    public static final String MOD_COLOR = "§3"; // §3 = dark aqua

    public static final String CHAT_PREFIX = BukkitCompatibilityLayer.MOD_COLOR + "[" + BukkitCompatibilityLayer.MOD_NAME + "]§r ";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME.toLowerCase());

    public static final GameRules.Key<GameRules.BooleanRule> DISABLE_END = GameRuleRegistry.register("disableEnd", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false));

    @Override
    public void onInitialize() {
        UseBlockCallback.EVENT.register(UseBlockListener::onUseBlock);
        CommandRegistrationCallback.EVENT.register(ScareCommand::register);
        CommandRegistrationCallback.EVENT.register(ModListCommand::register);
        CommandRegistrationCallback.EVENT.register(GetposCommand::register);
    }
}
