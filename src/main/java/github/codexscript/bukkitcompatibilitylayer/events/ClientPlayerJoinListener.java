package github.codexscript.bukkitcompatibilitylayer.events;

import github.codexscript.bukkitcompatibilitylayer.networking.payloads.HandshakePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class ClientPlayerJoinListener {
    public static void onPlayerJoin(ClientPlayNetworkHandler clientPlayNetworkHandler, PacketSender packetSender, MinecraftClient minecraftClient) {
        ClientPlayNetworking.send(new HandshakePayload());
    }
}
