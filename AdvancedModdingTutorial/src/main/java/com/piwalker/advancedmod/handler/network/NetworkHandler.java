package com.piwalker.advancedmod.handler.network;

import com.piwalker.advancedmod.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by SamuelPiWalker on 7/22/2015.
 */
public class NetworkHandler {

    private static SimpleNetworkWrapper INSTANCE;

    public static void init(){
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

        INSTANCE.registerMessage(MessageExplode.class, MessageExplode.class, 0, Side.SERVER);
    }

    public static void sendToServer(IMessage message){
        INSTANCE.sendToServer(message);
    }



}
