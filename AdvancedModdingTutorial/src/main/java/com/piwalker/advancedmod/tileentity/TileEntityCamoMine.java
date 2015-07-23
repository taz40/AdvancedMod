package com.piwalker.advancedmod.tileentity;

import com.piwalker.advancedmod.init.ModBlocks;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SamuelPiWalker on 7/22/2015.
 */
public class TileEntityCamoMine extends TileEntityAdvancedMod implements ISidedInventory {
    private int timer = 60;
    private ItemStack[] camoStacks = new ItemStack[6];

    public ItemStack getCamouflage(int side) {
        return this.getStackInSlot(side);
    }

    public void setCamouflage(ItemStack camoStack, int side) {
        this.setInventorySlotContents(side, camoStack);
    }

    @Override
    public void writeToPacket(ByteBuf buf) {
        for(int i = 0; i < camoStacks.length; i++) {
            ByteBufUtils.writeItemStack(buf, camoStacks[i]);
        }
    }

    @Override
    public void readFromPacket(ByteBuf buf) {
        for(int i = 0; i < camoStacks.length; i++) {
            camoStacks[i] = ByteBufUtils.readItemStack(buf);
        }
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

        Arrays.fill(camoStacks, null);
        NBTTagList camoStackTag = nbt.getTagList("camoStacks", 10);

        for(int i = 0; i < camoStackTag.tagCount(); i++) {
            NBTTagCompound tag = camoStackTag.getCompoundTagAt(i);
            int index = tag.getByte("index");
            if(index >= 0 && index < camoStacks.length) {
                camoStacks[index] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }


    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("timer", timer);

        NBTTagList camoStackTag = new NBTTagList();
        for(int i = 0; i < camoStacks.length; i++){
            ItemStack stack = camoStacks[i];
            if (stack != null) {
                NBTTagCompound camoStackTag1 = new NBTTagCompound();
                camoStackTag1.setByte("index", (byte) i);
                stack.writeToNBT(camoStackTag1);
                camoStackTag.appendTag(camoStackTag1);
            }
        }
        nbt.setTag("camoStacks", camoStackTag);
    }

    //Start of IInventory Methods

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return camoStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int slot)
    {
        return this.camoStacks[slot];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int slot, int amount)
    {
        if (this.camoStacks[slot] != null)
        {
            ItemStack itemstack;

            if (this.camoStacks[slot].stackSize <= amount)
            {
                itemstack = this.camoStacks[slot];
                this.setInventorySlotContents(slot, null);
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.camoStacks[slot].splitStack(amount);

                if (this.camoStacks[slot].stackSize == 0)
                {
                    this.setInventorySlotContents(slot, null);
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (this.camoStacks[slot] != null)
        {
            ItemStack itemstack = this.camoStacks[slot];
            this.camoStacks[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        this.camoStacks[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    /**
     * Returns the name of the inventory
     */
    public String getInventoryName()
    {
        return ModBlocks.camoMine.getUnlocalizedName() + ".name";
    }

    /**
     * Returns if the inventory is named
     */
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    public int getInventoryStackLimit()
    {
        return 1;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {}

    public void closeInventory() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return stack != null && stack.getItem() instanceof ItemBlock;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return new int[]{side};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return true;
    }
}
