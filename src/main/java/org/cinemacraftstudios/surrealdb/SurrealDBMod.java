package org.cinemacraftstudios.surrealdb;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

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



        db = new SurrealDBInstance(event.getSourceFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {}

}
