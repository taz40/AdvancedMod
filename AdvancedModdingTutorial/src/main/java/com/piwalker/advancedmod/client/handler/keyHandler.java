package com.piwalker.advancedmod.client.handler;

import com.piwalker.advancedmod.handler.network.MessageExplode;
import com.piwalker.advancedmod.handler.network.NetworkHandler;
import com.piwalker.advancedmod.reference.Key;
import com.piwalker.advancedmod.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by SamuelPiWalker on 7/21/2015.
 */
public class keyHandler {
    @SubscribeEvent
    public void onKeyEvent(InputEvent.KeyInputEvent event){
        if(Key.net.isPressed()){
            NetworkHandler.sendToServer(new MessageExplode(3.0F));
        }else if(Key.bigExplode.isPressed()){
            NetworkHandler.sendToServer(new MessageExplode(30.0F));
        }
    }
}
