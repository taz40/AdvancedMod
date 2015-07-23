package com.piwalker.advancedmod;

import com.piwalker.advancedmod.handler.keyHandler;
import com.piwalker.advancedmod.handler.network.NetworkHandler;
import com.piwalker.advancedmod.init.ModTileEntities;
import com.piwalker.advancedmod.world.gen.WorldGeneratorFlag;
import com.piwalker.advancedmod.init.ModBlocks;
import com.piwalker.advancedmod.proxy.IProxy;
import com.piwalker.advancedmod.reference.Reference;
import com.piwalker.advancedmod.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by SamuelPiWalker on 7/21/2015.
 */
@Mod(modid= Reference.MOD_ID, name=Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class AdvancedMod {

    @Mod.Instance
    public static AdvancedMod instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit();
        GameRegistry.registerWorldGenerator(new WorldGeneratorFlag(), 0);
        ModBlocks.init();
        NetworkHandler.init();
        ModTileEntities.init();
        LogHelper.info("Pre Initialization Complete.");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init();
        LogHelper.info("Initialization Complete.");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit();
        LogHelper.info("Post Initialization Complete");
    }


}
