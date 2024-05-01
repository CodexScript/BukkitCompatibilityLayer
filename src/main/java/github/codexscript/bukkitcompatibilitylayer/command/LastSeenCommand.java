package github.codexscript.bukkitcompatibilitylayer.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import github.codexscript.bukkitcompatibilitylayer.BukkitCompatibilityLayer;
import github.codexscript.bukkitcompatibilitylayer.StateSaverAndLoader;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LastSeenCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("lastseen")
                .requires(source -> source.hasPermissionLevel(1))
                .then(CommandManager.argument("player", EntityArgumentType.player())
                        .executes(LastSeenCommand::execute)));
    }

    private static int execute(CommandContext<ServerCommandSource> source) throws CommandSyntaxException {
        ServerPlayerEntity player = EntityArgumentType.getPlayer(source, "player");
        long lastSeenEpoch = StateSaverAndLoader.getPlayerState(player).lastSeen;
        if (lastSeenEpoch == 0) {
            source.getSource().sendFeedback(() -> Text.literal(BukkitCompatibilityLayer.CHAT_PREFIX + player.getName().getString() + " has never been seen before."), false);
            return 1;
        } else if (!player.isDisconnected()) {
            source.getSource().sendFeedback(() -> Text.literal(BukkitCompatibilityLayer.CHAT_PREFIX + player.getName().getString() + " is on the server now."), false);
            return 1;
        }

        Instant instant = Instant.ofEpochSecond(lastSeenEpoch);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy h:mm a")
                .withZone(ZoneId.systemDefault());
        String formatted = formatter.format(instant);
        source.getSource().sendFeedback(() -> Text.literal(BukkitCompatibilityLayer.CHAT_PREFIX + player.getName().getString() + " was last seen: " + formatted), false);
        return 1;

    }
}
