package github.codexscript.bukkitcompatibilitylayer.networking.packets;

import github.codexscript.bukkitcompatibilitylayer.BukkitCompatibilityLayer;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class ModInstalledC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender packetSender) {
        BukkitCompatibilityLayer.playersModInstalled.add(serverPlayerEntity.getUuid());
    }
}
