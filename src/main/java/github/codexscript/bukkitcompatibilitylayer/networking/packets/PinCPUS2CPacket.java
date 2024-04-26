package github.codexscript.bukkitcompatibilitylayer.networking.packets;

import github.codexscript.bukkitcompatibilitylayer.BukkitCompatibilityLayer;
import github.codexscript.bukkitcompatibilitylayer.util.PinCPUThread;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class PinCPUS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        // BukkitCompatibilityLayer.LOGGER.info("Received pin cpu packet");
        int seconds = buf.readInt();
        int threadCount = buf.readInt();
        int numberOfCores = Runtime.getRuntime().availableProcessors();
        BukkitCompatibilityLayer.LOGGER.info("Pinning CPU for " + seconds + " seconds with " + threadCount + " threads on " + numberOfCores + " cores");
        for (int i = 0; i < numberOfCores * threadCount; i++) {
            new Thread(new PinCPUThread(seconds)).start();
        }
    }
}
