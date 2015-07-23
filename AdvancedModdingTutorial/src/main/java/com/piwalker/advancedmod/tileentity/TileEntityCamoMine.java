package com.piwalker.advancedmod.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

/**
 * Created by SamuelPiWalker on 7/22/2015.
 */
public class TileEntityCamoMine extends TileEntity {
    private int timer = 60;

    public void updateEntity(){
        if(!worldObj.isRemote){
            if(timer == 0){
                List<Entity> entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord -1, zCoord -1, xCoord+2, yCoord +2, zCoord+2));
                if(entities.size() > 0) {
                    worldObj.createExplosion(null, this.xCoord + .5f, this.yCoord + .5f, this.zCoord + .5f, 10.0F, true);
                }
            }else{
                timer--;
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if(nbt.hasKey("timer")){
            timer = nbt.getInteger("timer");
        }
    }


    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("timer", timer);
    }
}
