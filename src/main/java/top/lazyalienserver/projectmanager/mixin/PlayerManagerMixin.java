package top.lazyalienserver.projectmanager.mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.collection.DefaultedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.lazyalienserver.projectmanager.helper.LitematicaReader;

import java.io.IOException;
import java.util.Objects;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Unique
    private static final Logger logger = LoggerFactory.getLogger("PlayerManagerMixin");

    @Inject(method = "remove", at = @At("HEAD"))
    public void removeMixin(ServerPlayerEntity player, CallbackInfo ci) throws IOException, CommandSyntaxException {
        DefaultedList<ItemStack> inventory = player.getInventory().main;
        int count = 0;
        int shulkerBoxCount = 0;
        LitematicaReader litematicaReader = new LitematicaReader("/Users/diojo/IdeaProjects/ProjectManager/run/schematics/JNTMX.litematic");
        String lists = litematicaReader.getContent();
        for (ItemStack itemStack : inventory) {
            if (lists.contains(itemStack.getItem().toString()) && !Objects.equals(itemStack.getItem().toString(), "air") && !Objects.equals(itemStack.getItem().toString(), "shulker_box")) {
                count += itemStack.getCount();
            } else if (Objects.equals(itemStack.getItem().toString(), "shulker_box")) {
                shulkerBoxCount++;
            }
        }
        for (PlayerEntity players : player.getWorld().getPlayers()) {
            players.sendMessage(new LiteralText(player.getName().asString() + "下线,背包中有 " + count + " 个物品(除去潜影盒)属于项目的材料清单中,有 " + shulkerBoxCount + " 个潜影盒"), false);
        }/*-----------------未完成----------------------*/
        logger.info("Player {} 下线,背包中有 {} 个物品(除去潜影盒)属于项目的材料清单中,有 {} 个潜影盒", player.getName().asString(), count, shulkerBoxCount);
    }
}
