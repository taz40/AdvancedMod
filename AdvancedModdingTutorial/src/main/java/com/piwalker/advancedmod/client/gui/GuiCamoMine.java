package com.piwalker.advancedmod.client.gui;

import com.piwalker.advancedmod.inventory.ContainerCamoMine;
import com.piwalker.advancedmod.tileentity.TileEntityCamoMine;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * Created by SamuelPiWalker on 7/23/2015.
 */
public class GuiCamoMine extends GuiAdvancedMod {
    public GuiCamoMine(InventoryPlayer playerInventory, TileEntityCamoMine te) {
        super(new ContainerCamoMine(playerInventory, te), "camoMine", te);
    }

}
