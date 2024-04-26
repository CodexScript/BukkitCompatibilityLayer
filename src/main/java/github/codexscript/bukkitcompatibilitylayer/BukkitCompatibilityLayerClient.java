package github.codexscript.bukkitcompatibilitylayer;

import github.codexscript.bukkitcompatibilitylayer.networking.NetworkingMessages;
import net.fabricmc.api.ClientModInitializer;

public class BukkitCompatibilityLayerClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        NetworkingMessages.registerS2CPackets();
    }
}
