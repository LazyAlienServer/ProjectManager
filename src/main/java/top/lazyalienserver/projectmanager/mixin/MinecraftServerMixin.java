package top.lazyalienserver.projectmanager.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.lazyalienserver.projectmanager.Projectmanager;
import top.lazyalienserver.projectmanager.command.ProjectManager;
import top.lazyalienserver.projectmanager.utils.ProjectListSave;

import java.io.IOException;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(method = "shutdown", at = @At("HEAD"))
    public void shutdownMixin(CallbackInfo ci) throws IOException {
        ProjectListSave.saveProjectList();
    }
    MinecraftServer minecraftServer = (MinecraftServer) (Object) this;
    @Unique
    private static final Logger logger = LoggerFactory.getLogger("ServerWorldMixin");
    @Inject(method = "loadWorld", at = @At("RETURN"))
    public void loadWorldMixin(CallbackInfo ci) throws IOException {
        logger.info(minecraftServer.getSavePath(WorldSavePath.ROOT).toString());
        ProjectManager.projects.clear();
        ProjectListSave.pathSet(minecraftServer.getSavePath(WorldSavePath.ROOT).toString() + "/LazyAlienServer/ProjectManager/ProjectList.json");
        ProjectManager.projects=ProjectListSave.readProjectList()!=null?ProjectListSave.readProjectList():ProjectManager.projects;
    }
}
