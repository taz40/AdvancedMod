package com.piwalker.advancedmod.inventory;

import com.piwalker.advancedmod.tileentity.TileEntityCamoMine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by SamuelPiWalker on 7/23/2015.
 */
public class ContainerCamoMine extends ContainerAdvancedMod {
    private TileEntityCamoMine te;

    public ContainerCamoMine(InventoryPlayer playerInventory, TileEntityCamoMine te){

        this.addSlotToContainer(new SlotsCamouflage(te, 0, 80, 58));
        this.addSlotToContainer(new SlotsCamouflage(te, 1, 80, 22));
        this.addSlotToContainer(new SlotsCamouflage(te, 2, 80, 40));
        this.addSlotToContainer(new SlotsCamouflage(te, 3, 102, 18));
        this.addSlotToContainer(new SlotsCamouflage(te, 4, 62, 40));
        this.addSlotToContainer(new SlotsCamouflage(te, 5, 98, 40));

        this.addPlayerSlots(playerInventory, 8, 84);
        this.te = te;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return te.isUseableByPlayer(player);
    }


    public ItemStack transferStackInSlot(EntityPlayer player, int slotNum)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotNum);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (slotNum < 6) {
                if (!this.mergeItemStack(itemstack1, 6, 42, true)) {
                    return null;
                }
            } else {
                boolean transfered = false;
                for (int i = 0; i < 6; i++) {
                    if (!((Slot) inventorySlots.get(i)).getHasStack()) {
                        if (itemstack1.stackSize > 0 && ((Slot)inventorySlots.get(i)).isItemValid(itemstack1)) {
                            ItemStack stack = itemstack1.splitStack(1);
                            if (this.mergeItemStack(stack, i, i + 1, false)) {
                                transfered = true;
                            } else {
                                itemstack1.stackSize++;
                            }
                        }
                    }
                }
                if (!transfered) {
                    return null;
                }
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
