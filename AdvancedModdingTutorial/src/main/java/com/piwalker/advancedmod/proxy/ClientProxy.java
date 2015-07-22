package com.piwalker.advancedmod.proxy;

import com.piwalker.advancedmod.reference.Key;
import cpw.mods.fml.client.registry.ClientRegistry;

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
        ClientRegistry.registerKeyBinding(Key.net);
    }
}
