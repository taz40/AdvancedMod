package com.piwalker.advancedmod.tileentity;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

/**
 * Created by SamuelPiWalker on 7/22/2015.
 */
public class TileEntityCamoMine extends TileEntityAdvancedMod {
    private int timer = 60;
    private ItemStack camoStack;

    public ItemStack getCamouflage() {
        return camoStack;
    }

    public void setCamouflage(ItemStack camoStack) {
        this.camoStack = camoStack;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public void writeToPacket(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, camoStack);
    }

    @Override
    public void readFromPacket(ByteBuf buf) {
        camoStack = ByteBufUtils.readItemStack(buf);
        worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
    }

    public void updateEntity(){
        if(!worldObj.isRemote){
            if(timer == 0){
                List<Entity> entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord -1, zCoord -1, xCoord+2, yCoord +2, zCoord+2));
                if(entities.size() > 0) {
                    worldObj.createExplosion(null, this.xCoord + .5f, this.yCoord + .5f, this.zCoord + .5f, 3.0F, true);
                }
            }else{
                timer--;
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        timer = nbt.getInteger("timer");
        if(nbt.hasKey("camoStack")){
            camoStack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("camoStack"));
        }else{
            camoStack = null;
        }
    }


    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("timer", timer);
        if(camoStack != null) {
            NBTTagCompound camoStackTag = new NBTTagCompound();
            camoStack.readFromNBT(camoStackTag);
            camoStackTag.setTag("camoStack", camoStackTag);
        }
    }
}
