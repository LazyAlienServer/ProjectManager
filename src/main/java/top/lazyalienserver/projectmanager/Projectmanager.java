package top.lazyalienserver.projectmanager;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lazyalienserver.projectmanager.command.ProjectManager;


public class Projectmanager implements ModInitializer {
    private final static Logger logger = LoggerFactory.getLogger("ProjectManager");

    @Override
    public void onInitialize() {
        logger.info("ProjectManager is initializing");
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> ProjectManager.register(dispatcher));

    }
}
