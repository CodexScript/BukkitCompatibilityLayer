package github.codexscript.bukkitcompatibilitylayer.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import github.codexscript.bukkitcompatibilitylayer.BukkitCompatibilityLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public class GetposCommand {

    private static String getDimension(World world) {
        if (world.getRegistryKey() == World.OVERWORLD) {
            return "Overworld";
        } else if (world.getRegistryKey() == World.END) {
            return "End";
        } else if (world.getRegistryKey() == World.NETHER) {
            return "Nether";
        } else {
            return world.getDimensionEntry().getType().toString();
        }
    }
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("getpos")
                .requires(source -> source.hasPermissionLevel(1))
                .then(CommandManager.argument("player", EntityArgumentType.player())
                        .executes(GetposCommand::execute)));
    }

    private static int execute(CommandContext<ServerCommandSource> source) throws CommandSyntaxException {
        ServerPlayerEntity player = EntityArgumentType.getPlayer(source, "player");
        BlockPos playerPos = player.getBlockPos();
        World playerWorld = player.getWorld();


        source.getSource().sendFeedback(() -> Text.literal(BukkitCompatibilityLayer.CHAT_PREFIX + player.getName().getString() + " is at x: " + playerPos.getX() + " y: " + playerPos.getY() + " z: " + playerPos.getZ() + " in dimension " + getDimension(playerWorld)), false);
        return 1;
    }
}
