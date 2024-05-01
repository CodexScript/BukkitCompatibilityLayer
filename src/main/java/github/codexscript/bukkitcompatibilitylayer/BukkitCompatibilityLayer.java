package github.codexscript.bukkitcompatibilitylayer;

import github.codexscript.bukkitcompatibilitylayer.command.*;
import github.codexscript.bukkitcompatibilitylayer.events.PlayerJoinListener;
import github.codexscript.bukkitcompatibilitylayer.events.PlayerLeaveListener;
import github.codexscript.bukkitcompatibilitylayer.events.UseBlockListener;
import github.codexscript.bukkitcompatibilitylayer.networking.NetworkingMessages;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;

public class BukkitCompatibilityLayer implements ModInitializer {
    public static final String MOD_NAME = "BukkitCompatibilityLayer";
    public static final String MOD_COLOR = "§3"; // §3 = dark aqua

    public static final String CHAT_PREFIX = "§r" + BukkitCompatibilityLayer.MOD_COLOR + "[" + BukkitCompatibilityLayer.MOD_NAME + "]§r ";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME.toLowerCase());

    public static final GameRules.Key<GameRules.BooleanRule> DISABLE_END = GameRuleRegistry.register("disableEnd", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false));

    public static HashMap<Object, Boolean> playersGhosting = new HashMap<>();
    public static Set<UUID> playersModInstalled = ConcurrentHashMap.newKeySet();

    public static final ScheduledExecutorService exectuor = java.util.concurrent.Executors.newScheduledThreadPool(1);
    @Override
    public void onInitialize() {
        UseBlockCallback.EVENT.register(UseBlockListener::onUseBlock);

        NetworkingMessages.registerC2SPackets();
        ServerPlayConnectionEvents.JOIN.register(PlayerJoinListener::onPlayerJoin);
        ServerPlayConnectionEvents.DISCONNECT.register(PlayerLeaveListener::onPlayerLeave);

        // Command registration
        CommandRegistrationCallback.EVENT.register(ScareCommand::register);
        CommandRegistrationCallback.EVENT.register(ModListCommand::register);
        CommandRegistrationCallback.EVENT.register(GetposCommand::register);
        CommandRegistrationCallback.EVENT.register(GhostKickCommand::register);
        CommandRegistrationCallback.EVENT.register(GetDiscordUIDCommand::register);
        CommandRegistrationCallback.EVENT.register(SetDiscordUIDCommand::register);
        CommandRegistrationCallback.EVENT.register(PinCPUCommand::register);
        CommandRegistrationCallback.EVENT.register(LastSeenCommand::register);
    }
}
