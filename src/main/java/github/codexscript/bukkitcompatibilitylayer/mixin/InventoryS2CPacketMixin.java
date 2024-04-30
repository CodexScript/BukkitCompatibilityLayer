package github.codexscript.bukkitcompatibilitylayer.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(InventoryS2CPacket.class)
public class InventoryS2CPacketMixin {
    @Inject(at = @At("HEAD"), method = "getContents", cancellable = true)
    private void getContents(CallbackInfoReturnable<List<ItemStack>> cir) {
        List<ItemStack> contents = ((InventoryS2CPacket)(Object)this).getContents();
        for (ItemStack itemStack : contents) {
            itemStack.setCustomName(Text.of("This is a custom name"));
        }
        cir.setReturnValue(contents);
    }
}
