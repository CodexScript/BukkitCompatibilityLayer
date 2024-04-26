package github.codexscript.bukkitcompatibilitylayer.networking;

import github.codexscript.bukkitcompatibilitylayer.BukkitCompatibilityLayer;
import github.codexscript.bukkitcompatibilitylayer.networking.packets.ModInstalledC2SPacket;
import github.codexscript.bukkitcompatibilitylayer.networking.packets.ModInstalledS2CPacket;
import github.codexscript.bukkitcompatibilitylayer.networking.packets.PinCPUS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class NetworkingMessages {

    public static final Identifier PIN_CPU_ID = new Identifier(BukkitCompatibilityLayer.MOD_NAME.toLowerCase(), "pincpu");
    public static final Identifier INSTALLED_HANDSHAKE_S2C_ID = new Identifier(BukkitCompatibilityLayer.MOD_NAME.toLowerCase(), "installed_handshake_s2c");
    public static final Identifier INSTALLED_HANDSHAKE_C2S_ID = new Identifier(BukkitCompatibilityLayer.MOD_NAME.toLowerCase(), "installed_handshake_c2s");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(INSTALLED_HANDSHAKE_C2S_ID, ModInstalledC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(PIN_CPU_ID, PinCPUS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(INSTALLED_HANDSHAKE_S2C_ID, ModInstalledS2CPacket::receive);
    }
}
