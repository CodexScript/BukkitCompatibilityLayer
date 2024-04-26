package github.codexscript.bukkitcompatibilitylayer.networking.packets;

import github.codexscript.bukkitcompatibilitylayer.networking.NetworkingMessages;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;

public class ModInstalledS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        ClientPlayNetworking.send(NetworkingMessages.INSTALLED_HANDSHAKE_C2S_ID, PacketByteBufs.empty());
    }
}
