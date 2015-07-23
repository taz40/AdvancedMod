package com.piwalker.advancedmod.handler.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by SamuelPiWalker on 7/22/2015.
 */
public class MessageExplode extends MessageBase<MessageExplode> {

    private float explosionSize;

    public MessageExplode(){
    }

    public MessageExplode(float explosionSize){
        this.explosionSize = explosionSize;
    }

    @Override
    public void handleClientSide(MessageExplode message, EntityPlayer player) {

    }

    @Override
    public void handleServerSide(MessageExplode message, EntityPlayer player) {
        player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, message.explosionSize, true);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        explosionSize = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(explosionSize);
    }
}
