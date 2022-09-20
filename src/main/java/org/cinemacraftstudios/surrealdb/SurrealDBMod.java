package org.cinemacraftstudios.surrealdb;

import com.alibaba.fastjson.JSON;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

@Mod(modid = SurrealDBMod.MODID, name = SurrealDBMod.NAME, version = SurrealDBMod.VERSION)
public class SurrealDBMod {

    public static final String MODID = "surrealdb";
    public static final String NAME = "SurrealDB Mod";
    public static final String VERSION = "0.1";

    public static Logger logger;

    SurrealDBInstance db;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        File config = event.getSuggestedConfigurationFile();

        SurrealDBData data = null;
        if(config.exists()) {
            try {
                data = JSON.parseObject(FileUtils.readFileToString(config, "UTF-8"), SurrealDBData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            data = new SurrealDBData("minecraft", "notch", "0.0.0.0:8000", false);
            String content = JSON.toJSONString(data, true);
            try {
                FileUtils.writeStringToFile(config, content, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        db = new SurrealDBInstance(data, event.getSourceFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {}

}
