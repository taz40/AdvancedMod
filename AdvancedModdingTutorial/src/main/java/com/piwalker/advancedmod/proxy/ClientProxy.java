package com.piwalker.advancedmod.proxy;

import com.piwalker.advancedmod.handler.keyHandler;
import com.piwalker.advancedmod.reference.Key;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 * Created by SamuelPiWalker on 7/21/2015.
 */
public class ClientProxy extends CommonProxy{
    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        registerKeyBindings();
    }

    @Override
    public void postInit() {

    }

    private void registerKeyBindings(){
        FMLCommonHandler.instance().bus().register(new keyHandler());
        ClientRegistry.registerKeyBinding(Key.net);
    }
}
