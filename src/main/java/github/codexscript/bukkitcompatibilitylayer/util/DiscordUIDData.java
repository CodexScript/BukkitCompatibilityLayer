package github.codexscript.bukkitcompatibilitylayer.util;

import net.minecraft.nbt.NbtCompound;

public class DiscordUIDData {
    public static String setUID(IEntityDataSaver player, String id) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putString("discordUID", id);
        return id;
    }
}
