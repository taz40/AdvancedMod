package com.piwalker.advancedmod.client.handler;

import com.piwalker.advancedmod.client.gui.GuiCamoMine;
import com.piwalker.advancedmod.inventory.ContainerCamoMine;
import com.piwalker.advancedmod.tileentity.TileEntityCamoMine;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by SamuelPiWalker on 7/23/2015.
 */
public class GuiHandler implements IGuiHandler {

    public enum GuiIDs{
        CAMO_MINE;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
       switch(GuiIDs.values()[ID]){
           case CAMO_MINE:
               return new ContainerCamoMine(player.inventory, (TileEntityCamoMine)world.getTileEntity(x, y, z));
           default:
               throw new IllegalArgumentException("No GUI with id "+ID);
       }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(GuiIDs.values()[ID]){
            case CAMO_MINE:
                return new GuiCamoMine(player.inventory, (TileEntityCamoMine)world.getTileEntity(x, y, z));
            default:
                throw new IllegalArgumentException("No GUI with id "+ID);
        }
    }
}
