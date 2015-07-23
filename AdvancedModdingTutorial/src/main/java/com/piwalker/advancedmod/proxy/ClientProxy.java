package com.piwalker.advancedmod.proxy;

import com.piwalker.advancedmod.handler.keyHandler;
import com.piwalker.advancedmod.reference.Key;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

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

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    private void registerKeyBindings(){
        FMLCommonHandler.instance().bus().register(new keyHandler());
        ClientRegistry.registerKeyBinding(Key.net);
    }
}
