package github.codexscript.bukkitcompatibilitylayer.mixin;

import github.codexscript.bukkitcompatibilitylayer.BukkitCompatibilityLayer;
import github.codexscript.bukkitcompatibilitylayer.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityDataSaverMixin implements IEntityDataSaver {
    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if (this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(at = @At("HEAD"), method = "writeNbt")
    protected void injectWriteNbt(NbtCompound nbt, CallbackInfoReturnable info) {
        if (persistentData != null) {
            nbt.put(BukkitCompatibilityLayer.MOD_NAME.toLowerCase() + ".discordUID", persistentData);
        }
    }

    @Inject(at = @At("HEAD"), method = "readNbt")
    protected void injectReadNbt(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains(BukkitCompatibilityLayer.MOD_NAME.toLowerCase() + ".discordUID", 10)) {
            persistentData = nbt.getCompound(BukkitCompatibilityLayer.MOD_NAME.toLowerCase() + ".discordUID");
        }
    }
}
