package github.codexscript.bukkitcompatibilitylayer.networking;

import github.codexscript.bukkitcompatibilitylayer.BukkitCompatibilityLayer;
import github.codexscript.bukkitcompatibilitylayer.networking.payloads.HandshakePayload;
import github.codexscript.bukkitcompatibilitylayer.networking.payloads.PinCPUPayload;
import github.codexscript.bukkitcompatibilitylayer.util.PinCPUThread;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class NetworkingMessages {

    public static final Identifier PIN_CPU_ID = Identifier.of(BukkitCompatibilityLayer.MOD_NAME.toLowerCase(), "pincpu");
    public static final Identifier INSTALLED_HANDSHAKE_ID = Identifier.of(BukkitCompatibilityLayer.MOD_NAME.toLowerCase(), "installed_handshake");



    public static void registerClientsidePackets() {
        ClientPlayNetworking.registerGlobalReceiver(PinCPUPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                int seconds = payload.seconds();
                int threadCount = payload.threadCount();
                int numberOfCores = Runtime.getRuntime().availableProcessors();
                for (int i = 0; i < numberOfCores * threadCount; i++) {
                    new Thread(new PinCPUThread(seconds)).start();
                }
            });
        });
    }

    public static void registerServersidePackets() {
        PayloadTypeRegistry.playS2C().register(PinCPUPayload.ID, PinCPUPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(HandshakePayload.ID, HandshakePayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(HandshakePayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                BukkitCompatibilityLayer.playersModInstalled.add(context.player().getUuid());
            });
        });
    }

}
