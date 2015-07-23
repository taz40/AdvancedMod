package com.piwalker.advancedmod.inventory;


import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by SamuelPiWalker on 7/23/2015.
 */
public class SlotsCamouflage extends Slot {
    public SlotsCamouflage(IInventory inventory, int inventoryIndex, int x, int y) {
        super(inventory, inventoryIndex, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return this.inventory.isItemValidForSlot(slotNumber, stack);
    }
}
