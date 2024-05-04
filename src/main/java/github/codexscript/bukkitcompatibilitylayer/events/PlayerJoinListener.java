package github.codexscript.bukkitcompatibilitylayer.events;

import github.codexscript.bukkitcompatibilitylayer.networking.NetworkingMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerJoinListener {

    public static void onPlayerJoin(ServerPlayNetworkHandler serverPlayNetworkHandler, PacketSender packetSender, MinecraftServer minecraftServer) {
        if (!minecraftServer.isSingleplayer()) {
            ServerPlayerEntity player = serverPlayNetworkHandler.player;
            ServerPlayNetworking.send(player, NetworkingMessages.INSTALLED_HANDSHAKE_S2C_ID, PacketByteBufs.empty());
        }
    }
}
