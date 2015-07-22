package com.piwalker.advancedmod.handler;

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
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, 4.0F, true);
        }
    }
}
